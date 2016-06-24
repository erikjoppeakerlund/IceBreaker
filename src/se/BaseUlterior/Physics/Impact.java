package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public abstract class Impact {

	protected float effect;
	protected GameObject origin;
	protected GameObject other;

	public Impact(GameObject origin, float effect, GameObject other) {
		this.origin = origin;
		this.effect = effect;
		this.other = other;
	}

	public Impact(GameObject origin, GameObject other) {
		this.origin = origin;
		this.effect = 2.0f;
		this.other = other;
	}

	public GameObject getOrigin() {
		return origin;
	}

	public abstract void calculateEffect(
			Vector2 affectedPiece/* Vector2 affectedPiece */);

}
