package se.BaseUlterior.Context;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class SingleText {

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

	public Font font;
	public TrueTypeFont trueTypeFont;

	public SingleText(float x, float y, int size, String value, Color color) {
		this.x = x;
		this.y = y;
		this.value = value;
		font = new Font("monospaced", Font.BOLD, size);
		trueTypeFont = new TrueTypeFont(font, true);
		this.color = color;
	}
}
