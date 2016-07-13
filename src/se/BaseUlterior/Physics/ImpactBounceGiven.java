package se.BaseUlterior.Physics;

import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounceGiven extends Impact {

	protected Set<Vector2> normals = null;
	protected Set<Vector2> normalsTester = null;
	protected float bouncyness;

	public ImpactBounceGiven(GameObject origin, GameObject go, float bouncyness) {
		super(origin, go);
		normalsTester = other.getMyNormalsAfterHitBy(origin);
		this.bouncyness = bouncyness;
		affectedPiece = origin.getMotion();
	}

	@Override
	public void calculateIntersects(int delta) {

		normals = other.getMyNormalsAfterHitBy(origin);
		if (normals.isEmpty()) {
			return;
		}

		Iterator<Vector2> ni = normals.iterator();
		Vector2 N = null;
		int i = 0;
		while (ni.hasNext()) {
			Vector2 no = ni.next();
			if (i < 1) {
				N = no;
			} else {
				N.add(no);
			}
			i++;
		}

		N.normalise();

		/*
		 * using the algorithm: V´ = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), V´ is the resulting vector
		 */

		float dot = affectedPiece.dot(N) * (1.0f);

		affectedPiece.add((-N.getX() / 250.0f) * delta, (-N.getY() / 250.0f) * delta);
		N.scale(dot);
		affectedPiece.sub(N);

		other.noForce = false;
		origin.noForce = false;
	}

	@Override
	protected void calculateContains(int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void notTouchingButWithin() {
		origin.noForce = false;
	}
}
