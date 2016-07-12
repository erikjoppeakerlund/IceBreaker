package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;

public class ImpactForce extends Impact {

	protected float gravityY;
	protected float gravityX;

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
	public void calculateIntersects(int delta) {
		if (!other.noForce) {
			affectedPiece.add(gravityX, gravityY);
		}
	}

	@Override
	protected void calculateContains(int delta) {
		if (!other.noForce) {
			affectedPiece.add(gravityX, gravityY);
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notTouchingButWithin() {
		// TODO Auto-generated method stub

	}
}
