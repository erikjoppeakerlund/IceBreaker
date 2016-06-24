package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class ImpactForce extends Impact {

	protected float gravityY;
	protected float gravityX;

	public float getGravityY() {
		return gravityY;
	}

	public float getGravityX() {
		return gravityX;
	}

	public ImpactForce(GameObject origin, GameObjectAgile other, float gravityX, float gravityY) {
		super(origin, other);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		affectedPiece.add(gravityX, gravityY);
	}
}
