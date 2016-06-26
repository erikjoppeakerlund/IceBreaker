package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public abstract class Impact {

	protected float effect;
	protected GameObject origin;
	protected GameObjectAgile other;

	public Impact(GameObject origin, float effect, GameObjectAgile other) {
		this.origin = origin;
		this.effect = effect;
		this.other = other;
	}

	public Impact(GameObject origin, GameObjectAgile other) {
		this.origin = origin;
		this.effect = 2.0f;
		this.other = other;
	}

	public GameObject getOrigin() {
		return origin;
	}

	public GameObjectAgile getGameObjectAgile() {
		return other;
	}

	public abstract void calculateEffect(Vector2 affectedPiece);
}
