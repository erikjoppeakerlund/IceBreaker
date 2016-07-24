package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class AITurretBulletLauncher extends AITurret {

	public AITurretBulletLauncher(float[] points, Vector2 startangle) {
		super(points, startangle, 100f);
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
		super.render(container, graphics);
	}

	@Override
	public Impact getImpact(GameObject other) {
		return new ImpactBounce(this, other, 1.0f, true);
	}

}
