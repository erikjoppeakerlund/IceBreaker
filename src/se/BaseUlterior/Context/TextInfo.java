package se.BaseUlterior.Context;

import java.util.ArrayList;

import se.BaseUlterior.Actions.Action;

public class TextInfo {

	public ArrayList<SingleText> singleTexts = new ArrayList<>();

	private float x;
	private float y;

	protected boolean showAllways = true;

	protected boolean show = true;

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
		if (!show && !showAllways) {
			return;
		}
		float trueX;
		float trueY;
		for (SingleText st : singleTexts) {
			trueX = st.getX();
			trueY = st.getY();
			st.trueTypeFont.drawString(x + trueX, y + trueY, st.getValue(), st.color);
		}
	}

	public TextInfo(float x, float y, boolean showAllways) {
		this.x = x;
		this.y = y;
		this.showAllways = showAllways;
	}

	public void wasShowAction(Action action) {
		switch (action) {
		case INSERT_MODE:
			show = false;
			break;
		case ACTION_MODE:
			show = true;
		}
	}
}
