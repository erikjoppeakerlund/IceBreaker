package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public abstract class Impact {

	protected float effect;
	protected GameObject origin;

	public Impact(GameObject origin, float effect) {
		this.origin = origin;
		this.effect = effect;
	}

	public Impact(GameObject origin) {
		this.origin = origin;
		this.effect = 2.0f;
	}

	public GameObject getOrigin() {
		return origin;
	}

	public abstract void calculateEffect(Vector2 affectedPiece);

}
