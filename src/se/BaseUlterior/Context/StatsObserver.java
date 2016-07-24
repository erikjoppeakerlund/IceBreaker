package se.BaseUlterior.Context;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;

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

	public StatsObserver(float x, float y) {
		this.info = IceBreaker.gameInfo;

		HP = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 - 25, 22, "HP: ", Color.black);
		weapon = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 * 2 - 25, 22, "WEAPON: ", Color.black);
		lastHit = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 * 3 - 25, 22, "LAST HIT: ", Color.black);
		kills = new SingleText(0, Constants.STATS_BOX_HEIGHT / 5 * 4 - 25, 22, "KILLS: ", Color.black);
		singleTexts.add(HP);
		singleTexts.add(weapon);
		singleTexts.add(lastHit);
		singleTexts.add(kills);
		this.x = x;
		this.y = y;
	}

	private SingleText HP;
	private SingleText weapon;
	private SingleText lastHit;
	private SingleText kills;

	@Override
	public void updateData() {

		HP.setValue("HP: " + info.getHP());
		weapon.setValue("WEAPON: " + info.getWeapon());
		lastHit.setValue("LAST HIT: " + info.getLastHit().toString());
		kills.setValue("KILLS: " + info.getKills());
	}
}
