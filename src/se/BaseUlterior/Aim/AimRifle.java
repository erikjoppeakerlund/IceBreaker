package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;

public class AimRifle extends Aim {

	@Override
	public void primaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void primaryReleased() {
		// TODO Auto-generated method stub

	}

	@Override
	public void secondaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void secondaryReleased() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateFulfill(GameContainer container, int arg) {
	}

	@Override
	protected void renderFullfill(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.red);
		float xTarget = x;
		float yTarget = y;
		boolean notFound = true;
		int STEP = 10;
		while (notFound) {
			xTarget += Math.cos(angle) * STEP;
			yTarget += Math.sin(angle) * STEP;
			for (GameObject go : BreakingPoint.all) {
				if (go.contains(xTarget, yTarget) && !go.isBackgroundObj()) {
					notFound = false;
					break;
				}
			}
		}
		graphics.drawLine(x, y, xTarget, yTarget);
	}

}
