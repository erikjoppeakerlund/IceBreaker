package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.EntityRicochet;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * Shotgun Aim
 * 
 * @author Johan Akerlund
 */
public class AimShotGun extends AimBulletWeapon {
	private boolean animationsIsDrawn = false;

	public AimShotGun(Animation gunFire) {
		super("res/img/GUNSshotGun.png", 0.4f);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		recoilPower = 0.2f;
		damage = Constants.WEAPON_EFFECT_SHOTGUN;
		slug = "SHOT GUN";
		initFireVectors();
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
			gunFire.getCurrentFrame().setRotation((float) theta + 65);
			gunFire.draw((spriteX + gunFireStartAt.x) - gunFireFrameWidth / 2,
					(spriteY + gunFireStartAt.y) - gunFireFrameHeight / 2);
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
			randomAngle.setTheta(theta + (Math.random() * 28f - 14f));
			float xTarget = spriteX + grip.x;
			float yTarget = spriteY + grip.y;
			notFound = true;
			while (notFound) {
				xTarget += randomAngle.x * STEP;
				yTarget += randomAngle.y * STEP;
				for (Entity go : ParallaxPhysicsEngine.all) {
					if (go.contains(xTarget, yTarget) && !go.piercable) {
						Entity ricochet = new EntityRicochet(go, spriteX + gunFireStartAt.x, spriteY + gunFireStartAt.y,
								xTarget, yTarget, 0.49f, damage);
						ParallaxPhysicsEngine.objsToAdd.add(ricochet);
						notFound = false;
						break;
					}
				}
			}
		}
	}
}
