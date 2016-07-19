package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/*
 * FIXME: Used as outer border, but currently as frame, should
 * be user as pillars instead in class se.BaseUlterior.Game.GroundMap,
 * to avoid unnecessary impact-check.
 */

public class WorldBuilderGroundSolid extends WorldBuilderGround {

	public WorldBuilderGroundSolid(float[] nodes) {
		super(nodes, 2.3f);
		this.color = Color.transparent;
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		// graphics.setColor(this.color);
		// graphics.fill(this);
	}

}
