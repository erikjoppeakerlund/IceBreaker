package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import se.BaseUlterior.GameObject.Override.PolygonCustom;
import se.BaseUlterior.Geom.Normal;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;

public abstract class GameObject extends Shape {

	private boolean closed = true;
	private boolean allowDups = false;

	protected List<Impact> currentImpacts = null;

	protected Color color = Color.darkGray;

	protected Vector2 motion = null;

	// put argument in builder!

	// public GameObject(float[] nodes) {
	// points = nodes;
	// // super(nodes);
	// currentImpacts = new ArrayList<>();
	// motion = new Vector2();
	// // normals = new ArrayList<>();
	// }

	private void init() {
		currentImpacts = new ArrayList<>();
		motion = new Vector2();

	}

	/**
	 * Construct a new polygon with 3 or more points. This constructor will take
	 * the first set of points and copy them after the last set of points to
	 * create a closed shape.
	 * 
	 * @param points
	 *            An array of points in x, y order.
	 */
	public GameObject(float points[]) {
		init();
		int length = points.length;

		this.points = new float[length];
		maxX = -Float.MIN_VALUE;
		maxY = -Float.MIN_VALUE;
		minX = Float.MAX_VALUE;
		minY = Float.MAX_VALUE;
		x = Float.MAX_VALUE;
		y = Float.MAX_VALUE;

		for (int i = 0; i < length; i++) {
			this.points[i] = points[i];
			if (i % 2 == 0) {
				if (points[i] > maxX) {
					maxX = points[i];
				}
				if (points[i] < minX) {
					minX = points[i];
				}
				if (points[i] < x) {
					x = points[i];
				}
			} else {
				if (points[i] > maxY) {
					maxY = points[i];
				}
				if (points[i] < minY) {
					minY = points[i];
				}
				if (points[i] < y) {
					y = points[i];
				}
			}
		}

		findCenter();
		calculateRadius();
		pointsDirty = true;
	}

