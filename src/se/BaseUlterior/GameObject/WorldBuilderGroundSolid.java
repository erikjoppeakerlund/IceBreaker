package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class WorldBuilderGroundSolid extends WorldBuilderGround {

	public WorldBuilderGroundSolid(float[] nodes, Color color) {
		super(nodes, color);
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
