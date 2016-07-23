package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Geom.Vector2;

public class AITurretVectorShotLauncher extends AITurret {

	private float vectorShotSpeed;

	public AITurretVectorShotLauncher(float[] points, Vector2 startangle) {
		super(points, startangle);
		vectorShotSpeed = Constants.VECTOR_SHOOOT_SPEED;
		this.color = Color.red;
	}

	@Override
	protected void shoot() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void aim() {

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		aim.render(container, graphics);
	}

}
