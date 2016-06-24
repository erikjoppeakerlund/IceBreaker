package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import se.BaseUlterior.Geom.Normal;
import se.BaseUlterior.Physics.Impact;

public abstract class GameObject extends Polygon {

	protected List<Impact> currentImpacts = null;

	public abstract Impact getImpact(GameObjectAgile agileObject);

	protected Color color = Color.darkGray;

	public abstract void update(GameContainer container, int arg) throws SlickException;

	public abstract void render(GameContainer container, Graphics graphics) throws SlickException;

	// protected List<Normal> normals = null;

	// TOTO: set back to null if it is not needed (or somethin)
	// to
	protected Normal normal = null;

	// TODO: rethink!
	/*
	 * public float[] getLatestNormal() { return this.latestNormal; }
	 */

	// public List<Normal> getNormals() {
	// return normals;
	// }

	public Normal getInternalNormal() {
		return this.normal;
	}

	public GameObject(float[] nodes) {
		super(nodes);
		currentImpacts = new ArrayList<>();
		// normals = new ArrayList<>();
	}

	/**
	 * Get the normal of the line between two points
	 * 
	 * @param start
	 *            The start point
	 * @param end
	 *            The end point
	 * @return The normal of the line between the two points
	 */
	public float[] getSurfaceNormal(float[] start, float[] end) {
		float dx = start[0] - end[0];
		float dy = start[1] - end[1];
		float len = (float) Math.sqrt((dx * dx) + (dy * dy));
		dx /= len;
		dy /= len;
		return new float[] { -dy, dx };
	}

	/**
	 * Check if this shape intersects with the shape provided.
	 * 
	 * @param shape
	 *            The shape to check if it intersects with this one.
	 * @return True if the shapes do intersect, false otherwise.
	 */
	public boolean provideMyNormalAfterHitBy(GameObject shape) {
		/*
		 * Intersection formula used: (x4 - x3)(y1 - y3) - (y4 - y3)(x1 - x3) UA
		 * = --------------------------------------- (y4 - y3)(x2 - x1) - (x4 -
		 * x3)(y2 - y1)
		 * 
		 * (x2 - x1)(y1 - y3) - (y2 - y1)(x1 - x3) UB =
		 * --------------------------------------- (y4 - y3)(x2 - x1) - (x4 -
		 * x3)(y2 - y1)
		 * 
		 * if UA and UB are both between 0 and 1 then the lines intersect.
		 * 
		 * Source: http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/
		 */
		checkPoints();

		boolean result = false;
		float points[] = getPoints(); // (x3, y3) and (x4, y4)
		float thatPoints[] = shape.getPoints(); // (x1, y1) and (x2, y2)
		int length = points.length;
		int thatLength = thatPoints.length;
		double unknownA;
		double unknownB;

		if (!closed()) {
			length -= 2;
		}
		if (!shape.closed()) {
			thatLength -= 2;
		}

		// x1 = thatPoints[j]
		// x2 = thatPoints[j + 2]
		// y1 = thatPoints[j + 1]
		// y2 = thatPoints[j + 3]
		// x3 = points[i]
		// x4 = points[i + 2]
		// y3 = points[i + 1]
		// y4 = points[i + 3]
		for (int i = 0; i < length; i += 2) {
			int iNext = i + 2;
			if (iNext >= points.length) {
				iNext = 0;
			}

			for (int j = 0; j < thatLength; j += 2) {
				int jNext = j + 2;
				if (jNext >= thatPoints.length) {
					jNext = 0;
				}

				unknownA = (((points[iNext] - points[i]) * (double) (thatPoints[j + 1] - points[i + 1]))
						- ((points[iNext + 1] - points[i + 1]) * (thatPoints[j] - points[i])))
						/ (((points[iNext + 1] - points[i + 1]) * (thatPoints[jNext] - thatPoints[j]))
								- ((points[iNext] - points[i]) * (thatPoints[jNext + 1] - thatPoints[j + 1])));
				unknownB = (((thatPoints[jNext] - thatPoints[j]) * (double) (thatPoints[j + 1] - points[i + 1]))
						- ((thatPoints[jNext + 1] - thatPoints[j + 1]) * (thatPoints[j] - points[i])))
						/ (((points[iNext + 1] - points[i + 1]) * (thatPoints[jNext] - thatPoints[j]))
								- ((points[iNext] - points[i]) * (thatPoints[jNext + 1] - thatPoints[j + 1])));

				if (unknownA >= 0 && unknownA <= 1 && unknownB >= 0 && unknownB <= 1) {

					result = true;
					if (points.length > i + 3) {

						float[] norm;

						if (shape.contains(points[i], points[i + 1])) {
							System.out.println("hej");
							norm = getNormal(i / 2);
						} else if (shape.contains(points[i + 2], points[i + 3])) {
							System.out.println("hej");
							norm = getNormal(i / 2 + 1);
						} else {
							norm = getSurfaceNormal(new float[] { points[i], points[i + 1] },
									new float[] { points[i + 2], points[i + 3] });
						}
						Normal n = new Normal(norm[0], norm[1]);

						// normals.add(n);
						normal = n;
					}
					// break;
				}
			}
			if (result) {
				// break;
			}
		}

		return result;
	}

}
