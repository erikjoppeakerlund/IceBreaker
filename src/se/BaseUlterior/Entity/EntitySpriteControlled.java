package se.BaseUlterior.Entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimGrenade;
import se.BaseUlterior.Aim.AimMachineGun;
import se.BaseUlterior.Aim.AimRifle;
import se.BaseUlterior.Aim.AimShotGun;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * Game objects which update method contains user input handling
 * 
 * @author Johan Akerlund
 */
public abstract class EntitySpriteControlled extends EntityAgile {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected final float MAX_SPEED = 0.7f;
	protected boolean mouseButtonPirmaryDown = false;

	protected boolean right = true;

	protected float JET_POWER = 0.009f;
	private SpriteSheet sprite = null;
	protected Animation animationMoveRight = null;
	protected Animation animationMoveLeft = null;
	private static final int SHEET_UPDATE_RATE = 85;

	protected boolean directionIsRight = true;

	private SpriteSheet gunFire = null;
	private Animation animationGunfire = null;

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;

	protected float HPCompare;

	private final int SPRITE_IMAGE_SIZE = 88;

	protected EntitySpriteControlled() {
		super(new Circle(Constants.CANVAS_WIDTH / 4, Constants.CANVAS_HEIGHT / 2, Constants.SPRITE_RADIUS).getPoints(),
				0f, false);
		this.setCenterX(Constants.CANVAS_WIDTH / 4);
		this.setCenterY(Constants.CANVAS_HEIGHT / 4);

		color = Color.transparent;
		try {
			sprite = new SpriteSheet("res/img/spriteSheet.png", SPRITE_IMAGE_SIZE, SPRITE_IMAGE_SIZE);
			sprite.bind();

			animationMoveRight = new Animation(false);
			animationMoveLeft = new Animation(false);
			for (int i = 0; i < 4; i++) {
				animationMoveRight.addFrame(sprite.getSprite(i, 0), SHEET_UPDATE_RATE);
			}
			for (int i = 0; i < 4; i++) {
				animationMoveLeft.addFrame(sprite.getSprite(i, 0).getFlippedCopy(true, false), SHEET_UPDATE_RATE);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			gunFire = new SpriteSheet("res/img/GUNFIREsimple.png", 96, 96);
			gunFire.bind();
			animationGunfire = new Animation(gunFire, 10);
			animationGunfire.setAutoUpdate(false);
			gunFireFrameWidth = animationGunfire.getCurrentFrame().getWidth();
			gunFireFrameHeight = animationGunfire.getCurrentFrame().getHeight();
			gunFire.setCenterOfRotation(gunFireFrameWidth / 2f, gunFireFrameHeight / 2f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		initAims();
		motionLess = false;

	}

	private void initAims() {
		aims = new ArrayList<Aim>();
		aims.add(new AimGrenade());
		aims.add(new AimRifle(animationGunfire));
		aims.add(new AimMachineGun(animationGunfire));
		aims.add(new AimShotGun(animationGunfire));
		aim = aims.get(2);
	}

	protected void screenFollowPlayer() {

		float centerX = getCenterX();
		float centerY = getCenterY();
		float moveScreenX = centerX - Constants.CANVAS_WIDTH / 2f;
		float moveScreenY = centerY - Constants.CANVAS_HEIGHT / 2f;

		if (motion.x <= 0 && ParallaxPhysicsEngine.currentX <= 0
				|| motion.x >= 0
						&& ParallaxPhysicsEngine.currentX + Constants.CANVAS_WIDTH >= Constants.CANVAS_WIDTH_FULL
				|| motion.x > 0 && centerX < Constants.CANVAS_WIDTH / 2 || motion.x < 0 && centerX
						+ ParallaxPhysicsEngine.currentX > Constants.CANVAS_WIDTH_FULL - Constants.CANVAS_WIDTH / 2) {
			moveScreenX = 0;
		}

		if (motion.y <= 0 && ParallaxPhysicsEngine.currentY <= 0
				|| motion.y >= 0
						&& ParallaxPhysicsEngine.currentY + Constants.CANVAS_HEIGHT >= Constants.CANVAS_HEIGHT_FULL
				|| motion.y > 0 && centerY < Constants.CANVAS_HEIGHT / 2 || motion.y < 0 && centerY
						+ ParallaxPhysicsEngine.currentY > Constants.CANVAS_HEIGHT_FULL - Constants.CANVAS_HEIGHT / 2) {
			moveScreenY = 0;
		}

		ParallaxPhysicsEngine.moveScreen(moveScreenX, moveScreenY);

	}

	protected void switchScreens() {
		float moveScreenX = 0;
		float moveScreenY = 0;

		float centerX = getCenterX();
		float centerY = getCenterY();

		if (!(centerX > Constants.CANVAS_WIDTH
				&& ParallaxPhysicsEngine.currentX >= Constants.CANVAS_WIDTH_FULL - Constants.CANVAS_WIDTH
				|| centerX < 0 && ParallaxPhysicsEngine.currentX <= 0)) {
			if (centerX > Constants.CANVAS_WIDTH) {
				moveScreenX = Constants.CANVAS_WIDTH;
			} else if (centerX < 0) {
				moveScreenX = -Constants.CANVAS_WIDTH;
			}
		}

		if (!(centerY > Constants.CANVAS_HEIGHT
				&& ParallaxPhysicsEngine.currentY >= Constants.CANVAS_HEIGHT_FULL - Constants.CANVAS_HEIGHT
				|| centerY < 0 && ParallaxPhysicsEngine.currentY <= 0)) {
			if (centerY > Constants.CANVAS_HEIGHT) {
				moveScreenY = Constants.CANVAS_HEIGHT;
			} else if (centerY < 0) {
				moveScreenY = -Constants.CANVAS_HEIGHT;
			}
		}

		ParallaxPhysicsEngine.moveScreen(moveScreenX, moveScreenY);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (directionIsRight) {
			if (HP != HPCompare) {
				animationMoveRight.drawFlash(getX(), getY(), SPRITE_IMAGE_SIZE, SPRITE_IMAGE_SIZE);
				HPCompare = HP;
			} else {
				animationMoveRight.draw(getX(), getY());
			}
		} else {
			if (HP != HPCompare) {
				animationMoveLeft.drawFlash(getX(), getY(), SPRITE_IMAGE_SIZE, SPRITE_IMAGE_SIZE);
				HPCompare = HP;
			} else {
				animationMoveLeft.draw(getX(), getY());
			}
		}
		aim.render(container, graphics);
	}

}
