package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class PartSolidSimple extends PartSolid {

	public PartSolidSimple(float[] nodes) {
		super(nodes, 2.3f);
		this.color = Color.transparent;
		motionLess = true;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
	}

}
