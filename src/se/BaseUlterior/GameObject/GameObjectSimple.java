package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactRicochet;

public class GameObjectSimple extends GameObject {

	private Color color = Color.yellow;

	GameObject target = null;

	private final int LIFE_SPAN_LIMIT = 4;
	private int lifeSpan = 0;

	public GameObjectSimple(float[] fs, GameObject target) {
		super(fs);
		this.target = target;
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			BreakingPoint.objsToRemove.add(this);
		}
		lifeSpan++;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		// graphics.setColor(color);
		// graphics.fill(this);

	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		return new ImpactRicochet(target, this);
	}

}
