package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;

public class ImpactFriction extends Impact {

	protected float effect;

	public ImpactFriction(GameObject origin, GameObjectFalling other, float effect) {
		super(origin, other);
		this.effect = effect;
	}

	@Override
	public void calculateIntersects(int delta) {
		affectedPiece.scale(effect * delta);
	}

	@Override
	protected void calculateContains(int delta) {
		// TODO Auto-generated method stub

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
