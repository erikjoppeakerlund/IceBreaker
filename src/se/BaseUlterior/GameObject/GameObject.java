package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;

/*
 * Class which instances might be affected by logics in an se.BaseUlterior.Physics.Impact
 * instance, rendered trough Slick2d library.
 * 
 * IMPORTANT NOTE: This class contains functions which are not written by be,
 * but by the author of Slick2d!
 * 
 */

public abstract class GameObject extends Polygon {

	protected List<Impact> currentImpacts = null;
	protected List<Impact> impactsToRemove = new ArrayList<>();
	protected Color color;
	protected Vector2 motion = null;
	public int maxRadiusStart;
	protected float bounciness;
	public boolean isBackgroundObj = false;
	public boolean piercable = false;
	public boolean isRotatingObject = false;
	public boolean forceRender = false;
	public boolean forceUpdate = false;
	public boolean motionLess = true;
	public float rotation;
	public short HP = 0;

	public Vector2 getMotion() {
		return motion;
	}

	private void init() {
		// why set on anything but falling pieces?
		currentImpacts = new ArrayList<>();
		motion = new Vector2();
		maxRadiusStart = (int) getBoundingCircleRadius();
	}

	protected GameObject(float[] points, boolean isBackgroundObj, boolean invisible, boolean isRotatingObject,
			boolean forceUpdate, boolean forceRender, boolean isSolid) {
		super(points);
		init();
		this.isBackgroundObj = isBackgroundObj;
		this.piercable = invisible;
		this.isRotatingObject = isRotatingObject;
		this.forceRender = forceRender;
		this.forceUpdate = forceUpdate;
		this.motionLess = isSolid;

	};

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

	public void removeImpact(Impact im) {
		impactsToRemove.add(im);
	}

	/**
	 * Check if this shape intersects with the shape provided.
	 * 
	 * @param shape
	 *            The shape to check if it intersects with this one.
	 * @return True if the shapes do intersect, false otherwise.
	 */
	public Set<Vector2> getMyNormalsAfterHitBy(GameObject shape) {

		Set<Vector2> normals = new HashSet<>();
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

		float points[] = getPoints(); // (x3, y3) and (x4, y4)
		float thatPoints[] = shape.getPoints(); // (x1, y1) and (x2, y2)
		int length = points.length;
		int thatLength = thatPoints.length;
		double unknownA;
		double unknownB;

		Vector2 lastNormal = null;

		if (!closed()) {
			length -= 2;
		}
		if (!shape.closed()) {
			thatLength -= 2;
		}

		float thatDx = 0;
		float thatDy = 0;

		float dX = 0;
		float dY = 0;

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

					Vector2 newestNormal;

					float[] norm = getSurfaceNormal(new float[] { points[i], points[i + 1] },
							new float[] { points[iNext], points[iNext + 1] });
					newestNormal = new Vector2(norm[0], norm[1]);

					if (i != 0) {
						normals.add(newestNormal);
						lastNormal = newestNormal;

					}

				}
			}
		}
		return normals;
	}

	public abstract void update(GameContainer container, int arg);

	public abstract void render(GameContainer container, Graphics graphics);

	public Impact getImpact(GameObject agileObject) {
		return null;
	}

	public void wasActionStateSet(Action action) {
	}

	public void animateHit(float aimAtX, float aimAtY) {
	}

	public void removeCurrentImpactsWhichBelingTo(GameObject go) {
		for (Impact im : currentImpacts) {
			if (im.getTrigger() == go || im.getAffected() == go) {
				removeImpact(im);
			}
		}
	}
}
