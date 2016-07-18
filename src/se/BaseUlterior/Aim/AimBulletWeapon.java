package se.BaseUlterior.Aim;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;

public abstract class AimBulletWeapon extends Aim {

	protected Image rifleImageRight = null;
	protected Image rifleImageLeft = null;
	protected int imageHeight;
	protected int imageWidth;
	private static final float IMAGE_SCALE = 0.26f;
	private static final float SMOKE_LENGTH = 41f;

	protected float startShotX;
	protected float startShotY;

	protected float startGunFireImageAtX;
	protected float startGunFireImageAtY;

	protected int shotRayFrames;
	protected ArrayList<float[]> explostions;
	protected ArrayList<float[]> rays;

	private Color rayColor = Color.darkGray;

	private final int WALL_HIT_EXPLOTION_SIZE = 10;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	public AimBulletWeapon(String pathToImage) {
		init(pathToImage);
	}

	public AimBulletWeapon(String pathToImage, float startArmLength) {
		super(startArmLength);
		init(pathToImage);
	}

	private void init(String pathToImage) {
		xGrip = -60;
		yGrip = -60;
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
		rays = new ArrayList<>();
		explostions = new ArrayList<>();
	}

	protected float aimAtX;
	protected float aimAtY;

	protected final int EXPLOTION_SIZE = 70;

	protected boolean wasJustShoot = false;
	protected final float BACK_FIRE = 14f;
	protected float gunFireStartAtX;
	protected float gunFireStartAtY;

	@Override
	public void primaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void primaryReleased() {
		// TODO Auto-generated method stub

	}

	protected void wasShoot() {
		updateAim();
		// UlteriorUtils.removeGround(aimAtX, aimAtY, EXPLOTION_SIZE, null);

		wasJustShoot = true;
		armLengt -= BACK_FIRE;
		explostions.add(new float[] { aimAtX, aimAtY, SMOKE_LENGTH / 2 });
		rays.add(new float[] { gunFireStartAtX, gunFireStartAtY, aimAtX, aimAtY, SMOKE_LENGTH });
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
		startShotX = getXAimFromDistanceAt(armLengt + imageWidth / 2);
		startShotY = getYAimFromDistanceAt(armLengt + imageWidth / 2);

		gunFireStartAtX = getXAimFromDistanceAt(armLengt + gunFireFrameWidth / 2 + imageWidth / 2);
		gunFireStartAtY = getYAimFromDistanceAt(armLengt + gunFireFrameWidth / 2 + imageWidth / 2);

		if (wasJustShoot && armLengt < START_ARM_LENGTH) {
			armLengt += 3f;
		} else if (wasJustShoot) {
			wasJustShoot = false;
		}
		if (!rays.isEmpty()) {
			Iterator<float[]> it = rays.iterator();
			while (it.hasNext()) {
				float[] ray = it.next();
				if (ray[4] < 1) {
					it.remove();
				}
				ray[4]--;
			}
		}
		if (!explostions.isEmpty()) {
			Iterator<float[]> it = explostions.iterator();
			while (it.hasNext()) {
				float[] expl = it.next();
				if (expl[2] < 1) {
					it.remove();
				}
				expl[2]--;
			}
		}

	}

	protected void updateAim() {
		float xTarget = xGrip;
		float yTarget = yGrip;
		boolean notFound = true;
		int STEP = 10;
		while (notFound) {
			xTarget += arm.x * STEP;
			yTarget += arm.y * STEP;
			for (GameObject go : BreakingPoint.all) {
				if (go.contains(xTarget, yTarget) && !go.isBackgroundObj) {
					aimAtX = xTarget;
					aimAtY = yTarget;
					notFound = false;
					break;
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (isRight) {
			rifleImageRight.setRotation((float) arm.getTheta());
			rifleImageRight.draw(xGrip - imageWidth / 2, yGrip - imageHeight / 2);
		} else {
			rifleImageLeft.setRotation((float) (arm.getTheta()));
			rifleImageLeft.draw(xGrip - imageWidth / 2, yGrip - imageHeight / 2);
		}
		graphics.setLineWidth(3);
		if (!rays.isEmpty()) {
			for (float[] ray : rays) {

				rayColor.a = ray[4] / SMOKE_LENGTH;
				graphics.setColor(rayColor);
				graphics.drawLine(ray[0], ray[1], ray[2], ray[3]);
			}
		}
		if (!explostions.isEmpty()) {
			for (float[] expl : explostions) {
				graphics.setColor(Color.white);
				graphics.fillRect(expl[0] - WALL_HIT_EXPLOTION_SIZE / 2, expl[1] - WALL_HIT_EXPLOTION_SIZE / 2,
						WALL_HIT_EXPLOTION_SIZE, WALL_HIT_EXPLOTION_SIZE);
			}
		}
		graphics.resetLineWidth();
	}

}
