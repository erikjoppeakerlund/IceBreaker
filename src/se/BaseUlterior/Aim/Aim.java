package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * Sprite aim
 * 
 * @author Johan Akerlund
 */
public abstract class Aim {
	public abstract void primaryPushed();

	public abstract void primaryReleased();

	public abstract void secondaryPushed();

	public abstract void secondaryReleased();

	protected Vector2 arm;

	public Vector2 getArm() {
		return arm;
	}

	protected Vector2 grip;
	protected Vector2 aimStart;

	public float spriteX;
	public float spriteY;
	protected Entity pointBlank = null;

	protected float aimAtX;
	protected float aimAtY;

	public float angle;
	protected float START_ARM_LENGTH;

	protected float armLengt;
	private final int TURNING_SPEED = 3;

	protected boolean isRight = true;
	protected boolean wasJustSwitched = false;

	public static boolean mobile = Constants.START_MODE == Action.ACTION_MODE_CELL;

	protected Animation gunFire = null;
	protected String slug;
	protected double theta;

	protected float armLengthQuote = 1f;

	public String getSlug() {
		return slug;
	}

	public void setAimAt(float x, float y) {
		aimAtX = x;
		aimAtY = y;
	}

	public Aim() {
		START_ARM_LENGTH = Constants.PERFERED_ARM_LENGTH;
		armLengt = START_ARM_LENGTH;
		init();
	}

	public Aim(float startArmLength) {
		START_ARM_LENGTH = startArmLength;
		armLengt = startArmLength;
		init();

	}

	private void init() {
		arm = new Vector2(0f);
		grip = new Vector2(0f).scale(armLengt);
		aimStartLengt = START_ARM_LENGTH * 3;
		aimStart = new Vector2(0f).scale(aimStartLengt);
		theta = arm.getTheta();
	}

	protected float aimStartLengt;

	public void setPosition(float x, float y) {
		spriteX = x;
		spriteY = y;
	}

	public void update(GameContainer container, int arg) {

		theta = arm.getTheta();

		grip.setTheta(theta);
		aimStart.setTheta(theta);

		if (!mobile) {
			updateWasJustSwitched();
		}
	}

	private void updateWasJustSwitched() {
		if ((theta >= 0 && (theta) < 90) || (theta > 270)) {
			if (!isRight) {
				wasJustSwitched = true;
			}
			isRight = true;
		} else {
			if (isRight) {
				wasJustSwitched = true;
			}
			isRight = false;
		}
		wasJustSwitched = false;

	}

	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.setLineWidth(4);
		graphics.drawRect(spriteX + aimStart.x, spriteY + aimStart.y, 9, 9);
		graphics.resetLineWidth();

	}

	public void renderFlashed(GameContainer container, Graphics graphics) {
		render(container, graphics);
	}

	public void setAngleToMouse(float angleToPoint) {
		arm.setTheta(angleToPoint);
	}

	public void cleanUp() {
		primaryReleased();
	}

	public void onThisWasChoosen() {
		updateWasJustSwitched();
	}

	public void angleUp() {
		if (!isRight) {
			if (theta < 260)
				arm.add(TURNING_SPEED);
		} else {
			if (theta > 280 || theta < 90)
				arm.sub(TURNING_SPEED);
		}
	}

	public void angleDown() {
		if (isRight) {
			if (theta > 270 || theta < 80)
				arm.add(TURNING_SPEED);
		} else {
			if (theta > 100)
				arm.sub(TURNING_SPEED);
		}

	}

	public void setIsRight(boolean is) {
		if (is != isRight) {
			arm.set(-arm.x, arm.y);
		}
		isRight = is;
	}

	public int getCurrentThete() {
		return (int) theta;
	}

	public void setPointBlank(Entity go) {
		this.pointBlank = go;
	}

	protected void updateAim() {

		float xTarget = spriteX + grip.x;
		float yTarget = spriteY + grip.y;
		boolean notFound = true;
		int STEP = 8;
		while (notFound) {
			xTarget += arm.x * STEP;
			yTarget += arm.y * STEP;
			for (Entity go : ParallaxPhysicsEngine.all) {
				if (go.contains(xTarget, yTarget) && !(go.piercable)) {
					aimAtX = xTarget;
					aimAtY = yTarget;
					notFound = false;
					if (pointBlank != go) {
						if (pointBlank != null && !pointBlank.isRotatingObject) {
							pointBlank.forceUpdate = false;
						}
						go.forceUpdate = true;
					}
					pointBlank = go;
					break;
				}
			}
		}
	}

	public abstract void shoot();
}
