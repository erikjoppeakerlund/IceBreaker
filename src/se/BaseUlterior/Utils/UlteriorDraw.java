package se.BaseUlterior.Utils;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class UlteriorDraw {
	public static void drawRicochet(Graphics g, float x, float y, float towards) {
		Shape explosion = new Polygon(new float[] { 0, 20, 20, 40, 20, 0 });
		explosion = explosion.transform(Transform.createRotateTransform(towards));
		explosion.setCenterX(x);
		explosion.setCenterY(y);
		g.fill(explosion);
	}
}
