package se.BaseUlterior.AI;

import java.util.ArrayList;
import java.util.List;

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
	private final int MAX_HEART_RATE = 9;
	private int heartBeat = MAX_HEART_RATE;
	private final float SPEED = 0.49f;
	private Vector2 helperVector;

	protected float[] getBackToSpot;

	protected float[] currentSpot;
	protected final int WAIT_FOR_BEFORE_TURN = 1;

	private final int CONTINUE_WITH_TRACK = 4;

	private List<float[]> track;

	public AIBot(float height) {
		super(height);
		setCenterX(Constants.CANVAS_WIDTH * 0.75f);
		setCenterY(Constants.CANVAS_HEIGHT / 2f);
		helperVector = new Vector2(1f, 1f);
		forceUpdate = true;
		track = new ArrayList<>();
	}

	private boolean upToDate = true;
	private int trackingIterator = 0;

	private float[] toAdd;

	private int seenAlLong = CONTINUE_WITH_TRACK;

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (heartBeat >= MAX_HEART_RATE) {
			lookUpClosestTarget();
			boolean clearSpot = clearSpot(getCenter());

			if (clearSpot && seenAlLong < CONTINUE_WITH_TRACK) {
				seenAlLong++;
			}
			if (clearSpot) {
				trackingIterator = 0;
				if (!track.isEmpty()) {
					track.clear();
				}
				if (seenAlLong >= CONTINUE_WITH_TRACK) {
					helperVector.setTheta(thetaWhereTargetIsHeading());
				}
				if (!upToDate) {
					upToDate = true;
				}
				toAdd = new float[] { target.getCenterX() + ParallaxPhysicsEngine.currentX,
						target.getCenterY() + ParallaxPhysicsEngine.currentY };
			} else {
				seenAlLong = 0;
				if (upToDate) {
					track.add(toAdd);
				}
				int itr = trackingIterator;
				helperVector.setTheta(UlteriorUtils.angleToPoint(getCenterX() + ParallaxPhysicsEngine.currentX,
						getCenterY() + ParallaxPhysicsEngine.currentY, track.get(itr)[0], track.get(itr)[1]));
				upToDate = false;
			}
			heartBeat = 0;

			if (!upToDate) {

				track.add(new float[] { target.getCenterX() + ParallaxPhysicsEngine.currentX,
						target.getCenterY() + ParallaxPhysicsEngine.currentY });
			}
		} else

		{
			heartBeat++;
		}
		if (!upToDate) {
			if (UlteriorUtils.distance(getCenterX() + ParallaxPhysicsEngine.currentX,
					getCenterY() + ParallaxPhysicsEngine.currentY, track.get(trackingIterator)[0],
					track.get(trackingIterator)[1]) < maxRadiusStart * 2.1) {
				trackingIterator++;
			}
		}
		motion.add(helperVector.x, helperVector.y).normalise().scale(SPEED);
		if (HP <= 0) {
			UlteriorUtils.cleanUpImpactFromWorldBuilderObject(this);
			ParallaxPhysicsEngine.objsToRemove.add(this);
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
