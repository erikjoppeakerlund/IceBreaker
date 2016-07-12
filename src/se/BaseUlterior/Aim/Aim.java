package se.BaseUlterior.Aim;

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
	protected final float START_ARM_LENGTH = Constants.SPRITE_RADIUS * 2.6f;

	protected float armLengt = START_ARM_LENGTH;

	public void setPosition(float x, float y) {
		this.spriteX = x;
		this.spriteY = y;
	}

	// might want to add game container here at leas...
	public abstract void update();

	public void setAngleToMouse(float angleToPoint) {
		this.angle = angleToPoint;
	}

}
