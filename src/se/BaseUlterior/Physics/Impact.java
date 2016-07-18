package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public abstract class Impact {

	protected GameObject origin;
	protected GameObject other;
	protected Vector2 affectedPiece;

	public Impact(GameObject origin, GameObject other) {
		this.origin = origin;
		this.other = other;
		affectedPiece = other.getMotion();
	}

	public GameObject getTrigger() {
		return origin;
	}

	public GameObject getAffected() {
		return other;
	}

	public abstract void calculateImpact(int delta);

}
