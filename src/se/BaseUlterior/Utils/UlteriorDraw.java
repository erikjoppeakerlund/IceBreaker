package se.BaseUlterior.Utils;

import org.newdawn.slick.Color;
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

	public static void aim(Graphics g, float x, float y, float radius) {
		Color starColor = new Color(1f, 0f, 0f, 0.5f);
		final float EDGES = 15;
		g.setLineWidth(4);
		g.setColor(starColor);
		// dummy
		float valley = 4f;

		float chunks = (float) (Math.PI * 2 / (EDGES * 2));
		for (int i = 0; i <= EDGES * 3 + 3; i += 3) {
			float itr = (float) i;
			float currentLap = itr * chunks / (float) Math.PI * 2f;
			float currentLap2 = (itr + 1) * chunks / (float) Math.PI * 2f;
			float currentLap3 = (itr + 2) * chunks / (float) Math.PI * 2f;
			g.drawLine(x + (float) Math.cos(currentLap) * radius, y + (float) Math.sin(currentLap) * radius,
					x + (float) Math.cos(currentLap2) * (radius + valley),
					y + (float) Math.sin(currentLap2) * (radius + valley));
			g.drawLine(x + (float) Math.cos(currentLap2) * (radius + valley),
					y + (float) Math.sin(currentLap2) * (radius + valley), x + (float) Math.cos(currentLap3) * radius,
					y + (float) Math.sin(currentLap3) * radius);
		}

	}
}
