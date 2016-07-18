package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;

public class ImpactForce extends Impact {

	protected float gravityY;
	protected float gravityX;
	protected boolean contains = false;

	public float getGravityY() {
		return gravityY;
	}

	public float getGravityX() {
		return gravityX;
	}

	public ImpactForce(GameObject origin, GameObject other, float gravityX, float gravityY) {
		super(origin, other);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
	}

	@Override
	public void calculateImpact(int delta) {
		if (!contains) {
			if (origin.contains(other)) {
				contains = true;
			}
		}
		if (contains) {
			affectedPiece.add(gravityX, gravityY);
		}
	}

	@Override
	public void onReset() {
		contains = false;
	}
}
