package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Geom.Vector2;

public abstract class Impact {

	protected float effect;
	protected GameObject origin;
	protected GameObjectFalling other;
	protected Vector2 affectedPiece;

	public Impact(GameObject origin, float effect, GameObjectFalling other) {
		this.origin = origin;
		this.effect = effect;
		this.other = other;
		affectedPiece = other.getMotion();
	}

	public Impact(GameObject origin, GameObjectFalling other) {
		this.origin = origin;
		this.effect = 2.0f;
		this.other = other;
		affectedPiece = other.getMotion();
	}

	public GameObject getTrigger() {
		return origin;
	}

	public GameObjectFalling getAffected() {
		return other;
	}

	public void checkCalculate() {
		if (origin.intersects(other)) {
			calculateIntersects();
		}
		if (origin.contains(other)) {
			calculateIntersects();
		}
	}

	protected abstract void calculateIntersects();

	protected abstract void calculateContains();

	public abstract void onDestroy();

	public abstract void notTouchingButWithin();

}
