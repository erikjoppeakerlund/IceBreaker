package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;

public class ImpactFriction extends Impact {

	protected float effect;
	protected boolean contains = false;

	public ImpactFriction(GameObject origin, GameObject other, float effect) {
		super(origin, other);
		this.effect = effect;
	}

	@Override
	public void calculateImpact(int delta) {
		if (origin.contains(other))
			affectedPiece.scale(effect);
	}

}
