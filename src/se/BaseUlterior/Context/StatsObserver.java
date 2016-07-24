package se.BaseUlterior.Context;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import se.BaseUlterior.Config.Constants;

public class StatsObserver extends Observer {

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
		// if (show || showAllways) {
		float trueX;
		float trueY;
		for (SingleText st : singleTexts) {
			trueX = st.getX();
			trueY = st.getY();
			st.trueTypeFont.drawString(x + trueX, y + trueY, st.getValue(), st.color);
		}
		// }
	}

	public StatsObserver(Info info, float x, float y, boolean showAllways) {
		this.x = x;
		this.y = y;
		this.showAllways = showAllways;
		this.info = info;

		HP = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5, 22, "HP: ", Color.black);
		weapon = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 * 2, 22, "Weapon: ", Color.black);
		lastHit = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 * 3, 22, "Last Hit: ", Color.black);
		kills = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 * 4, 22, "Kills: ", Color.black);
	}

	SingleText HP;
	SingleText weapon;
	SingleText lastHit;
	SingleText kills;

	@Override
	public void updateData() {
	}
}
