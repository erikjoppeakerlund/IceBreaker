package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	public ImpactBounce(GameObject origin) {
		super(origin);
		// TODO Auto-generated constructor stub
	}

	public ImpactBounce(GameObject origin, float effect) {
		super(origin, effect);
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		float[][] closestPoints = origin.getClosestPoints();

		// using the algorithm
		// V´ = V - (2*(V . N)) * N

		// ...where N is the normal of the hit surface,
		// V is the moving particle ('affectedPiece'),
		// V´ is the resulting vector

		// you might need to redefine this like so:
		// affectedPiece = V.sub(V.dot(N)*2.0f).mul(N);

		Vector2 N = new Vector2(origin.getSurfaceNormal(closestPoints[0], closestPoints[1]));

		affectedPiece = affectedPiece.sub(affectedPiece.dot(N) * effect).scale(N.length());

	}
}
