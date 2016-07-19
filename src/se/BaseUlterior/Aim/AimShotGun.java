package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectRicochet;
import se.BaseUlterior.Geom.Vector2;

public class AimShotGun extends AimBulletWeapon {
	private boolean animationsIsDrawn = false;

	public AimShotGun(Animation gunFire) {
		super("res/img/GUNSshotGun.png", 0.4f);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		if (wasJustShoot) {
			gunFire.setCurrentFrame(0);
			gunFire.update(arg);
			animationsIsDrawn = true;
		} else if (animationsIsDrawn) {
			if (gunFire.getFrame() < gunFire.getFrameCount() - 1) {
				gunFire.update(arg);
			} else {
				animationsIsDrawn = false;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) arm.getTheta() + 65);
			gunFire.draw(gunFireStartAtX - gunFireFrameWidth / 2, gunFireStartAtY - gunFireFrameHeight / 2);
		}

	}

	@Override
	public void primaryPushed() {
		wasShoot();
	}

	@Override
	protected void wasShoot() {
		wasJustShoot = true;
		armLengt -= BACK_FIRE * 2f;

		int STEP = 8;
		int nrOfShots = 5;
		boolean notFound = true;
		for (int i = 0; i < nrOfShots; i++) {
			Vector2 randomAngle = arm.copy();
			randomAngle.setTheta(arm.getTheta() + (Math.random() * 28f - 14f));
			float xTarget = xGrip;
			float yTarget = yGrip;
			notFound = true;
			while (notFound) {
				xTarget += randomAngle.x * STEP;
				yTarget += randomAngle.y * STEP;
				for (GameObject go : BreakingPoint.all) {
					if (go.contains(xTarget, yTarget) && !go.isBackgroundObj) {
						GameObject ricochet = new GameObjectRicochet(new Circle(xTarget, yTarget, 29f).getPoints(), go,
								gunFireStartAtX, gunFireStartAtY, xTarget, yTarget, 0.25f);
						BreakingPoint.objsToAdd.add(ricochet);
						notFound = false;
						break;
					}
				}
			}
		}
	}
}
