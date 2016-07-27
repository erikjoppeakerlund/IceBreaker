package se.BaseUlterior.Physics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

/**
 * Affect the other game objects' motion vector as a 'punch'. Class not used!
 * 
 * @author Johan Akerlund
 */
public class ImpactExplosion extends Impact {

	private Vector2 returnValue;
	private float size;
	private float bumpEffect;

	public ImpactExplosion(GameObject origin, GameObject other, float damage, float size, float bumpEffect) {
		super(origin, other);
		returnValue = new Vector2();
		this.size = size;
		if (other.HP > 0) {
			other.HP -= damage;
		}
		this.bumpEffect = bumpEffect;
		if (other.START_HP > 999) {
			IceBreaker.gameInfo.setHP(other.HP);
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
			affectedPiece.add(returnValue.scale(powerNormalized * bumpEffect));
		}

	}

}
