package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectRicochet;

public class AIAimTurret extends AIAim {
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
	private Animation gunFire;

	protected float damage = 20;

	public AIAimTurret(Animation gunFire, float armLengt) {
		super(armLengt);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) arm.getTheta() + 65);
			gunFire.draw(gunFireStartAtX - gunFireFrameWidth / 2, gunFireStartAtY - gunFireFrameHeight / 2);
		}

	}

	@Override
	public void update(GameContainer container, int arg) {

		xGrip = getXAimFromDistanceAt(armLengt);
		yGrip = getYAimFromDistanceAt(armLengt);
		gunFireStartAtX = getXAimFromDistanceAt(armLengt + gunFireFrameWidth / 2);
		gunFireStartAtY = getYAimFromDistanceAt(armLengt + gunFireFrameWidth / 2);

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

		if (wasJustShoot) {
			wasJustShoot = false;
		}

	}

	protected void wasShoot() {
		wasJustShoot = true;
		GameObject ricochet = new GameObjectRicochet(pointBlank, gunFireStartAtX, gunFireStartAtY, aimAtX, aimAtY,
				weight, damage);
		IceBreaker.objsToAdd.add(ricochet);
	}

	@Override
	public void shoot() {
		updateAim();
		wasShoot();
	}

}
