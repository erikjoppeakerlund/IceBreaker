package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Normal;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	protected Normal normal = null;

	// We don't need the normal parameter here!
	public ImpactBounce(GameObject origin, float effect, Normal normal, GameObjectAgile go) {
		super(origin, effect, go);
		this.normal = normal;
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		// other.intersectsGameObject(origin);

		origin.provideMyNormalAfterHitBy(other);

		/*
		 * using the algorithm: V´ = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), V´ is the resulting vector
		 */

		Normal normal = getCorrectNormal(affectedPiece);

		Vector2 N = new Vector2(normal.getVal1(), normal.getVal2());

		float dot = affectedPiece.dot(N) * effect;

		N.scale(dot);

		affectedPiece.sub(N);

	}

	private Normal getCorrectNormal(Vector2 motion) {

		// if (motion.getY() > 0.0f && motion.getX() > 0.0f) {
		// return
		// }

		return origin.getInternalNormal();

	}
}
