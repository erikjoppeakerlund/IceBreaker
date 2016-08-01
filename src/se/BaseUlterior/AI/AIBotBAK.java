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

public class AIBotBAK extends AISprite {

	protected Vector2 aimArm;
	private Entity target;
	private int heartBeat = 0;
	private final int HEART_RATE = 30;
	private final float SPEED = 0.58f;
	private Vector2 helperVector;

	protected float[] getBackToSpot;

	protected float[] currentSpot;
	protected final int WAIT_FOR_BEFORE_TURN = 1;

	private boolean hasSpot = false;
	private float[] spot;

	public AIBotBAK(float height) {
		super(height);
		// aimArm = aim.getArm();
		setCenterX(Constants.CANVAS_WIDTH * 0.75f);
		setCenterY(Constants.CANVAS_HEIGHT / 2f);
		helperVector = new Vector2(1f, 1f);
		forceUpdate = true;
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (heartBeat >= HEART_RATE) {
			lookUpClosestTarget();
			boolean clearSpot = clearSpot(getCenter());

			if (!clearSpot && !hasSpot) {
				helperVector.setTheta(findClosestWayAroundTheta(UlteriorUtils.angleToPoint(getCenterX(), getCenterY(),
						target.getCenterX(), target.getCenterY())));
				hasSpot = true;
			} else if (clearSpot && !hasSpot) {
				helperVector.setTheta(UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), target.getCenterX(),
						target.getCenterY()));
			} else if (hasSpot) {
				if (clearSpot) {
					hasSpot = false;
				}
				helperVector.setTheta(UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), spot[0], spot[1]));
			}
			heartBeat = 0;
			motion.normalise().scale(SPEED).setTheta(helperVector.getTheta());

		} else {
			heartBeat++;
		}

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

	protected Entity whichIsTheTarget(float startX, float startY, float angle) {
		float xTarget = startX;
		float yTarget = startY;
		boolean notFound = true;
		int STEP = 8;
		Vector2 theta = new Vector2(angle);
		while (notFound) {
			xTarget += theta.x * STEP;
			yTarget += theta.y * STEP;
			for (Entity go : ParallaxPhysicsEngine.all) {
				if (go.contains(xTarget, yTarget) && !(go.piercable) && go != this) {
					return go;
				}
			}
		}
		return null;
	}

	private float findClosestWayAroundTheta(float angle) {
		return findWayAroundAtTurningAngle(angle);

	}

	protected float[] clearSpot(float angle) {
		float xTarget = getCenterX();
		float yTarget = getCenterY();
		boolean notFound = true;
		int STEP = 8;
		Vector2 theta = new Vector2(angle);
		while (notFound) {
			xTarget += theta.x * STEP;
			yTarget += theta.y * STEP;
			for (Entity go : ParallaxPhysicsEngine.all) {
				if (go.contains(xTarget, yTarget) && !(go.piercable) && go != this) {
					return new float[] { xTarget, yTarget };
				}
			}
		}
		return null;
	}

	protected boolean clearSpot(float[] coordinates) {
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

	private float START_CONSIDERED_WALL = 500f;

	private float findWayAroundAtTurningAngle(float angle) {
		Vector2 theta = new Vector2(angle);
		float THETA_STEP = 6f;

		float thetaStep = THETA_STEP;

		int stepsRun = 0;
		float distance = 0;
		Vector2 helperVect = theta.copy();
		float distance2 = 0;

		while (stepsRun < 360 / THETA_STEP) {

			float[] projection = clearSpot((float) theta.getTheta());
			float[] projection2 = clearSpot((float) helperVect.getTheta());

			float TEST = START_CONSIDERED_WALL;

			float tempDistance1 = UlteriorUtils.distance(getCenterX(), getCenterY(), projection[0], projection[1]);
			float tempDistance2 = UlteriorUtils.distance(getCenterX(), getCenterY(), projection2[0], projection2[1]);

			if (stepsRun == 0) {
				distance = tempDistance1;
				distance2 = tempDistance2;
			}
			if (Math.abs(tempDistance1 - distance) > TEST) {
				hasSpot = true;
				spot = projection;
				return (float) theta.add(THETA_STEP * 8).getTheta();

			}
			if (Math.abs(tempDistance2 - distance2) > TEST) {
				spot = projection2;
				hasSpot = true;
				return (float) helperVect.sub(THETA_STEP * 8).getTheta();
			}
			distance = tempDistance1;
			distance2 = tempDistance2;

			theta.add(thetaStep);
			helperVect.sub(thetaStep);

			stepsRun++;
		}
		return angle;
	}
}
