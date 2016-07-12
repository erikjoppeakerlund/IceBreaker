package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Physics.Impact;

public abstract class Component extends GameObject {

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

		float tempWidth = getWidth() * horizontalFloat;
		float tempHeight = getHeight() * verticalFloat;

		for (Component comp : subs) {
			comp.setX(tempWidth);
			comp.setY(tempHeight);
			tempHeight += (comp.getHeight() + comp.getMargin()) * verticalAdjustAlignment;
			tempWidth += (comp.getWidth() + comp.getMargin()) * horizontalAdjustAlignment;
			if (tempWidth + (comp.getWidth() * horizontalAdjustAlignment) > getWidth()) {
				tempWidth = 0;
				tempHeight += comp.getHeight() + comp.getMargin();
			}
			if (tempHeight + (comp.getHeight() * verticalAdjustAlignment) > getHeight()) {
				tempHeight = 0;
				tempWidth += comp.getWidth() + comp.getMargin();
			}
		}
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
		graphics.setColor(this.color);
		graphics.fill(this);

	}

	@Override
	public Impact getImpact(GameObjectFalling agileObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
