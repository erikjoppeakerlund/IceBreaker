package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public class WorldBuilderGroundSolid extends WorldBuilderGround {

	public WorldBuilderGroundSolid(float[] nodes) {
		super(nodes, 2.3f);
		this.color = Color.transparent;
		motionLess = true;
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
	}

}
