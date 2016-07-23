package se.BaseUlterior.AI;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimTurret;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class AITurret extends GameObject {

	protected Aim aim;
	protected GameObject target;

	protected Vector2 startAngle;
	protected final int UPDATE_SPEED = 40;
	protected Vector2 aimArm;

	protected int timeSinceLast;
	private int itr;

	public AITurret(float[] points, Vector2 startangle) {
		super(points, false, false, false, false, false, true);
		this.aim = new AimTurret();
		this.startAngle = startangle;
		aim.setPosition(getCenterX(), getCenterY());
		aimArm = aim.getArm();
		timeSinceLast = (int) (Math.random() * UPDATE_SPEED);
	}

	@Override
	public void update(GameContainer container, int arg) {
		// if (timeSinceLast > UPDATE_SPEED) {

		itr++;
		if (lookUpClosestTarget()) {
			// // aim();
			if (itr % UPDATE_SPEED == 0) {
				shoot();
			}
			// timeSinceLast = 0;
			// }
			// } else {
			// timeSinceLast++;
		}
		aim.setPosition(getCenterX(), getCenterY());
		aim.update(container, arg);
	}

	protected abstract void shoot();

	protected abstract void aim();

	protected Vector2 injectVector = new Vector2();

	protected boolean lookUpClosestTarget() {
		boolean result = false;
		float distanceToClosestTarget = Constants.CANVAS_WIDTH;
		for (GameObject go : IceBreaker.all) {
			// more or less than zero? NOTE: CRITICAL!
			// if (UlteriorUtils.isWithinRange(go, IceBreaker.wholeSceene) &&
			// aim.getArm().dot(startAngle) > 0.0f) {
			if (UlteriorUtils.isWithinRange(go, IceBreaker.wholeSceene) && !go.motionLess && !go.isBackgroundObj
					&& go != this) {
				float xDist = go.getCenterX() - this.getCenterX();
				float yDist = go.getCenterY() - this.getCenterY();
				float distanceTest = (float) Math
						.sqrt((Math.pow(go.getX() - this.getX(), 2) + Math.pow(go.getY() - this.getY(), 2)));
				// System.out.println(distanceTest);
				if (distanceTest < distanceToClosestTarget) {
					distanceToClosestTarget = distanceTest;
					injectVector.set(xDist, yDist);
					injectVector.normalise();
					aim.setAngleToMouse((float) injectVector.getTheta());
					// aimArm.set(injectVector);

					this.target = go;
					result = true;
				}
			}
		}
		return result;
	}
}
