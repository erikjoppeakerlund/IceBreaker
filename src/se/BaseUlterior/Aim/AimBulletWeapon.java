package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.EntityRicochet;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * Abstract aim class which handles all sub aim classes which do not produce any
 * sub instance of se.BaseUlterior.GameObjectMateriaFirm
 * 
 * @author Johan Akerlund
 */
public abstract class AimBulletWeapon extends Aim {

	private Image rifleImageRight = null;
	private Image rifleImageLeft = null;
	private int imageHeight;
	private int imageWidth;
	public static final float IMAGE_SCALE_STANDARD = 0.26f;

	protected float startShotX;
	protected float startShotY;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	protected float weight = 1.0f;
	private float imageScale;

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
			rifleImageRight = new Image(pathToImage).getScaledCopy(imageScale);
			rifleImageLeft = new Image(pathToImage, true).getScaledCopy(imageScale).getFlippedCopy(false, true);
			imageHeight = (int) (rifleImageRight.getHeight());
			imageWidth = (int) (rifleImageRight.getWidth());
			rifleImageRight.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));
			rifleImageLeft.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));
			rifleImageRight.bind();
			rifleImageLeft.bind();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	protected final int EXPLOTION_SIZE = 70;

	protected boolean wasJustShoot = false;
	protected final float BACK_FIRE = 14f;
	protected float gunFireStartAtX;
	protected float gunFireStartAtY;
	protected float damage = 20;

	@Override
	public void primaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void primaryReleased() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shoot() {
		wasShoot();
	}

	protected void wasShoot() {
		wasJustShoot = true;
		armLengt -= BACK_FIRE;
		Entity ricochet = new EntityRicochet(pointBlank, gunFireStartAtX, gunFireStartAtY, aimAtX, aimAtY, weight,
				damage);
		ParallaxPhysicsEngine.objsToAdd.add(ricochet);
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
