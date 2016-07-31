package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.EntityRicochet;
import se.BaseUlterior.Geom.Vector2;
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

	protected Vector2 startShot;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	protected float weight = 1.0f;
	private float imageScale;

	protected float recoilPower = 0;
	private float RECOIL_RETURN_FACTOR = 3f;

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

	protected void initFireVectors() {
		startShotLength = START_ARM_LENGTH + imageWidth / 2;
		gunFireStartAtLength = START_ARM_LENGTH - BACK_FIRE + gunFireFrameWidth / 2 + imageWidth / 2;

		startShot = new Vector2(0f).scale(startShotLength);
		gunFireStartAt = new Vector2(0f).scale(gunFireStartAtLength);
	}

	private float startShotLength;
	protected float gunFireStartAtLength;

	protected final int EXPLOTION_SIZE = 70;

	protected boolean wasJustShoot = false;
	protected final float BACK_FIRE = 14f;

	protected Vector2 gunFireStartAt;
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

		armLengthQuote = armLengt / START_ARM_LENGTH;

		Entity ricochet = new EntityRicochet(pointBlank, spriteX + gunFireStartAt.x, spriteY + gunFireStartAt.y, aimAtX,
				aimAtY, weight, damage);
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

		startShot.setTheta(theta);
		gunFireStartAt.setTheta(theta);
		if (wasJustShoot && armLengt < START_ARM_LENGTH) {

			armLengt += RECOIL_RETURN_FACTOR;

			armLengthQuote = armLengt / START_ARM_LENGTH;

			aimStart.normalise().scale(armLengthQuote * aimStartLengt);
			grip.normalise().scale(armLengthQuote * armLengt);
			gunFireStartAt.normalise().scale(armLengthQuote * gunFireStartAtLength);
			startShot.normalise().scale(armLengthQuote * startShotLength);

		} else if (wasJustShoot) {
			wasJustShoot = false;

		}

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (isRight) {
			rifleImageRight.setRotation((float) theta);
			// rifleImageRight.draw(xGrip - imageWidth / 2, yGrip - imageHeight
			// / 2);
			rifleImageRight.draw((spriteX + grip.x) - imageWidth / 2, (spriteY + grip.y) - imageHeight / 2);
		} else {
			rifleImageLeft.setRotation((float) (theta));
			// rifleImageLeft.draw(xGrip - imageWidth / 2, yGrip - imageHeight /
			// 2);
			rifleImageLeft.draw((spriteX + grip.x) - imageWidth / 2, (spriteY + grip.y) - imageHeight / 2);
		}

	}

}
