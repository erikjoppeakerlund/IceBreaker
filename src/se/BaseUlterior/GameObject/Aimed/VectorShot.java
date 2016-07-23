package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.MateriaFirm;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactExplosion;

public class VectorShot extends MateriaFirm {

	private final float power = 10f;
	private final float sizeOfExplosion = 50f;
	private Vector2 forcedMotionVector;
	private final float SPEED = Constants.VECTOR_SHOOOT_SPEED;

	public VectorShot(float x, float y, Vector2 angleVector) {
		super(new Circle(x, y, Constants.VECTOR_SHOT_RADIUS, 10).getPoints());
		forcedMotionVector = angleVector.copy().normalise().scale(SPEED);
		this.color = Color.red;
	}

	@Override
	public void update(GameContainer container, int delta) {
		setCenterX(getCenterX() + this.forcedMotionVector.getX() * delta);
		setCenterY(getCenterY() + this.forcedMotionVector.getY() * delta);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		graphics.setLineWidth(2);
		graphics.draw(this);
		graphics.resetLineWidth();
	}

	@Override
	public Impact getImpact(GameObject piece) {
		// we might have to put an animation right here...
		return new ImpactExplosion(this, piece, power, sizeOfExplosion);
	}

}
