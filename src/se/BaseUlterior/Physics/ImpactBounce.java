package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	protected float[] normalRecieved;

	public ImpactBounce(GameObject origin, float[] normal) {
		super(origin);
		this.normalRecieved = normal;
	}

	public ImpactBounce(GameObject origin, float effect, float[] normal) {
		super(origin, effect);
		this.normalRecieved = normal;
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		// using the algorithm
		// V´ = V - (2*(V . N)) * N

		// ...where N is the normal of the hit surface,
		// V is the moving particle ('affectedPiece'),
		// V´ is the resulting vector

		Vector2 N = new Vector2(normalRecieved);

		float dot = affectedPiece.dot(N) * effect;

		N.scale(dot);

		affectedPiece.sub(N);

	}
}
