package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Utils.UlteriorUtils;

public class AimRifle extends Aim {

	private float aimAtX;
	private float aimAtY;

	private final int EXPLOTION_SIZE = 41;

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
		UlteriorUtils.removeGround(aimAtX, aimAtY, EXPLOTION_SIZE, null);
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
		if (isRight) {
			rifleImageRight.setRotation((float) Math.toDegrees(angle));
			rifleImageRight.draw(x - imageWidth / 2, y - imageHeight / 2);
		} else {
			rifleImageLeft.setRotation((float) Math.toDegrees(angle) - (float) Math.PI);
			rifleImageLeft.draw(x - imageWidth / 2, y - imageHeight / 2);
		}
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
					aimAtX = xTarget;
					aimAtY = yTarget;
					notFound = false;
					break;
				}
			}
		}
		graphics.setLineWidth(1);
		graphics.drawLine(x, y, xTarget, yTarget);
	}

}
