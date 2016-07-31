package se.BaseUlterior.AI;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import se.BaseUlterior.Aim.Processed.AIAim;
import se.BaseUlterior.Aim.Processed.AIAimTurret;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * @author Johan Akerlund
 */

public abstract class AITurret extends AIMotionLess {

	protected AIAim aim;
	protected Entity target;

	protected Vector2 startAngle;
	protected final int UPDATE_SPEED = Constants.TURRETS_MAX_RELOAD_SPEED;
	protected Vector2 aimArm;

	protected int timeSinceLast;
	private int itr;

	private SpriteSheet gunFire = null;
	private Animation animationGunfire = null;

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;
	private final float MAX_SPEE_TURRET = Constants.MAX_FOLLOW_SPEED_TURRET;

	protected Image turretImage = null;
	protected int imageHeight;
	protected int imageWidth;
	protected static final float IMAGE_SCALE_STANDARD = 0.26f;

	public AITurret(float[] points, String pathToImage, float imageScale, Vector2 startangle, float height,
			float armLength, SpriteSheet gunFire, Image turretImage) {
		super(points, height);

		this.turretImage = turretImage;
		animationGunfire = new Animation(gunFire, 10);
		animationGunfire.setAutoUpdate(false);
		gunFireFrameWidth = animationGunfire.getCurrentFrame().getWidth();
		gunFireFrameHeight = animationGunfire.getCurrentFrame().getHeight();
		gunFire.setCenterOfRotation(gunFireFrameWidth / 2f, gunFireFrameHeight / 2f);

		this.aim = new AIAimTurret(animationGunfire, armLength);
		this.startAngle = startangle;
		aim.setPosition(getCenterX(), getCenterY());

		aimArm = aim.getArm();
		aimArm.setTheta(startangle.getTheta());
		timeSinceLast = (int) (Math.random() * UPDATE_SPEED);

		init(pathToImage);
		isRotatingObject = true;
	}

	private void init(String pathToImage) {

		imageHeight = (int) (turretImage.getHeight());
		imageWidth = (int) (turretImage.getWidth());
		turretImage.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));
	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		itr++;
		if (lookUpClosestTarget()) {
			if (itr % UPDATE_SPEED == 0) {
				shoot();
				itr = 0;
			}
		} else {
			timeSinceLast++;
		}
		aim.setPosition(getX() + maxRadiusStart, getY() + maxRadiusStart);
		aim.update(container, arg);
		if (HP <= 0) {
			ParallaxPhysicsEngine.gameInfo.setKills(ParallaxPhysicsEngine.gameInfo.getKills() + 1);
			ParallaxPhysicsEngine.objsToRemove.add(this);
		}
	}

	protected abstract void shoot();

	protected abstract void aim();

	protected Vector2 injectVector = new Vector2();

	protected boolean lookUpClosestTarget() {
		boolean result = false;
		float distanceToClosestTarget = Constants.CANVAS_WIDTH;
		for (Entity go : ParallaxPhysicsEngine.all) {
			float dotProduct = aimArm.dot(startAngle);
			if (UlteriorUtils.isWithinRange(go, ParallaxPhysicsEngine.wholeSceene) && !go.motionLess
					&& !go.isBackgroundObj && !go.piercable && go != this) {
				float xDist = go.getCenterX() - this.getCenterX();
				float yDist = go.getCenterY() - this.getCenterY();
				float distanceTest = (float) Math.sqrt((Math.pow(xDist, 2) + Math.pow(yDist, 2)));
				if (distanceTest < distanceToClosestTarget) {
					distanceToClosestTarget = distanceTest;
					injectVector.set(xDist, yDist);
					double diff = (injectVector.getTheta() - aimArm.getTheta());
					if (diff > 0 && diff > MAX_SPEE_TURRET) {
						diff = MAX_SPEE_TURRET;
					} else if (diff < 0 && diff < -MAX_SPEE_TURRET) {
						diff = -MAX_SPEE_TURRET;
					}
					aimArm.add(diff);
					if (dotProduct > 0) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		turretImage.setRotation((float) aimArm.getTheta());
		turretImage.draw(getCenterX() - imageWidth / 2, getCenterY() - imageHeight / 2);
	}
}
