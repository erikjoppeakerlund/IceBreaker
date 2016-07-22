package se.BaseUlterior.AI;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class AI extends GameObject {

	protected Aim aim;
	protected GameObject target;

	protected Vector2 currentAim;
	protected Vector2 startAngle;

	public AI(float[] points, Aim aim, Vector2 startangle) {
		super(points, false, false, false, false, false, true);
		this.aim = aim;
		currentAim = new Vector2();
		this.startAngle = startangle;
	}

	@Override
	public void update(GameContainer container, int arg) {
		if (UlteriorUtils.isWithinRange(IceBreaker.wholeSceene, this)) {
			if (lookUpClosestTarget()) {
				aim();
				shoot();
			}
		}

	}

	protected abstract void shoot();

	protected void aimDirectly() {

	}

	protected void aimAhead() {

	}

	protected abstract void aim();

	protected boolean lookUpClosestTarget() {
		boolean result = false;
		float distanceToClosestTarget = Constants.CANVAS_WIDTH;
		for (GameObject go : IceBreaker.all) {
			// more or less than zero? NOTE: CRITICAL!
			if (UlteriorUtils.isWithinRange(go, this) && aim.getArm().dot(startAngle) > 0.0f) {
				float xDist = go.getX() - this.getX();
				float yDist = go.getY() - this.getY();
				float distanceTest = (float) Math
						.sqrt((Math.pow(go.getX() - this.getX(), 2) * Math.pow(go.getY() - this.getY(), 2)));
				if (distanceTest < distanceToClosestTarget) {
					distanceToClosestTarget = distanceTest;
					currentAim.set(xDist, yDist);
					aim.getArm().set(xDist, yDist);
					aim.updateAim();
					if (!aim.getPointBlank().motionLess) {
						this.target = go;
						result = true;
					}
				}
			}
		}
		return result;
	}
}
