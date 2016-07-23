package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimTurret extends AimBulletWeapon {
	private boolean animationsIsDrawn = false;

	public AimTurret(Animation gunFire) {
		super("res/img/turret.png", IMAGE_SCALE_STANDARD);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		recoilPower = 0.05f;

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		if (isRight) {
			rifleImageRight.setRotation((float) arm.getTheta());
			rifleImageRight.draw(spriteX - imageWidth / 2, spriteY - imageHeight / 2);
		} else {
			rifleImageLeft.setRotation((float) (arm.getTheta()));
			rifleImageLeft.draw(spriteX - imageWidth / 2, spriteY - imageHeight / 2);
		}
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) arm.getTheta() + 65);
			gunFire.draw(gunFireStartAtX - gunFireFrameWidth / 2, gunFireStartAtY - gunFireFrameHeight / 2);
		}

	}

	@Override
	public void update(GameContainer container, int arg) {

		xGrip = getXAimFromDistanceAt(armLengt);
		yGrip = getYAimFromDistanceAt(armLengt);

		xAim = getXAimFromDistanceAt(armLengt * 3);
		yAim = getYAimFromDistanceAt(armLengt * 3);
		startShotX = getXAimFromDistanceAt(armLengt);
		startShotY = getYAimFromDistanceAt(armLengt);

		gunFireStartAtX = getXAimFromDistanceAt(armLengt + gunFireFrameWidth / 2);
		gunFireStartAtY = getYAimFromDistanceAt(armLengt + gunFireFrameWidth / 2);

		if (wasJustShoot && armLengt < START_ARM_LENGTH) {
			armLengt += 3f;
			if (recoil != null) {
				recoil.setCenterX(xGrip);
				recoil.setCenterY(yGrip);
			}
		} else if (wasJustShoot) {
			wasJustShoot = false;
		}

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

}
