package se.BaseUlterior.AI;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class AIBot extends AISprite {

	protected Vector2 aimArm;
	private GameObject target;
	private int heartBeat = 0;
	private final int HEART_RATE = 30;
	private final float SPEED = 0.7f;
	private Vector2 helperVector;

	protected float[] getBackToSpot;

	protected float[] currentSpot;
	protected final int WAIT_FOR_BEFORE_TURN = 1;

	public AIBot(float height) {
		super(height);
		aimArm = aim.getArm();
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
			if (!clearSpot(getCenter())) {
				helperVector.setTheta(findClosestWayAroundTheta(UlteriorUtils.angleToPoint(getCenterX(), getCenterY(),
						target.getCenterX(), target.getCenterY())));
			} else {
				helperVector.setTheta(UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), target.getCenterX(),
						target.getCenterY()));
			}
			heartBeat = 0;
			motion.normalise().scale(SPEED).setTheta(helperVector.getTheta());
		} else

		{
			heartBeat++;
		}

	}

	private boolean lookUpClosestTarget() {
		boolean result = false;
		float distanceToClosestTarget = Constants.CANVAS_WIDTH;
		for (GameObject go : IceBreaker.all) {
			if (UlteriorUtils.isWithinRange(go, IceBreaker.wholeSceene) && !go.motionLess && !go.isBackgroundObj
					&& !go.piercable && go != this) {
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

	private float lookUpCurrentThetaGoal(float targetX, float targetY) {

		float angle = UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), targetX, targetY);
		return angle;
	}

	protected GameObject whichIsTheTarget(float startX, float startY, float angle) {
		float xTarget = startX;
		float yTarget = startY;
		boolean notFound = true;
		int STEP = 8;
		Vector2 theta = new Vector2(angle);
		while (notFound) {
			xTarget += theta.x * STEP;
			yTarget += theta.y * STEP;
			for (GameObject go : IceBreaker.all) {
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
			for (GameObject go : IceBreaker.all) {
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
			for (GameObject go : IceBreaker.all) {
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

	private GameObject gameObjectInTheWay;

	private float findWayAroundAtTurningAngle(float angle) {
		Vector2 theta = new Vector2(angle);
		float THETA_STEP = 6f;

		float thetaStep = THETA_STEP;

		int stepsRun = 0;
		float distance = 0;
		Vector2 helperVect = theta.copy();
		float distance2 = 0;

		boolean lastLap1 = false;
		boolean lastLap2 = false;

		while (stepsRun < 360 / THETA_STEP) {

			float[] projection = clearSpot((float) theta.getTheta());
			float[] projection2 = clearSpot((float) helperVect.getTheta());

			float TEST = 500f;

			float tempDistance1 = UlteriorUtils.distance(getCenterX(), getCenterY(), projection[0], projection[1]);
			float tempDistance2 = UlteriorUtils.distance(getCenterX(), getCenterY(), projection2[0], projection2[1]);

			if (stepsRun == 0) {
				distance = tempDistance1;
				distance2 = tempDistance2;

			}

			if (Math.abs(distance - tempDistance1) > TEST) {

				// if (lastLap1) {
				return (float) theta.add(THETA_STEP * 4).getTheta();
				// }
				// lastLap1 = true;

			}
			if (Math.abs(distance2 - tempDistance2) > TEST) {
				// if (lastLap2) {
				return (float) helperVect.sub(THETA_STEP * 4).getTheta();
				// }
				// lastLap2 = true;
			}
			distance = tempDistance1;
			distance2 = tempDistance2;

			theta.add(thetaStep);
			helperVect.sub(thetaStep);

			// if (lastLap1 || lastLap2) {
			// theta.add(thetaStep * 10f);
			// helperVect.sub(thetaStep * 10f);
			// }

			stepsRun++;
		}
		return angle;
	}

}
