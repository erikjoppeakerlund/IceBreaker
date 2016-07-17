package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Physics.Impact;

public abstract class Component extends GameObject {

	protected boolean hide = false;

	protected int forcedWidth;
	protected int forcedHeight;

	protected int margin = 5;
	protected Component parent = null;

	protected int padding;
	protected Stacking stacking = Stacking.FROM_LEFT;

	protected List<Component> subs = null;
	protected int horizontalAdjustAlignment;
	protected int verticalAdjustAlignment;

	protected int horizontalFloat;
	protected int verticalFloat;

	public Component(float width, float height) {
		super(new float[] { 0, 0, width, 0, width, height, 0, height });
		init();
	}

	public Component(float[] points) {
		super(points);
		init();
	}

	private void init() {
		checkStacking();
		subs = new ArrayList<>();
		forcedHeight = (int) getHeight();
		forcedWidth = (int) getWidth();
		isBackgroundObj = true;
	}

	public void add(Component component) {
		this.subs.add(component);
		this.stack();
		BreakingPoint.objsToAdd.add(component);
		// this.parent = parent;
	}

	private void checkStacking() {
		switch (stacking) {
		case FROM_LEFT:
			horizontalAdjustAlignment = 1;
			verticalAdjustAlignment = 0;
			horizontalFloat = 0;
			verticalFloat = 0;
			break;
		case FROM_RIGHT:
			horizontalAdjustAlignment = -1;
			verticalAdjustAlignment = 0;
			horizontalFloat = 1;
			verticalFloat = 0;
			break;
		case FROM_TOP:
			horizontalAdjustAlignment = 0;
			verticalAdjustAlignment = 1;
			horizontalFloat = 0;
			verticalFloat = 0;
			break;
		case FROM_BOTTOM:
			horizontalAdjustAlignment = 0;
			verticalAdjustAlignment = -1;
			horizontalFloat = 0;
			verticalFloat = 1;
			break;
		}
	}

	public void setContentAlignment() {
		checkStacking();
	}

	public void setContentStacking(Stacking stacking) {
		this.stacking = stacking;
		checkStacking();
	}

	private void stack() {

		float tempWidth = forcedWidth * horizontalFloat;
		float tempHeight = forcedHeight * verticalFloat;

		for (Component comp : subs) {
			comp.setX(tempWidth);
			comp.setY(tempHeight);
			tempHeight += (comp.getHeight() + comp.getMargin()) * verticalAdjustAlignment;
			tempWidth += (comp.getWidth() + comp.getMargin()) * horizontalAdjustAlignment;
			if (tempWidth + (comp.getWidth() * horizontalAdjustAlignment) > forcedWidth) {
				tempWidth = 0;
				tempHeight += comp.getHeight() + comp.getMargin();
			}
			if (tempHeight + (comp.getHeight() * verticalAdjustAlignment) > forcedHeight) {
				tempHeight = 0;
				tempWidth += comp.getWidth() + comp.getMargin();
			}
		}
	}

	public void setForcedWidth(int forcedWidth) {
		this.forcedWidth = forcedWidth;
		stack();
	}

	public void setForcedHeight(int forcedHeight) {
		this.forcedHeight = forcedHeight;
		stack();
	}

	public float getMargin() {
		return margin;
	}

	private void wasBreakingLine() {

	}

	public void pack() {
		for (Component comp : subs) {
			comp.finalAction();
		}
	}

	protected abstract void finalAction();

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		if (!hide) {
			graphics.setColor(this.color);
			graphics.fill(this);
		}

	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void putOnTop() {
		// for (GameObject sub : subs) {
		// BreakingPoint.addOnTop(sub);
		// sub.putOnTop();
		// }
	}

	@Override
	public void wasActionStateSet(Action action) {
		switch (action) {
		case INSERT_MODE:
			hide = true;
			break;
		case PAUSE:
			hide = true;
			break;
		case ACTION_MODE_DESKTOP:
			hide = false;
		}
	}
}
