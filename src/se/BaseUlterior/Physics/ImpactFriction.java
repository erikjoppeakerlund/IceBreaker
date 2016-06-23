package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactFriction extends Impact {

	public ImpactFriction(GameObject origin, float effect) {
		super(origin);
		this.effect = effect;
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		affectedPiece.scale(effect);
	}
}
