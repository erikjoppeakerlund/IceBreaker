package se.BaseUlterior.Context;

import java.util.ArrayList;

public class TextInfo {

	public ArrayList<SingleText> singleTexts = new ArrayList<>();

	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void draw() {
		float trueX;
		float trueY;
		for (SingleText st : singleTexts) {
			trueX = st.getX();
			trueY = st.getY();
			st.trueTypeFont.drawString(x + trueX, y + trueY, st.getValue(), st.color);
		}
	}

	public TextInfo(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
