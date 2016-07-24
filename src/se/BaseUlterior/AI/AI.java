package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.GameObject.MateriaFirm;

public abstract class AI extends MateriaFirm {
	protected float height;
	protected Color HPColor;

	protected AI(float[] points, float height) {
		// super(points, false, false, false, false, false, true);
		super(points);
		this.height = height;
		HP = 100f;
		HPColor = new Color(0, 1, 0, 0.69f);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		HPColor.r = 1f - HP / 100f;
		HPColor.g = HP / 100f;
		graphics.setColor(HPColor);
		graphics.fillRect(getCenterX() - 60f, getCenterY() + height, 180f * HP / 100f, 12f);
		graphics.setColor(Color.black);
		graphics.resetLineWidth();
		graphics.drawRect(getCenterX() - 60f, getCenterY() + height, 180f, 12f);
	}

}
