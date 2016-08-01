package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Entity.Materia;

/**
 * @author Johan Akerlund
 */

public abstract class AIMotionLess extends Materia {
	protected float height;
	protected Color HPColor;
	protected float dHP;

	private final float LIFE_AMOUNT_LINE_WIDTH = 4;

	protected AIMotionLess(float[] points, float height) {
		super(points);
		START_HP = 100f;
		this.height = height;
		HP = START_HP;
		dHP = HP;
		HPColor = new Color(0, 1, 0, 0.69f);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		HPColor.r = 1f - HP / START_HP;
		HPColor.g = HP / START_HP;
		graphics.setColor(HPColor);
		graphics.fillRect(getCenterX() - 60f, getCenterY() + height, 180f * HP / START_HP, 12f);
		graphics.setColor(Color.black);
		graphics.setLineWidth(LIFE_AMOUNT_LINE_WIDTH);
		graphics.drawRect(getCenterX() - 60f, getCenterY() + height, 180f, 12f);
		graphics.resetLineWidth();
	}

}
