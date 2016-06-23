package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Physics.Impact;

public abstract class GameObject extends Polygon {

	public abstract Impact getImpact(AgileObject agileObject);

	protected Color color = Color.darkGray;

	public abstract void update(GameContainer container, int arg) throws SlickException;

	public abstract void render(GameContainer container, Graphics graphics) throws SlickException;

	protected float[] latestNormal;

	public float[] getLatestNormal() {
		return this.latestNormal;
	}

	public GameObject(float[] nodes) {
		super(nodes);
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
	public boolean intersects(GameObject shape) {
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

					this.latestNormal = getSurfaceNormal(new float[] { points[i], points[i + 1] },
							new float[] { points[i + 2], points[i + 3] });
					// System.out.println(Arrays.toString(latestNormal));

					result = true;
					break;
				}
			}
			if (result) {
				break;
			}
		}

		return result;
	}

	public float[][] getClosestPoints(float x, float y) {
		float closest1 = Constants.CANVAS_WIDTH;
		float closest2 = Constants.CANVAS_WIDTH;
		int closeIndex1 = 0;
		int closeIndex2 = 0;
		float[] points = getPoints();

		for (int i = 0; i < points.length; i += 2) {

			float x1 = points[i];
			float x2 = points[i + 2];

			float y1 = points[i + 1];
			float y2 = points[i + 3];

			float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			closeIndex1 = closest1 < dist && i != closeIndex2 ? i : closeIndex1;
			closeIndex2 = closest2 < dist && i != closeIndex1 ? i : closeIndex2;
		}
		return new float[][] { getPoint(closeIndex1), getPoint(closeIndex2) };
	}

}
