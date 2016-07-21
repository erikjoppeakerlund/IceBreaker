package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;

/**
 * Scale other game objects motion vector
 * 
 * @author Johan Akerlund
 */
public class ImpactFriction extends Impact {

	protected float effect;
	protected boolean contains = false;

	public ImpactFriction(GameObject origin, GameObject other, float effect) {
		super(origin, other);
		this.effect = effect;
	}

	@Override
	public void calculateImpact(int delta) {
		if (origin.contains(other)) {
			if (other.isRotatingObject) {
				other.rotation *= effect;
			}
			affectedPiece.scale(effect);
		}
	}

}
