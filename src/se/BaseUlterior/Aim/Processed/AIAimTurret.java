package se.BaseUlterior.Aim.Processed;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.EntityRicochet;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

public class AIAimTurret extends AIAim {
	private boolean animationsIsDrawn = false;

	protected int gunFireFrameWidth;
	protected int gunFireFrameHeight;

	protected final int EXPLOTION_SIZE = 70;

	protected boolean wasJustShoot = false;
	protected final float BACK_FIRE = 14f;

	protected float weight = 1.0f;
	private Animation gunFire;

	protected float damage = 20;

	public AIAimTurret(Animation gunFire, float armLengt) {
		super(armLengt);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		initFireVectors();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) theta + 65);
			gunFire.draw((spriteX + gunFireStartAt.x) - gunFireFrameWidth / 2,
					(spriteY + gunFireStartAt.y) - gunFireFrameHeight / 2, Color.red);
		}

	}

	private Vector2 gunFireStartAt;
	private float gunFireStartAtLength;

	protected void initFireVectors() {
		gunFireStartAtLength = armLengt + gunFireFrameWidth / 2;

		gunFireStartAt = new Vector2(0f).scale(gunFireStartAtLength);
	}

	@Override
	public void update(GameContainer container, int arg) {

		super.update(container, arg);

		gunFireStartAt.setTheta(theta);

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
		Entity ricochet = new EntityRicochet(pointBlank, (spriteX + gunFireStartAt.x), (spriteY + gunFireStartAt.y),
				aimAtX, aimAtY, weight, damage);
		ParallaxPhysicsEngine.objsToAdd.add(ricochet);
	}

	@Override
	public void shoot() {
		updateAim();
		wasShoot();
	}

}
