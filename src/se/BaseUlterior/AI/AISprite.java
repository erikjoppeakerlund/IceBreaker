package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.GameObject.GameObjectSprite;

/**
 * @author Johan Akerlund
 */

public class AISprite extends GameObjectSprite {
	protected float height;
	protected Color HPColor;
	protected float dHP;

	protected AISprite(float height) {
		super();
		this.height = height;
		START_HP = 300;
		HP = START_HP;
		HPColor = new Color(0, 1, 0, 0.69f);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		HPColor.r = 1f - HP / 100f;
		HPColor.g = HP / 100f;
		graphics.setColor(HPColor);
		graphics.fillRect(getCenterX() - 43f, getCenterY() + height, 96f * HP / START_HP, 12f);
		graphics.setColor(Color.black);
		graphics.resetLineWidth();
		graphics.drawRect(getCenterX() - 43f, getCenterY() + height, 96f, 12f);
	}

}
