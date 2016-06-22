package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactForce extends Impact {

	public ImpactForce(GameObject origin) {
		super(origin);
	}

	public ImpactForce(GameObject origin, float effect) {
		super(origin, effect);
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		// affectedPiece.scale(origin.getForceValue());
		affectedPiece.scale(effect);
	}
}
