package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Physics.Density;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactFriction;

/**
 * Game object which getImpact method generates
 * se.BaseUlterior.Physics.ImpactFriction
 * 
 * @author Johan Akerlund
 */
public class PartFriction extends Entity {

	Density density;

	protected float frictionAmount;

	public PartFriction(float[] nodes) {
		super(nodes, false, true, false, false, false, true);
		this.density = Density.WATER;
		piercable = true;
	}

	public PartFriction(float[] nodes, Density density) {
		super(nodes, false, true, false, false, false, true);
		this.density = density;
		init();
		piercable = true;
	}

	public PartFriction(float[] nodes, Density density, boolean isBackground) {
		super(nodes, false, true, false, false, false, true);
		this.density = density;
		piercable = true;
		init();
	}

	protected void init() {
		switch (density) {
		case SOIL:
			this.color = Color.darkGray;
			this.frictionAmount = 0.81f;
			break;
		case MUDD:
			this.color = Color.green;
			this.frictionAmount = 0.83f;
			break;
		case GREECE:
			this.color = Color.magenta;
			this.frictionAmount = 0.88f;
			break;
		case WATER:
			this.color = new Color(0, 0, 0.29f, 0.4f);
			this.frictionAmount = 0.90f;
			break;
		case LAVA:
			this.color = new Color(1, 0, 0, 0.4f);
			this.frictionAmount = 0.86f;
			break;
		case AIR:
			this.color = Color.transparent;
			this.frictionAmount = Constants.GENERA_FRICTION;
			break;
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	@Override
	public Impact getImpact(Entity piece) {
		return new ImpactFriction(this, piece, frictionAmount);
	}

	public void setDensity(Density density) {
		this.density = density;
		init();
	}

	@Override
	public void update(GameContainer container, int arg) {
	}

}
