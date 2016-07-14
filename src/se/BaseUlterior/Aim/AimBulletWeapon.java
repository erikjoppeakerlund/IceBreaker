package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class AimBulletWeapon extends Aim {

	protected float aimAtX;
	protected float aimAtY;

	protected final int EXPLOTION_SIZE = 41;

	@Override
	public void primaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void primaryReleased() {
		// TODO Auto-generated method stub

	}

	protected void wasShoot() {
		UlteriorUtils.removeGround(aimAtX, aimAtY, EXPLOTION_SIZE, null);
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
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		float xTarget = x;
		float yTarget = y;
		boolean notFound = true;
		int STEP = 10;
		while (notFound) {
			xTarget += Math.cos(angle) * STEP;
			yTarget += Math.sin(angle) * STEP;
			for (GameObject go : BreakingPoint.all) {
				if (go.contains(xTarget, yTarget) && !go.isBackgroundObj()) {
					aimAtX = xTarget;
					aimAtY = yTarget;
					notFound = false;
					break;
				}
			}
		}

	}

}
