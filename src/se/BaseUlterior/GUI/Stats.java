package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;

public class Stats extends Component {

	private static final int styleHeigth = Constants.STATS_BOX_HEIGHT;
	private static final int styleWidth = Constants.STATS_BOX_WIDTH;

	private final static int tabSize = 50;
	Color colorAlpha = new Color(0.7f, 0.7f, 0.8f, 0.39f);

	TextInfo info;
	SingleText HP;
	SingleText weapon;
	SingleText lastHit;
	SingleText kills;

	public Stats() {
		super(new float[] { 0, 0, styleWidth, 0, styleWidth, styleHeigth - tabSize, styleWidth - tabSize, styleHeigth,
				0, styleHeigth });
		color = colorAlpha;
		// setForcedHeight(300);
		// setForcedWidth(600);
		// initInfo();
	}

	// public void initInfo() {
	// info = new TextInfo(styleWidth / 2, 0, true);
	// HP = new SingleText(0, styleHeigth / 5, 22, "HP: ", Color.black);
	// weapon = new SingleText(0, styleHeigth / 5 * 2, 22, "Weapon: ",
	// Color.black);
	// lastHit = new SingleText(0, styleHeigth / 5 * 3, 22, "Last Hit: ",
	// Color.black);
	// kills = new SingleText(0, styleHeigth / 5 * 4, 22, "Kills: ",
	// Color.black);
	// info.singleTexts.add(HP);
	// info.singleTexts.add(weapon);
	// info.singleTexts.add(lastHit);
	// info.singleTexts.add(kills);
	// IceBreaker.info.textInfos.add(info);
	// }

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(color);
		graphics.fill(this);
		graphics.setColor(Color.black);
		graphics.setLineWidth(Constants.LINE_WIDTH);
		graphics.draw(this);
	}
}
