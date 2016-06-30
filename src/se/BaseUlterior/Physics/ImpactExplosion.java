package se.BaseUlterior.Physics;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class ImpactExplosion extends Impact {

	private long timeSinceExplotion;
	private long TIME_UNTIL_GONE = 500;

	private final float MAX_IMPACT = 0.3f;

	public ImpactExplosion(GameObject origin, GameObjectAgile other) {
		super(origin, other);
		timeSinceExplotion = System.currentTimeMillis();
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		float expX = origin.getCenterX();
		float expY = origin.getCenterY();

		float agileX = other.getCenterX();
		float agileY = other.getCenterY();

		float distanceX = agileX - expX;
		float distanceY = agileY - expY;

		float maxImpact = origin.getBoundingCircleRadius();

		float impactX = MAX_IMPACT * maxImpact / distanceX;
		float impactY = MAX_IMPACT * maxImpact / distanceY;

		affectedPiece.add(new Vector2(impactX, impactY));

		/*
		 * logics for handeling Agile Objects that are within the range of the
		 * explosion.
		 */
	}

	public void updateUntilGone() {
		if (System.currentTimeMillis() - timeSinceExplotion > TIME_UNTIL_GONE) {
			BreakingPoint.objsToRemove.add(origin);
			if (other != null) {
				other.removeImpact(this);
			}
		}
	}

	public void setAgileObject(GameObjectAgile agileObject) {
		this.other = agileObject;
	}
}
