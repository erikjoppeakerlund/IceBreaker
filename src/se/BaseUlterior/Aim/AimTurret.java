package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectRicochet;

public class AimTurret extends Aim {
	private boolean animationsIsDrawn = false;

	protected float startShotX;
	protected float startShotY;

	protected float startGunFireImageAtX;
	protected float startGunFireImageAtY;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	protected final int EXPLOTION_SIZE = 70;

	protected boolean wasJustShoot = false;
	protected final float BACK_FIRE = 14f;
	protected float gunFireStartAtX;
	protected float gunFireStartAtY;

	protected float weight = 1.0f;

	public AimTurret(Animation gunFire) {
		// super("res/img/turret.png", IMAGE_SCALE_STANDARD * 0.91f);
		this.gunFire = gunFire;
		// gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		// gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		// recoilPower = 0.05f;

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		// if (isRight) {
		// rifleImageRight.setRotation((float) arm.getTheta());
		// rifleImageRight.draw(spriteX - imageWidth / 2, spriteY - imageHeight
		// / 2);
		// } else {
		// rifleImageLeft.setRotation((float) (arm.getTheta()));
		// rifleImageLeft.draw(spriteX - imageWidth / 2, spriteY - imageHeight /
		// 2);
		// }
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) arm.getTheta() + 65);
			gunFire.draw(gunFireStartAtX - gunFireFrameWidth / 2, gunFireStartAtY - gunFireFrameHeight / 2);
		}

	}

	@Override
	public void update(GameContainer container, int arg) {

		xGrip = getXAimFromDistanceAt(armLengt);
		yGrip = getYAimFromDistanceAt(armLengt);

		xAim = getXAimFromDistanceAt(armLengt * 3);
		yAim = getYAimFromDistanceAt(armLengt * 3);
		startShotX = getXAimFromDistanceAt(armLengt);
		startShotY = getYAimFromDistanceAt(armLengt);

		gunFireStartAtX = getXAimFromDistanceAt(armLengt + gunFireFrameWidth / 2);
		gunFireStartAtY = getYAimFromDistanceAt(armLengt + gunFireFrameWidth / 2);

		if (wasJustShoot && armLengt < START_ARM_LENGTH) {
			armLengt += 3f;

		} else if (wasJustShoot) {
			wasJustShoot = false;
		}

		if (wasJustShoot) {
			gunFire.setCurrentFrame(0);
			gunFire.update(arg);
			animationsIsDrawn = true;
		} else if (animationsIsDrawn) {
			if (gunFire.getFrame() < gunFire.getFrameCount() - 1) {
				gunFire.update(arg);
			} else {
				animationsIsDrawn = false;
			}
		}
	}

	@Override
	public void renderFlashed(GameContainer container, Graphics graphics) {
		super.renderFlashed(container, graphics);
		// if (isRight) {
		// rifleImageRight.setRotation((float) arm.getTheta());
		// rifleImageRight.draw(spriteX - imageWidth / 2, spriteY - imageHeight
		// / 2, Color.red);
		// } else {
		// rifleImageLeft.setRotation((float) (arm.getTheta()));
		// rifleImageLeft.draw(spriteX - imageWidth / 2, spriteY - imageHeight /
		// 2, Color.red);
		// }
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) arm.getTheta() + 65);
			gunFire.draw(gunFireStartAtX - gunFireFrameWidth / 2, gunFireStartAtY - gunFireFrameHeight / 2);
		}
	}

	protected void wasShoot() {
		wasJustShoot = true;
		armLengt -= BACK_FIRE;
		GameObject ricochet = new GameObjectRicochet(pointBlank, gunFireStartAtX, gunFireStartAtY, aimAtX, aimAtY,
				weight);
		IceBreaker.objsToAdd.add(ricochet);
		// recoil = new GameObjectExplosion(new Circle(xGrip, yGrip,
		// START_ARM_LENGTH * 1.5f).getPoints(), recoilPower,
		// Color.transparent, true);
		// IceBreaker.objsToAdd.add(recoil);
	}

	@Override
	public void primaryPushed() {
		// TODO Auto-generated method stub

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
	public void shoot() {
		// TODO Auto-generated method stub

	}

}
