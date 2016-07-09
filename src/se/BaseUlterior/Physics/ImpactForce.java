package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;

public class ImpactForce extends Impact {

	protected float gravityY;
	protected float gravityX;

	public float getGravityY() {
		return gravityY;
	}

	public float getGravityX() {
		return gravityX;
	}

	public ImpactForce(GameObject origin, GameObjectFalling other, float gravityX, float gravityY) {
		super(origin, other);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
	}

	@Override
	public void calculateIntersects() {
		// if (!other.isForceException()) {
		if (!other.noForce) {
			affectedPiece.add(gravityX, gravityY);
		}
		// }
	}

	@Override
	protected void calculateContains() {
		{
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
