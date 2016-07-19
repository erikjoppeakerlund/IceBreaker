package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactRicochet;

public class GameObjectRicochet extends GameObject {

	private Color color = Color.yellow;

	GameObject target = null;

	private int LIFE_SPAN_LIMIT = 4;
	private int lifeSpan = 0;
	private float gunFireStartAtX;
	private float gunFireStartAtY;
	private float aimAtX;
	private float aimAtY;
	private Color rayColor = new Color(0.61f, 0.61f, 0.61f);
	private float weight;
	private float startX;
	private float startY;

	public GameObjectRicochet(float[] fs, GameObject target, float gunFireStartAtX, float gunFireStartAtY, float aimAtX,
			float aimAtY, float weight) {
		super(fs);
		this.target = target;
		this.gunFireStartAtX = gunFireStartAtX;
		this.gunFireStartAtY = gunFireStartAtY;
		this.aimAtX = aimAtX;
		this.aimAtY = aimAtY;
		this.weight = weight;
		this.LIFE_SPAN_LIMIT *= weight;
		startX = getX();
		startY = getY();
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
		rayColor.a = 1f - (float) lifeSpan / (float) LIFE_SPAN_LIMIT;
		graphics.setColor(rayColor);
		graphics.setLineWidth(weight * 2f);
		float newX = getX();
		float newY = getY();
		graphics.drawLine(gunFireStartAtX + newX - startX, gunFireStartAtY + newY - startY, aimAtX + newX - startX,
				aimAtY + newY - startY);

	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		return new ImpactRicochet(target, this);
	}

}
