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

	private boolean hide = false;

	protected float forcedWidth;
	protected float forcedHeight;

	protected float margin = 5.0f;

	protected float maxWidth = -1;
	protected float minWidth = -1;
	protected float maxHeight = -1;
	protected float minHeight = -1;

	public void setMaxWidth(float maxWidth) {
		this.maxWidth = maxWidth;
	}

	public float getMaxWidth() {
		return maxWidth;
	}

	protected Component parent = null;

	protected int padding;
	protected Stacking stacking = Stacking.FROM_LEFT;

	protected List<Component> subs = null;
	protected float horizontalAdjustAlignment;
	protected float verticalAdjustAlignment;

	protected float horizontalFloat;
	protected float verticalFloat;

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
		forcedHeight = getHeight();
		forcedWidth = getWidth();
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
			horizontalAdjustAlignment = 1.0f;
			verticalAdjustAlignment = 0.0f;
			horizontalFloat = 0f;
			verticalFloat = 0f;
			break;
		case FROM_RIGHT:
			horizontalAdjustAlignment = -1.0f;
			verticalAdjustAlignment = 0.0f;
			horizontalFloat = 1.0f;
			verticalFloat = 0f;
			break;
		case FROM_TOP:
			horizontalAdjustAlignment = 0.0f;
			verticalAdjustAlignment = 1.0f;
			horizontalFloat = 0f;
			verticalFloat = 0f;
			break;
		case FROM_BOTTOM:
			horizontalAdjustAlignment = 0.0f;
			verticalAdjustAlignment = -1.0f;
			horizontalFloat = 0f;
			verticalFloat = 1.0f;
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

	public void setForcedWidth(float forcedWidth) {
		this.forcedWidth = forcedWidth;
		stack();
	}

	public void setForcedHeight(float forcedHeight) {
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
		for (GameObject sub : subs) {
			// BreakingPoint.addOnTop(sub);
			sub.putOnTop();
		}
	}

	@Override
	public void wasActionStateSet(Action action) {
		switch (action) {
		case INSERT_MODE:
			hide = true;
			break;
		case ACTION_MODE:
			hide = false;
		}
	}
}
