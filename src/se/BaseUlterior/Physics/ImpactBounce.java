package se.BaseUlterior.Physics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	protected Set<Vector2> normals = null;

	public ImpactBounce(GameObject origin, GameObjectAgile go) {
		super(origin, go);
		normals = new HashSet<Vector2>();
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		other.addForceException();

		normals = origin.getMyNormalsAfterHitBy(other);

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

		// Vector2 N = new Vector2(normals.getVal1(), normal.getVal2());

		float dot = affectedPiece.dot(N) * (1.0f + other.getBouncyness());

		N.scale(dot);
		affectedPiece.sub(N);
	}
}
