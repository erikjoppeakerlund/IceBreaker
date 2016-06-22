package se.BaseUlterior.GameObject;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.Game;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;

//will most likely be turned into an abstract class...
public class AgileObject extends GameObject {
	private List<Impact> currentImpacts = null;
	private boolean underImpact = false;
	private Vector2 movement = null;

	private Color color = Color.red;

	public AgileObject(float[] nodes) {
		super(nodes);
		movement = new Vector2();
	}

	public AgileObject(Vector2 startMovement, float[] nodes) {
		super(nodes);
		movement = startMovement;
	}

	public void getAffected() {
		underImpact = true;
	}

	public void setCurrentImpact(Impact impact) {
		this.currentImpacts.add(impact);
	}

	@Override
	public void update(GameContainer container, int arg) {
		checkImpact();
		if (underImpact) {
			runImpact();
		}
		this.x += this.movement.getX();
		this.y += this.movement.getY();
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
			float x1 = p.getCenterX();
			float x2 = this.x;

			float y1 = p.getCenterY();
			float y2 = this.y;

			float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			if (p.getBoundingCircleRadius() < dist) {
				if (this.intersects(p)) {
					underImpact = true;
					this.currentImpacts.add(p.getImpact(this));
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
	}

}
