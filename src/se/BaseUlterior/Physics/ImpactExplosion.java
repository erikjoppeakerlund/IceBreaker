package se.BaseUlterior.Physics;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class ImpactExplosion extends Impact {

	private long timeSinceExplotion;
	private long TIME_UNTIL_GONE = 200;

	public ImpactExplosion(GameObject origin, GameObjectAgile other) {
		super(origin, other);
		timeSinceExplotion = System.currentTimeMillis();
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		/*
		 * logict for hadnlign Agile Objects that are within the range of the
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
