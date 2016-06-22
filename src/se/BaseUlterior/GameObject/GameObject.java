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

	public float[][] getClosestPoints() {
		float closest1 = Constants.CANVAS_SIZE;
		float closest2 = Constants.CANVAS_SIZE;
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
