package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Geom.Vector2;

public class AITurretVectorShotLauncher extends AITurret {

	public AITurretVectorShotLauncher(float[] points, Vector2 startangle) {
		super(points, startangle);
		this.color = Color.red;
	}

	@Override
	protected void shoot() {
		aim.updateAim();
		aim.shoot();
	}

	@Override
	protected void aim() {

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		aim.render(container, graphics);
	}

}
