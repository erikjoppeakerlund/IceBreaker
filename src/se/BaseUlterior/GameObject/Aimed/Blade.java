package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Physics.Impact;

public class Blade extends GameObject {

	protected Polygon me = null;

	protected float angle;

	public Blade() {
		// super(new float[] { 0, 50, 30, 20, 90, 81 });
		me = new Polygon(new float[] { 0, 50, 30, 20, 90, 81 });
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.white);
		graphics.fill(me);
	}

	@Override
	public Impact getImpact(GameObjectFalling agileObject) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Polygon me() {
		return me;
	}

	public void seteMe(Polygon me) {
		this.me = me;
	}

	@Override
	public void setCenterX(float centerX) {
		me.setCenterX(centerX);
	}

	@Override
	public void setCenterY(float centerY) {
		me.setCenterY(centerY);
	}

}
