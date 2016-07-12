package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	protected final float START_ARM_LENGTH = Constants.SPRITE_RADIUS * 2.3f;

	protected float armLengt = START_ARM_LENGTH;

	protected boolean isRight = true;
	protected boolean wasJustSwitched = false;

	public void setPosition(float x, float y) {
		this.spriteX = x;
		this.spriteY = y;
	}

	protected abstract void updateFulfill(GameContainer container, int arg);

	public void update(GameContainer container, int arg) {
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
		x = spriteX + (float) Math.cos(angle) * armLengt;
		y = spriteY + (float) Math.sin(angle) * armLengt;
		updateFulfill(container, arg);
		wasJustSwitched = false;
	}

	public void render(GameContainer container, Graphics graphics) throws SlickException {
		renderFullfill(container, graphics);
	}

	protected abstract void renderFullfill(GameContainer container, Graphics graphics) throws SlickException;

	public void setAngleToMouse(float angleToPoint) {
		dAngle = angleToPoint - angle;
		this.angle = angleToPoint;
	}

}
