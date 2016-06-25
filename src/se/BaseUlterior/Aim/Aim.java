package se.BaseUlterior.Aim;

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
