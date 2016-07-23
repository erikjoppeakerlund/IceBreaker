package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimTurret extends AimBulletWeapon {

	public AimTurret() {
		super("res/img/turret.png", IMAGE_SCALE_STANDARD);

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

	}

}
