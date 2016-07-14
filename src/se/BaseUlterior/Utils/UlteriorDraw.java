package se.BaseUlterior.Utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class UlteriorDraw {
	public static void drawWithAlphe(Graphics g, float aplha, float[] points, Color color) {
	}

	public static void drawBorder(Graphics g, float[] points) {
		for (int i = 0; i < points.length - 2; i += 2) {
			g.drawLine(points[i], points[i + 1], points[i + 2], points[i + 3]);
		}
	}
}
