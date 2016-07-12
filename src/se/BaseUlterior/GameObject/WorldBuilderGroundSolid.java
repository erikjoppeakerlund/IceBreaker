package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class WorldBuilderGroundSolid extends WorldBuilderGround {

	public WorldBuilderGroundSolid(float[] nodes) {
		super(nodes);
		this.color = Color.transparent;
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
