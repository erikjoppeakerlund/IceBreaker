package se.BaseUlterior.Utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class UlteriorDraw {
	public static void drawGrenade(Graphics g, float x, float y, float rotation) {
		g.setColor(Color.green);
		g.drawOval(x, y, 39, 28, 10);
	}
}
