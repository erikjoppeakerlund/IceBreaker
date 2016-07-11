package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Geom.Vector2;

public abstract class Impact {

	protected GameObject origin;
	protected GameObjectFalling other;
	protected Vector2 affectedPiece;

	public Impact(GameObject origin, GameObjectFalling other) {
		this.origin = origin;
		this.other = other;
		affectedPiece = other.getMotion();
	}

	public GameObject getTrigger() {
		return origin;
	}

	public GameObjectFalling getAffected() {
		return other;
	}

	public void checkCalculate(int delta) {
		if (origin.intersects(other)) {
			calculateIntersects(delta);
		}
		if (origin.contains(other)) {
			calculateIntersects(delta);
		}
	}

	protected abstract void calculateIntersects(int delta);

	protected abstract void calculateContains(int delta);

	public abstract void onDestroy();

	public abstract void notTouchingButWithin();

}
