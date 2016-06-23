package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactForce extends Impact {

	protected float gravityY;
	protected float gravityX;
	protected Vector2 motion;

	public ImpactForce(GameObject origin, float gravityX, float gravityY) {
		super(origin);
		this.motion = new Vector2(gravityX, gravityY);
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		affectedPiece.add(motion);
	}
}
