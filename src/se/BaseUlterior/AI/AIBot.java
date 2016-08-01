package se.BaseUlterior.AI;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * @author Johan Akerlund
 */

public class AIBot extends AISprite {

	protected Vector2 aimArm;
	private Entity target;
	private int heartBeat = 0;
	private int HEART_RATE = 0;
	private final int MAX_HEART_RATE = 49;
	private final float SPEED = 0.89f;
	private Vector2 helperVector;

	protected float[] getBackToSpot;

	protected float[] currentSpot;
	protected final int WAIT_FOR_BEFORE_TURN = 1;

	public AIBot(float height) {
		super(height);
		setCenterX(Constants.CANVAS_WIDTH * 0.75f);
		setCenterY(Constants.CANVAS_HEIGHT / 2f);
		helperVector = new Vector2(1f, 1f);
		forceUpdate = true;
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (heartBeat >= MAX_HEART_RATE) {
			lookUpClosestTarget();
			boolean clearSpot = clearSpot(getCenter());

			if (clearSpot) {
				helperVector.setTheta(thetaWhereTargetIsHeading());
				// motion.setTheta(helperVector.getTheta());
			}
			heartBeat = 0;
			HEART_RATE = (int) (Math.random() * MAX_HEART_RATE);

			motion.add(helperVector.x, helperVector.y).normalise().scale(SPEED);
		} else {
			heartBeat++;
		}
		// motion.normalise().scale(SPEED);

	}

	private boolean lookUpClosestTarget() {
		boolean result = false;
		float distanceToClosestTarget = Constants.CANVAS_WIDTH;
		for (Entity go : ParallaxPhysicsEngine.all) {
			if (!go.motionLess && !go.isBackgroundObj && !go.piercable && go != this) {
				float xDist = go.getCenterX() - this.getCenterX();
				float yDist = go.getCenterY() - this.getCenterY();
				float distanceTest = (float) Math.sqrt((Math.pow(xDist, 2) + Math.pow(yDist, 2)));
				if (distanceTest < distanceToClosestTarget) {
					target = go;
					distanceToClosestTarget = distanceTest;
					result = true;
				}
			}
		}
		return result;
	}

	private boolean clearSpot(float[] coordinates) {
		float xTarget = coordinates[0];
		float yTarget = coordinates[1];
		boolean notFound = true;
		int STEP = 8;
		Vector2 theta = new Vector2(
				UlteriorUtils.angleToPoint(xTarget, yTarget, target.getCenterX(), target.getCenterY()));
		while (notFound) {
			xTarget += theta.x * STEP;
			yTarget += theta.y * STEP;
			for (Entity go : ParallaxPhysicsEngine.all) {
				if (go.contains(xTarget, yTarget) && !(go.piercable) && go != this) {
					if (go == target) {
						return true;

					} else {
						return false;
					}
				}

			}
		}
		return false;
	}

	private float thetaWhereTargetIsHeading() {
		float aX = target.getCenterX();
		float aY = target.getCenterY();

		Vector2 b = target.getMotion().copy();
		float targetSpeed = b.length();

		Vector2 imagenedPath = motion.copy();

		float centerX = getCenterX();
		float centerY = getCenterY();

		Vector2 correctDirectionCheck = new Vector2();

		float i = 1;
		while (i < 10000) {

			b.normalise().scale(targetSpeed * (float) i);

			float bX = aX + b.x;
			float bY = aY + b.y;

			imagenedPath.setTheta(UlteriorUtils.angleToPoint(centerX, centerY, bX, bY));

			imagenedPath.normalise().scale(SPEED * (float) i);

			float cX = centerX + imagenedPath.x * i;
			float cY = centerY + imagenedPath.y * i;

			correctDirectionCheck.set(imagenedPath.x - aX, imagenedPath.y - aY);

			if (((bX - aX) * (cY - aY) - (bY - aY) * (cX - aX)) > 0 && b.dot(correctDirectionCheck) < 0) {
				return (float) imagenedPath.getTheta();
			}
			i += 1.0f;
		}
		return UlteriorUtils.angleToPoint(centerX, centerY, aX, aY);
	}

}
