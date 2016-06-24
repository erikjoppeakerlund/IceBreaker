package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class ImpactFriction extends Impact {

	public ImpactFriction(GameObject origin, GameObjectAgile other, float effect) {
		super(origin, other);
		this.effect = effect;
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		affectedPiece.scale(effect);
	}
}
