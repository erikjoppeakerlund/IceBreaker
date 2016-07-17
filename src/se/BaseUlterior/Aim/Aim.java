package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Geom.Vector2;

public abstract class Aim {
	public abstract void primaryPushed();

	public abstract void primaryReleased();

	public abstract void secondaryPushed();

	public abstract void secondaryReleased();

	protected Vector2 arm;

	protected float xGrip;
	protected float yGrip;
	protected float xAim;
	protected float yAim;
	public float spriteX;
	public float spriteY;

	public float angle;
	protected float dAngle;
	protected float START_ARM_LENGTH;

	protected float armLengt;
	private int turningSpeed = 3;

	protected boolean isRight = true;
	protected boolean wasJustSwitched = false;

	protected boolean mobile = false;

	protected Animation gunFire = null;

	public Aim() {
		arm = new Vector2(0.0f);
		START_ARM_LENGTH = Constants.PERFERED_ARM_LENGTH;
		armLengt = START_ARM_LENGTH;
	}

	public Aim(float startArmLength) {
		arm = new Vector2(0.0f);
		START_ARM_LENGTH = startArmLength;
		armLengt = startArmLength;
	}

	public void setPosition(float x, float y) {
		spriteX = x;
		spriteY = y;
	}

	public void update(GameContainer container, int arg) {

		xGrip = getXAimFromDistanceAt(armLengt);
		yGrip = getYAimFromDistanceAt(armLengt);

		xAim = getXAimFromDistanceAt(armLengt * 3);
		yAim = getYAimFromDistanceAt(armLengt * 3);

		if (!mobile) {
			if ((arm.getTheta() > 0 && (arm.getTheta()) < 90) || (arm.getTheta() > 270)) {
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
	}

	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.setLineWidth(4);
		graphics.drawRect(xAim, yAim, 9, 9);
		graphics.resetLineWidth();

	}

	public void setAngleToMouse(float angleToPoint) {
		arm.setTheta(angleToPoint);
	}

	public void cleanUp() {

	}

	public void onThisWasChoosen() {

	}

	protected float getXAimFromDistanceAt(float distance) {
		return spriteX + arm.x * distance;
	}

	protected float getYAimFromDistanceAt(float distance) {
		return spriteY + arm.y * distance;
	}

	public void angleUp() {
		if (!isRight) {
			if (arm.getTheta() < 260)
				arm.add(turningSpeed);
		} else {
			if (arm.getTheta() > 280 || arm.getTheta() < 90)
				arm.sub(turningSpeed);
		}
	}

	public void angleDown() {
		if (isRight) {
			if (arm.getTheta() > 270 || arm.getTheta() < 80)
				arm.add(turningSpeed);
		} else {
			if (arm.getTheta() > 100)
				arm.sub(turningSpeed);
		}

	}

	public void setIsRight(boolean is) {
		if (is != isRight) {
			arm.set(-arm.x, arm.y);
		}
		isRight = is;
	}

}
