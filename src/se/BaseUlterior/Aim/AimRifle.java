package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AimRifle extends AimBulletWeapon {

	private Image rifleImageRight = null;
	private Image rifleImageLeft = null;
	private int imageHeight;
	private int imageWidth;
	private static final float IMAGE_SCALE = 0.26f;

	public AimRifle() {
		try {
			rifleImageRight = new Image("res/img/GUNSrifle.png");
			rifleImageLeft = new Image("res/img/GUNSrifle.png", true);
			imageHeight = (int) (rifleImageRight.getHeight() * IMAGE_SCALE);
			imageWidth = (int) (rifleImageRight.getWidth() * IMAGE_SCALE);
			rifleImageLeft = rifleImageLeft.getScaledCopy(IMAGE_SCALE).getFlippedCopy(false, true);
			rifleImageRight = rifleImageRight.getScaledCopy(IMAGE_SCALE);
			rifleImageRight.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));
			rifleImageLeft.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void primaryPushed() {
		wasShoot();
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
	public void render(GameContainer container, Graphics graphics) {
		if (isRight) {
			rifleImageRight.setRotation((float) Math.toDegrees(angle));
			rifleImageRight.draw(x - imageWidth / 2, y - imageHeight / 2);
		} else {
			rifleImageLeft.setRotation((float) Math.toDegrees(angle) - (float) Math.PI);
			rifleImageLeft.draw(x - imageWidth / 2, y - imageHeight / 2);
		}
		graphics.setColor(Color.red);
		graphics.drawLine(x, y, aimAtX, aimAtY);

	}

}
