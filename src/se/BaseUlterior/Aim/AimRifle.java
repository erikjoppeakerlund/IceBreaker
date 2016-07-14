package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimRifle extends AimBulletWeapon {

	// private Image rifleImageRight = null;
	// private Image rifleImageLeft = null;
	// private int imageHeight;
	// private int imageWidth;
	// private static final float IMAGE_SCALE = 0.26f;

	// public AimRifle() {
	// try {
	// rifleImageRight = new Image("res/img/GUNSrifle.png");
	// rifleImageLeft = new Image("res/img/GUNSrifle.png", true);
	// imageHeight = (int) (rifleImageRight.getHeight() * IMAGE_SCALE);
	// imageWidth = (int) (rifleImageRight.getWidth() * IMAGE_SCALE);
	// rifleImageLeft =
	// rifleImageLeft.getScaledCopy(IMAGE_SCALE).getFlippedCopy(false,
	// true);
	// rifleImageRight = rifleImageRight.getScaledCopy(IMAGE_SCALE);
	// rifleImageRight.setCenterOfRotation((imageWidth / 2), (imageHeight /
	// 2));
	// rifleImageLeft.setCenterOfRotation((imageWidth / 2), (imageHeight /
	// 2));
	//
	// } catch (SlickException e) {
	// e.printStackTrace();
	// }
	// }

	public AimRifle() {
		super("res/img/GUNSrifle.png");
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		graphics.setColor(Color.red);
		graphics.drawLine(x, y, aimAtX, aimAtY);

	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		updateAim();
	}

	@Override
	public void primaryPushed() {
		wasShoot();
	}

}
