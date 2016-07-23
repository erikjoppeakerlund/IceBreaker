package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimTurret extends AimBulletWeapon {

	public AimTurret() {
		super("res/img/turret.png", IMAGE_SCALE_STANDARD);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (isRight) {
			rifleImageRight.setRotation((float) arm.getTheta());
			rifleImageRight.draw(spriteX - imageWidth / 2, spriteY - imageHeight / 2);
		} else {
			rifleImageLeft.setRotation((float) (arm.getTheta()));
			rifleImageLeft.draw(spriteX - imageWidth / 2, spriteY - imageHeight / 2);
		}
	}

}
