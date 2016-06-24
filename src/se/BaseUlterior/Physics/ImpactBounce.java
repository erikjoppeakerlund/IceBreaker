package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Normal;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	// protected float[] normalRecieved;
	// protected List<Normal> normals;

	protected Normal normal = null;

	// not really an appliccable constructor
	/*
	 * public ImpactBounce(GameObject origin, float[] normal) { super(origin);
	 * this.normalRecieved = normal; }
	 */

	public ImpactBounce(GameObject origin, float effect/* , float[] normal */,
			/* List<Normal> normals */ Normal normal, GameObject go) {
		super(origin, effect, go);
		// this.normalRecieved = normal;
		// this.normals = normals;
		this.normal = normal;
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {

		// using the algorithm
		// V� = V - (2*(V . N)) * N

		// ...where N is the normal of the hit surface,
		// V is the moving particle ('affectedPiece'),
		// V� is the resulting vector

		Vector2 N = new Vector2(normal.getVal1(), normal.getVal2());

		// Normal recieved = normals.get(normals.size() - 1);
		//
		// float[] normalRecieved = new float[] { recieved.getVal1(),
		// recieved.getVal2() };
		//
		// float[] normal = new float[normalRecieved.length];
		//
		// System.arraycopy(normalRecieved, 0, normal, 0, normal.length);
		//
		// Vector2 N = new Vector2(normal);
		//
		// if (normals.size() > 2) {
		// normals = normals.subList(normals.size() - 3, normals.size() - 1);
		// }

		// normals.remove(normals.indexOf(normalRecieved));

		float dot = affectedPiece.dot(N) * effect;

		N.scale(dot);

		affectedPiece.sub(N);

	}
}
