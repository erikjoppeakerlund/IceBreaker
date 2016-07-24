package se.BaseUlterior.AI;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimTurret;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class AITurret extends AI {

	protected Aim aim;
	protected GameObject target;

	protected Vector2 startAngle;
	protected final int UPDATE_SPEED = Constants.TURRETS_MAX_UPDATE_SPEED;
	protected Vector2 aimArm;

	protected int timeSinceLast;
	private int itr;

	private SpriteSheet gunFire = null;
	private Animation animationGunfire = null;

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;
	private final float MAX_SPEE_TURRET = Constants.MAX_SPEE_TURRET;

	public AITurret(float[] points, Vector2 startangle, float height) {
		super(points, height);

		try {
			gunFire = new SpriteSheet("res/img/GUNFIREsimple.png", 96, 96);
			animationGunfire = new Animation(gunFire, 10);
			animationGunfire.setAutoUpdate(false);
			gunFireFrameWidth = animationGunfire.getCurrentFrame().getWidth();
			gunFireFrameHeight = animationGunfire.getCurrentFrame().getHeight();
			gunFire.setCenterOfRotation(gunFireFrameWidth / 2f, gunFireFrameHeight / 2f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.aim = new AimTurret(animationGunfire);
		this.startAngle = startangle;
		aim.setPosition(getCenterX(), getCenterY());
		aimArm = aim.getArm();
		timeSinceLast = (int) (Math.random() * UPDATE_SPEED);
		aim.setAngleToMouse((float) startangle.getTheta());

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
		aim.setPosition(getCenterX(), getCenterY());
		aim.update(container, arg);
		if (HP <= 0) {
			IceBreaker.objsToRemove.add(this);
		}
	}

	protected abstract void shoot();

	protected abstract void aim();

	protected Vector2 injectVector = new Vector2();

	protected boolean lookUpClosestTarget() {
		boolean result = false;
		float distanceToClosestTarget = Constants.CANVAS_WIDTH;
		for (GameObject go : IceBreaker.all) {
			float dotProduct = aimArm.dot(startAngle);
			if (UlteriorUtils.isWithinRange(go, IceBreaker.wholeSceene) && !go.motionLess && !go.isBackgroundObj
					&& !go.piercable && go != this) {
				float xDist = go.getCenterX() - this.getCenterX();
				float yDist = go.getCenterY() - this.getCenterY();
				float distanceTest = (float) Math
						.sqrt((Math.pow(go.getX() - this.getX(), 2) + Math.pow(go.getY() - this.getY(), 2)));
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
}