	/**
	 * Create an empty polygon
	 *
	 */
	public GameObject() {
		init();
		points = new float[0];
		maxX = -Float.MIN_VALUE;
		maxY = -Float.MIN_VALUE;
		minX = Float.MAX_VALUE;
		minY = Float.MAX_VALUE;
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
	public Set<Normal> provideMyNormalAfterHitBy(
			GameObject shape/* , Normal normal */) {

		Set<Normal> normals = new HashSet<>();
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

		float thatDx = shape.motion.x;
		float thatDy = shape.motion.y;

		float dX = motion.x;
		float dY = motion.y;

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

				unknownA = (((points[iNext] + dX - points[i] + dX)
						* (double) (thatPoints[j + 1] + thatDy - points[i + 1] + thatDy))
						- ((points[iNext + 1] + dY - points[i + 1] + dY) * (thatPoints[j] + thatDx - points[i] + dX)))
						/ (((points[iNext + 1] + dY - points[i + 1] + dY)
								* (thatPoints[jNext] + thatDx - thatPoints[j] + thatDx))
								- ((points[iNext] + dX + -points[i] + dX)
										* (thatPoints[jNext + 1] + thatDy - thatPoints[j + 1] + thatDy)));
				unknownB = (((thatPoints[jNext] + thatDx - thatPoints[j] + thatDx)
						* (double) (thatPoints[j + 1] + thatDy - points[i + 1] + dY))
						- ((thatPoints[jNext + 1] + thatDy - thatPoints[j + 1] + thatDy)
								* (thatPoints[j] + thatDx - points[i] + dX)))
						/ (((points[iNext + 1] + dY - points[i + 1] + dY)
								* (thatPoints[jNext] + thatDx - thatPoints[j] + thatDx))
								- ((points[iNext] + dX - points[i] + dX)
										* (thatPoints[jNext + 1] + thatDy - thatPoints[j + 1] + thatDy)));

				if (unknownA >= 0 && unknownA <= 1 && unknownB >= 0 && unknownB <= 1) {

					result = true;
					if (points.length > i + 3) {

						float[] norm;
						norm = getSurfaceNormal(new float[] { points[i], points[i + 1] },
								new float[] { points[i + 2], points[i + 3] });
						Normal n = new Normal(norm[0], norm[1]);

						normals.add(n);

					}
					// break;
				}
			}
			if (result) {
				// break;
			}
		}
		return normals;
		// return result;
	}

	/**
	 * Check if this shape intersects with the shape provided.
	 * 
	 * @param shape
	 *            The shape to check if it intersects with this one.
	 * @return True if the shapes do intersect, false otherwise.
	 */
	public boolean intersectsGameobject(GameObject shape) {
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

		float thatDx = shape.motion.x;
		float thatDy = shape.motion.y;

		float dX = motion.x;
		float dY = motion.y;

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

				unknownA = (((points[iNext] + dX - points[i] + dX)
						* (double) (thatPoints[j + 1] + thatDy - points[i + 1] + thatDy))
						- ((points[iNext + 1] + dY - points[i + 1] + dY) * (thatPoints[j] + thatDx - points[i] + dX)))
						/ (((points[iNext + 1] + dY - points[i + 1] + dY)
								* (thatPoints[jNext] + thatDx - thatPoints[j] + thatDx))
								- ((points[iNext] + dX + -points[i] + dX)
										* (thatPoints[jNext + 1] + thatDy - thatPoints[j + 1] + thatDy)));
				unknownB = (((thatPoints[jNext] + thatDx - thatPoints[j] + thatDx)
						* (double) (thatPoints[j + 1] + thatDy - points[i + 1] + dY))
						- ((thatPoints[jNext + 1] + thatDy - thatPoints[j + 1] + thatDy)
								* (thatPoints[j] + thatDx - points[i] + dX)))
						/ (((points[iNext + 1] + dY - points[i + 1] + dY)
								* (thatPoints[jNext] + thatDx - thatPoints[j] + thatDx))
								- ((points[iNext] + dX - points[i] + dX)
										* (thatPoints[jNext + 1] + thatDy - thatPoints[j + 1] + thatDy)));

				if (unknownA >= 0 && unknownA <= 1 && unknownB >= 0 && unknownB <= 1) {
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

	/**
	 * Check if the shape passed is entirely contained within this shape.
	 * 
	 * @param other
	 *            The other shape to test against this one
	 * @return True if the other shape supplied is entirely contained within
	 *         this one.
	 */
	public boolean containsGameObject(GameObject other) {
		if (other.intersectsGameobject(this)) {
			return false;
		}

		for (int i = 0; i < other.getPointCount(); i++) {
			float[] pt = other.getPoint(i);
			if (!contains(pt[0] + other.motion.x, pt[1] + other.motion.y)) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected void createPoints() {

	}

	/**
	 * Apply a transformation and return a new shape. This will not alter the
	 * current shape but will return the transformed shape.
	 * 
	 * @param transform
	 *            The transform to be applied
	 * @return The transformed shape.
	 */
	public Shape transform(Transform transform) {
		checkPoints();

		PolygonCustom resultPolygon = new PolygonCustom();

		float result[] = new float[points.length];
		transform.transform(points, 0, result, 0, points.length / 2);
		resultPolygon.setPoints(result);
		resultPolygon.findCenterPublic();
		resultPolygon.setClosed(closed);

		return resultPolygon;
	}

	public void setPoints(float[] points) {
		this.points = points;
	}

	public abstract void update(GameContainer container, int arg) throws SlickException;

	public abstract void render(GameContainer container, Graphics graphics) throws SlickException;

	public abstract Impact getImpact(GameObjectAgile agileObject);

	/**
	 * @see org.newdawn.slick.geom.Shape#closed()
	 */
	public boolean closed() {
		return closed;
	}

	/**
	 * Indicate if the polygon should be closed
	 * 
	 * @param closed
	 *            True if the polygon should be closed
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	/**
	 * Add a point to the polygon
	 * 
	 * @param x
	 *            The x coordinate of the point
	 * @param y
	 *            The y coordinate of the point
	 */
	public void addPoint(float x, float y) {
		if (hasVertex(x, y) && (!allowDups)) {
			return;
		}

		ArrayList tempPoints = new ArrayList();
		for (int i = 0; i < points.length; i++) {
			tempPoints.add(new Float(points[i]));
		}
		tempPoints.add(new Float(x));
		tempPoints.add(new Float(y));
		int length = tempPoints.size();
		points = new float[length];
		for (int i = 0; i < length; i++) {
			points[i] = ((Float) tempPoints.get(i)).floatValue();
		}
		if (x > maxX) {
			maxX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (x < minX) {
			minX = x;
		}
		if (y < minY) {
			minY = y;
		}
		findCenter();
		calculateRadius();

		pointsDirty = true;
	}

	/**
	 * Indicate if duplicate points are allow
	 * 
	 * @param allowDups
	 *            True if duplicate points are allowed
	 */
	public void setAllowDuplicatePoints(boolean allowDups) {
		this.allowDups = allowDups;
	}
}
