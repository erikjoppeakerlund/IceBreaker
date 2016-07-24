package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.StatsObserver;

public class Stats extends Component {

	private static final int styleHeigth = Constants.STATS_BOX_HEIGHT;
	private static final int styleWidth = Constants.STATS_BOX_WIDTH;

	private final static int tabSize = 50;
	Color colorAlpha = new Color(0.7f, 0.7f, 0.8f, 0.39f);

	// TextInfo info;
	SingleText HP;
	SingleText weapon;
	SingleText lastHit;
	SingleText kills;
	StatsObserver stats;

	public Stats() {
		super(new float[] { 0, 0, styleWidth, 0, styleWidth, styleHeigth - tabSize, styleWidth - tabSize, styleHeigth,
				0, styleHeigth });
		color = colorAlpha;
		stats = new StatsObserver(Constants.STATS_BOX_WIDTH / 2, 0);
	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) {
		// super.update(container, arg);
		// if (isPaused) {
		// if (this.getX() < 0) {
		// setX(getX() + 7f);
		// }
		// } else {
		// if (this.getX() > this.getWidth() / 4) {
		// setX(getX() - 7f);
		// }
		// }

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(color);
		graphics.fill(this);
		graphics.setColor(Color.black);
		graphics.setLineWidth(Constants.LINE_WIDTH);
		graphics.draw(this);
		// stats.draw();
	}
}
