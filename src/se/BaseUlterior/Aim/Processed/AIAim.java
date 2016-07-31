package se.BaseUlterior.Aim.Processed;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

public abstract class AIAim {

	protected float armLengt;
	public float spriteX;
	public float spriteY;

	protected Vector2 grip;
	protected Vector2 aimStart;

	protected Entity pointBlank = null;

	protected Vector2 arm;

	public Vector2 getArm() {
		return arm;
	}

	public AIAim(float armLengt) {
		this.armLengt = armLengt;
		arm = new Vector2(0.0f);
		grip = new Vector2(0f).scale(armLengt);
	}

	protected double theta;

	protected float aimAtX;
	protected float aimAtY;

	protected void updateAim() {

		float xTarget = spriteX + grip.x;
		float yTarget = spriteY + grip.y;

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
		theta = arm.getTheta();
		grip.setTheta(theta);

	}

	public abstract void shoot();
}
