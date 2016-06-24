package se.BaseUlterior.Physics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Normal;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	protected Set<Normal> normals = null;

	// We don't need the normal parameter here!
	public ImpactBounce(GameObject origin, float effect, GameObjectAgile go) {
		super(origin, effect, go);
		normals = new HashSet<Normal>();
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		normals = origin.provideMyNormalAfterHitBy(other);

		Iterator<Normal> ni = normals.iterator();
		Vector2 N = null;
		int i = 0;
		float lenght = 1.0f;
		while (ni.hasNext()) {
			Normal no = ni.next();
			if (i < 1) {
				N = new Vector2(no.getVal1(), no.getVal2());
				lenght = N.length();
			} else {
				N.add(no.getVal1(), no.getVal2());
			}
			i++;
		}

		N.normalise();
//		N.scale();

		/*
		 * using the algorithm: V´ = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), V´ is the resulting vector
		 */

		// Vector2 N = new Vector2(normals.getVal1(), normal.getVal2());

		float dot = affectedPiece.dot(N) * effect;

		N.scale(dot);

		affectedPiece.sub(N);

	}

}
