package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.Game;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

//will most likely be turned into an abstract class...
public class AgileObject extends GameObject {
	private List<Impact> currentImpacts = null;
	private boolean underImpact = false;
	private Vector2 movement = null;

	private Impact currentForce = null;

	private Color color = Color.red;

	public AgileObject(float[] nodes) {
		super(nodes);
		movement = new Vector2();
		currentImpacts = new ArrayList<>();
		currentForce = Game.generalGravity.getImpact(this);
	}

	public AgileObject(Vector2 startMovement, float[] nodes) {
		super(nodes);
		movement = startMovement;
		currentImpacts = new ArrayList<>();
		currentForce = Game.generalGravity.getImpact(this);
	}

	public void getAffected() {
		underImpact = true;
	}

	@Override
	public void update(GameContainer container, int arg) {
		if (currentForce != null) {
			currentForce.calculateEffect(this.movement);
		}

		checkImpact();
		if (underImpact) {
			runImpact();
		}
		this.setX(this.getX() + this.movement.getX());
		this.setY(this.getY() + this.movement.getY());
	}

	private void runImpact() {

		int[] removeIndexes = new int[currentImpacts.size()];
		int i = 0;

		for (Impact im : currentImpacts) {
			im.calculateEffect(movement);
			if (!im.getOrigin().contains(this)) {
				removeIndexes[i] = 1;
			}
			i++;
		}
		for (int j = 0; j < removeIndexes.length; j++) {
			if (removeIndexes[j] == 1) {
				currentImpacts.remove(j);
			}
		}
		if (currentImpacts.isEmpty()) {
			underImpact = false;
		}

	}

	private void checkImpact() {
		for (GameObject p : Game.all) {
			if (p == this || p == currentForce.getOrigin()) {
				return;
			}
			float x1 = p.getCenterX();
			float x2 = this.x;

			float y1 = p.getCenterY();
			float y2 = this.y;

			float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			if (p.getBoundingCircleRadius() < dist) {
				if (this.intersects(p)) {
					Impact i = p.getImpact(this);

					if (i instanceof ImpactForce) {
						currentForce = i;
					} else {
						this.currentImpacts.add(p.getImpact(this));
					}

					underImpact = true;
				}
			}
		}
	}

	public Vector2 getMovement() {
		return movement;
	}

	public void setMovement(Vector2 movement) {
		this.movement = movement;
	}

	@Override
	public Impact getImpact(AgileObject piece) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
		this.checkPoints();
	}

}
