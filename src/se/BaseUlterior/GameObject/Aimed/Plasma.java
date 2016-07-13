package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Utils.UlteriorUtils;

public class Plasma extends GameObjectFalling {

	private boolean hitWall = false;

	public Plasma() {
		super(new Circle(-80, -80, 30).getPoints(), 0.0f);
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (!hitWall) {
			super.update(container, delta);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.orange);
		graphics.fill(this);
	}

	@Override
	protected void checkImpact() {
		for (GameObject go : BreakingPoint.all) {

			if (go == this) {
				continue;
			}

			if (UlteriorUtils.isWithinRange(go, this)) {
				if (go.intersects(this) || go.contains(this)) {
					boolean contains = false;
					for (Impact im : currentImpacts) {
						if (im.getTrigger() == go || im.getAffected().noForce) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						Impact im = go.getImpact(this);
						if (im != null) {
							currentImpacts.add(im);
							if (im.getTrigger() instanceof WorldBuilderGround) {
								hitWall = true;
							}
							underImpact = true;
						}
					}
				}
			}

		}
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
	}

}
