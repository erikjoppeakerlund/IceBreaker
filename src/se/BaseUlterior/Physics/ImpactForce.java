package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;

/**
 * Adds values to a game objects motion vector
 * 
 * @author Johan Akerlund
 */

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
	public void checkCalculate(int delta) {
		calculateImpact(delta);
	}

	@Override
	public void calculateImpact(int delta) {
		if (origin.contains(other))
			affectedPiece.add(gravityX, gravityY);
	}

}
