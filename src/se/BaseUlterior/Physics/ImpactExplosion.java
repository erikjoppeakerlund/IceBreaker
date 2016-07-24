package se.BaseUlterior.Physics;

import se.BaseUlterior.Config.Constants;
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
	private float size;

	public ImpactExplosion(GameObject origin, GameObject other, float power, float size) {
		super(origin, other);
		returnValue = new Vector2();
		this.power = power;
		this.size = size;
		if (other.HP > 0) {
			other.HP -= power * 29;
		}
	}

	@Override
	public void calculateImpact(int delta) {
		float expX = origin.getCenterX();
		float expY = origin.getCenterY();

		float agileX = other.getCenterX();
		float agileY = other.getCenterY();

		float distanceX = agileX - expX;
		float distanceY = agileY - expY;

		float maxImpact = size;

		returnValue.set(distanceX, distanceY);

		float powerLength = maxImpact - returnValue.length();
		float powerNormalized = powerLength / maxImpact;
		if (powerNormalized > 0.5f) {
			powerNormalized = 0.5f;
		}

		returnValue.normalise();
		if (affectedPiece.length() < Constants.GENERA_OBJECT_MAX_SPEED) {
			affectedPiece.add(returnValue.scale(powerNormalized * this.power));
		}

	}

}
