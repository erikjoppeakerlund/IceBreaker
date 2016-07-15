package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;

public abstract class Aim {
	public abstract void primaryPushed();

	public abstract void primaryReleased();

	public abstract void secondaryPushed();

	public abstract void secondaryReleased();

	protected float x;
	protected float y;
	protected float spriteX;
	protected float spriteY;

	protected float angle;
	protected float dAngle;
	protected float START_ARM_LENGTH;

	protected float armLengt;

	protected boolean isRight = true;
	protected boolean wasJustSwitched = false;

	protected float cosX;
	protected float sinY;
	protected Animation gunFire = null;

	public Aim() {
		START_ARM_LENGTH = Constants.PERFERED_ARM_LENGTH;
		armLengt = START_ARM_LENGTH;
	}

	public Aim(float startArmLength) {
		START_ARM_LENGTH = startArmLength;
		armLengt = startArmLength;
	}

	public void setPosition(float x, float y) {
		this.spriteX = x;
		this.spriteY = y;
	}

	public void update(GameContainer container, int arg) {
		cosX = (float) Math.cos(angle);
		sinY = (float) Math.sin(angle);

		x = spriteX + (float) cosX * armLengt;
		y = spriteY + (float) sinY * armLengt;
		if ((angle + Math.PI / 2) > 0 && (angle + Math.PI / 2) < Math.PI) {
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

	public abstract void render(GameContainer container, Graphics graphics);

	public void setAngleToMouse(float angleToPoint) {
		dAngle = angleToPoint - angle;
		angle = angleToPoint;
	}

	public void cleanUp() {

	}

	public void onThisWasChoosen() {

	}

}
