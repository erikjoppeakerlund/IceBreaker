package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.EntityAgile;

/**
 * @author Johan Akerlund
 */

public class AISprite extends EntityAgile {
	protected float height;
	protected Color HPColor;
	protected float dHP;
	private boolean underHit;

	private int hitItr = 0;

	private Color hitColor;

	protected AISprite(float height) {
		super(new Circle(Constants.CANVAS_WIDTH / 4, Constants.CANVAS_HEIGHT / 2, Constants.SPRITE_RADIUS * 0.7f)
				.getPoints(), 0f);
		this.height = height;
		START_HP = 300;
		HP = START_HP;
		dHP = HP;
		HPColor = new Color(0, 1, 0, 0.69f);
		motionLess = false;
		color = Color.black;
		hitColor = new Color(0, 0, 0, 0.3f);
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (HP != dHP) {
			underHit = true;
			dHP = HP;
		}
		if (underHit) {
			hitItr++;
			if (hitItr % 4 == 0) {
				isFlashed = !isFlashed;
			}
		}
		if (hitItr > Constants.HIT_LENGTH_FRAMES) {
			hitItr = 0;
			underHit = false;
		}
	}

	boolean isFlashed;

	@Override
	public void render(GameContainer container, Graphics graphics) {
		if (!underHit) {
			graphics.setColor(this.color);
		} else {
			if (isFlashed) {
				graphics.setColor(color);
				graphics.fill(this);
			} else {
				graphics.setColor(Color.white);
			}
		}
		graphics.fill(this);

		HPColor.r = 1f - HP / 100f;
		HPColor.g = HP / 100f;
		graphics.setColor(HPColor);
		graphics.fillRect(getCenterX() - 43f, getCenterY() + height, 96f * HP / START_HP, 12f);
		graphics.setColor(Color.black);
		graphics.resetLineWidth();
		graphics.drawRect(getCenterX() - 43f, getCenterY() + height, 96f, 12f);
	}

}
