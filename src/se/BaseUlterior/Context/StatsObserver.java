package se.BaseUlterior.Context;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

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
		float trueX;
		float trueY;
		for (SingleText st : singleTexts) {
			trueX = st.getX();
			trueY = st.getY();
			st.trueTypeFont.drawString(x + trueX, y + trueY, st.getValue(), st.color);
		}
	}

	public StatsObserver(float x, float y) {
		this.info = ParallaxPhysicsEngine.gameInfo;
		Color fontColor = Color.green;

		HP = new SingleText(Constants.CANVAS_WIDTH - 200, 50, 24, "HP: ", fontColor, "SANS_SERIF");
		weapon = new SingleText(Constants.CANVAS_WIDTH - 450, 50, 24, "WEAPON: ", fontColor, "SANS_SERIF");
		kills = new SingleText(Constants.CANVAS_WIDTH - 700, 50, 24, "KILLS: ", fontColor, "SANS_SERIF");
		sec = new SingleText(Constants.CANVAS_WIDTH - 850, 50, 24, "SEC: ", fontColor, "SANS_SERIF");
		singleTexts.add(HP);
		singleTexts.add(weapon);
		singleTexts.add(kills);
		singleTexts.add(sec);
		this.x = x;
		this.y = y;
	}

	public void setTime(int sec) {
		this.sec.setValue("SEC: " + sec);
	}

	private SingleText HP;
	private SingleText weapon;
	private SingleText kills;
	private SingleText sec;

	@Override
	public void updateData() {

		HP.setValue("HP: " + info.getHP());
		weapon.setValue("WEAPON: " + info.getWeapon());
		kills.setValue("KILLS: " + info.getKills() + "/" + ParallaxPhysicsEngine.nrOfTurrets);
	}
}
