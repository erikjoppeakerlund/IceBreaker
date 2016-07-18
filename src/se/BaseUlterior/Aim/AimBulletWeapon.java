package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectSimple;

public abstract class AimBulletWeapon extends Aim {

	protected Image rifleImageRight = null;
	protected Image rifleImageLeft = null;
	protected int imageHeight;
	protected int imageWidth;
	private static final float IMAGE_SCALE = 0.26f;

	protected float startShotX;
	protected float startShotY;

	protected float startGunFireImageAtX;
	protected float startGunFireImageAtY;

	protected int shotRayFrames;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	protected GameObject pointBlank = null;

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
		wasJustShoot = true;
		armLengt -= BACK_FIRE;
		GameObject ricochet = new GameObjectSimple(new Circle(aimAtX, aimAtY, 29f).getPoints(), pointBlank);
		BreakingPoint.objsToAdd.add(ricochet);
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

	}

	protected void updateAim() {
		float xTarget = xGrip;
		float yTarget = yGrip;
		boolean notFound = true;
		int STEP = 4;
		while (notFound) {
			xTarget += arm.x * STEP;
			yTarget += arm.y * STEP;
			for (GameObject go : BreakingPoint.all) {
				if (go.contains(xTarget, yTarget) && !go.isBackgroundObj) {
					aimAtX = xTarget;
					aimAtY = yTarget;
					notFound = false;
					pointBlank = go;
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
	}

}
