package se.BaseUlterior.Aim.Processed;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

public abstract class AIAim {

	protected float armLengt;
	protected float xAim;
	protected float yAim;
	public float spriteX;
	public float spriteY;
	protected Entity pointBlank = null;

	protected float xGrip;
	protected float yGrip;

	protected Vector2 arm;

	public Vector2 getArm() {
		return arm;
	}

	public AIAim(float armLengt) {
		this.armLengt = armLengt;
		arm = new Vector2(0.0f);
	}

	protected float aimAtX;
	protected float aimAtY;

	protected void updateAim() {
		float xTarget = xGrip;
		float yTarget = yGrip;
		boolean notFound = true;
		int STEP = 8;
		while (notFound) {
			xTarget += arm.x * STEP;
			yTarget += arm.y * STEP;
			for (Entity go : ParallaxPhysicsEngine.all) {
				if (go.contains(xTarget, yTarget) && !(go.piercable)) {
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

	public void setPosition(float x, float y) {
		spriteX = x;
		spriteY = y;
	}

	public abstract void render(GameContainer container, Graphics graphics);

	public void update(GameContainer container, int arg) {
		xGrip = getXAimFromDistanceAt(armLengt);
		yGrip = getYAimFromDistanceAt(armLengt);
	}

	protected float getXAimFromDistanceAt(float distance) {
		return spriteX + arm.x * distance;
	}

	protected float getYAimFromDistanceAt(float distance) {
		return spriteY + arm.y * distance;
	}

	public abstract void shoot();
}
