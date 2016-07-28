package se.BaseUlterior.Context;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class SingleText {

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	Color color;

	private String value;

	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font font;
	public TrueTypeFont trueTypeFont;

	public SingleText(float x, float y, int size, String value, Color color, String style) {
		this.x = x;
		this.y = y;
		this.value = value;
		font = new Font(style, Font.PLAIN, size);
		trueTypeFont = new TrueTypeFont(font, true);
		this.color = color;
	}

	public void drawString() {
		trueTypeFont.drawString(x, y, value, color);
	}
}
