package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class AimBulletWeapon extends Aim {

	private Image rifleImageRight = null;
	private Image rifleImageLeft = null;
	private int imageHeight;
	private int imageWidth;
	private static final float IMAGE_SCALE = 0.26f;

	public AimBulletWeapon(String pathToImage) {
		try {
			rifleImageRight = new Image(pathToImage);
			rifleImageLeft = new Image(pathToImage, true);
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

	protected float aimAtX;
	protected float aimAtY;

	protected final int EXPLOTION_SIZE = 41;

	protected boolean wasJustShoot = false;
	protected final float BACK_FIRE = 14f;

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
		wasJustShoot = true;
		armLengt -= BACK_FIRE;
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
		if (wasJustShoot && armLengt < START_ARM_LENGTH) {
			armLengt += 3f;
		} else if (wasJustShoot) {
			wasJustShoot = false;
		}
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

	}

}
