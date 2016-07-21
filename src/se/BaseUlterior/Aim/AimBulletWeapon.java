package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectExplosion;
import se.BaseUlterior.GameObject.GameObjectRicochet;

/**
 * Abstract aim class which handles all sub aim classes which do not produce any
 * sub instance of se.BaseUlterior.GameObjectMateriaFirm
 * 
 * @author Johan Akerlund
 */
public abstract class AimBulletWeapon extends Aim {

	protected Image rifleImageRight = null;
	protected Image rifleImageLeft = null;
	protected int imageHeight;
	protected int imageWidth;
	protected static final float IMAGE_SCALE_STANDARD = 0.26f;

	protected float startShotX;
	protected float startShotY;

	protected float startGunFireImageAtX;
	protected float startGunFireImageAtY;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	protected GameObject pointBlank = null;

	protected int shotRayFrames;

	protected float weight = 1.0f;
	private float imageScale;
	protected GameObjectExplosion recoil;

	protected float recoilPower = 0;

	public AimBulletWeapon(String pathToImage, float imageScale) {
		this.imageScale = imageScale;
		init(pathToImage);
	}

	public AimBulletWeapon(String pathToImage, float startArmLength, float imageScale) {
		super(startArmLength);
		this.imageScale = imageScale;
		init(pathToImage);
	}

	private void init(String pathToImage) {
		xGrip = -60;
		yGrip = -60;
		try {
			rifleImageRight = new Image(pathToImage);
			rifleImageLeft = new Image(pathToImage, true);
			imageHeight = (int) (rifleImageRight.getHeight() * imageScale);
			imageWidth = (int) (rifleImageRight.getWidth() * imageScale);
			rifleImageLeft = rifleImageLeft.getScaledCopy(imageScale).getFlippedCopy(false, true);
			rifleImageRight = rifleImageRight.getScaledCopy(imageScale);
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
		GameObject ricochet = new GameObjectRicochet(new Circle(aimAtX, aimAtY, 29f).getPoints(), pointBlank,
				gunFireStartAtX, gunFireStartAtY, aimAtX, aimAtY, weight);
		IceBreaker.objsToAdd.add(ricochet);
		recoil = new GameObjectExplosion(new Circle(xGrip, yGrip, START_ARM_LENGTH * 1.5f).getPoints(), recoilPower,
				Color.transparent);
		IceBreaker.objsToAdd.add(recoil);
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
			// if (recoil != null) {
			// recoil.setCenterX(xGrip);
			// recoil.setCenterY(yGrip);
			// }
		} else if (wasJustShoot) {
			wasJustShoot = false;
		}

	}

	protected void updateAim() {
		float xTarget = xGrip;
		float yTarget = yGrip;
		boolean notFound = true;
		int STEP = 8;
		while (notFound) {
			xTarget += arm.x * STEP;
			yTarget += arm.y * STEP;
			for (GameObject go : IceBreaker.all) {
				if (go.contains(xTarget, yTarget) && !go.isBackgroundObj) {
					aimAtX = xTarget;
					aimAtY = yTarget;
					notFound = false;
					if (pointBlank != go) {
						if (pointBlank != null && !pointBlank.isRotatingObject) {
							pointBlank.forceUpdate = false;
						}
						go.forceUpdate = true;
					}
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
