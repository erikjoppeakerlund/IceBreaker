package se.BaseUlterior.AI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class AITurretBulletLauncher extends AITurret {

	public AITurretBulletLauncher(float[] points, Vector2 startangle) {
		super(points, "res/img/turret.png", IMAGE_SCALE_STANDARD * 0.91f, startangle, 100f,
				Constants.PERFERED_ARM_LENGTH);
		this.color = Color.red;
	}

	@Override
	protected void shoot() {
		aim.shoot();
	}

	@Override
	protected void aim() {

	}

	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		aim.render(container, graphics);
	}

	@Override
	public Impact getImpact(GameObject other) {
		return new ImpactBounce(this, other, 1.0f, true);
	}

}
