package se.BaseUlterior.Physics;

import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	protected Set<Vector2> normals = null;
	protected Set<Vector2> normalsTester = null;

	public ImpactBounce(GameObject origin, GameObjectFalling go) {
		super(origin, go);
		normals = origin.getMyNormalsAfterHitBy(other);
	}

	@Override
	public void calculateIntersects(int delta) {

		normalsTester = origin.getMyNormalsAfterHitBy(other);
		if (!other.noForce) {
			normals = normalsTester;
		}
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
		 * using the algorithm: V� = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), V� is the resulting vector
		 */

		// Vector2 N = new Vector2(normals.getVal1(), normal.getVal2());

		float dot = affectedPiece.dot(N) * (1.0f + other.getBouncyness());

		if (!other.noForce) {
			affectedPiece.add((-N.getX() / 100.0f) * delta, (-N.getY() / 100.0f) * delta);
		}
		N.scale(dot);
		affectedPiece.sub(N);
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
		other.noForce = false;
	}
}
