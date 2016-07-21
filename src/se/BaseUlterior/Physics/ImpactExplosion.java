package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

/**
 * Affect the other game objects' motion vector as a 'punch'. Class not used!
 * 
 * @author Johan Akerlund
 */
public class ImpactExplosion extends Impact {

	private Vector2 returnValue;
	private float power;

	public ImpactExplosion(GameObject origin, GameObject other, float power) {
		super(origin, other);
		returnValue = new Vector2();
		this.power = power;
	}

	@Override
	public void calculateImpact(int delta) {
		float expX = origin.getCenterX();
		float expY = origin.getCenterY();

		float agileX = other.getCenterX();
		float agileY = other.getCenterY();

		float distanceX = agileX - expX;
		float distanceY = agileY - expY;

		float maxImpact = origin.getBoundingCircleRadius();

		returnValue.set(distanceX, distanceY);

		float powerLength = maxImpact - returnValue.length();
		float powerNormalized = powerLength / maxImpact;

		returnValue.normalise();

		affectedPiece.add(returnValue.scale(powerNormalized * this.power));

	}

}
