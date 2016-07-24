package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Physics.Impact;

public abstract class Component extends GameObject {

	protected boolean hide = false;

	protected int forcedWidth;
	protected int forcedHeight;

	protected int marginX = 5;
	protected int marginY = 0;
	protected Component parent = null;

	protected int padding;
	protected Stacking stacking = Stacking.FROM_LEFT;

	protected List<Component> subs = null;
	protected int horizontalAdjustAlignment;
	protected int verticalAdjustAlignment;

	protected int horizontalFloat;
	protected int verticalFloat;

	protected boolean isPaused;

	public Component(float width, float height) {
		super(new float[] { 0, 0, width, 0, width, height, 0, height }, true, true, false, true, false, true);
		init();
	}

	public Component(float[] points) {
		super(points, true, true, false, true, false, true);
		init();
	}

	private void init() {
		checkStacking();
		subs = new ArrayList<>();
		forcedHeight = (int) getHeight();
		forcedWidth = (int) getWidth();
	}

	public void add(Component component) {
		this.subs.add(component);
		this.stack();
		IceBreaker.objsToAdd.add(component);
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
			comp.setX(tempWidth - (comp.getWidth() + comp.getMarginX()) * horizontalFloat);
			comp.setY(tempHeight);
			tempHeight += (comp.getHeight() + comp.getMarginY()) * verticalAdjustAlignment;
			tempWidth += (comp.getWidth() + comp.getMarginX()) * horizontalAdjustAlignment;
			if (tempWidth + (comp.getWidth() * horizontalAdjustAlignment) > forcedWidth
					|| tempWidth + (comp.getWidth() * horizontalAdjustAlignment) < 0) {
				tempWidth = forcedWidth * horizontalFloat;
				tempHeight += comp.getHeight() + comp.getMarginY() * verticalAdjustAlignment;
			}
			if (tempHeight + (comp.getHeight() * verticalAdjustAlignment) > forcedHeight
					|| tempHeight + (comp.getHeight() * verticalAdjustAlignment) < 0) {
				tempHeight = forcedHeight * verticalFloat;
				tempWidth += comp.getWidth() + comp.getMarginX() * horizontalAdjustAlignment;
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

	public float getMarginX() {
		return marginX;
	}

	public float getMarginY() {
		return marginY;
	}

	public void setMarginX(int marginX) {
		this.marginX = marginX;
	}

	public void setMarginY(int marginY) {
		this.marginY = marginY;
	}

	public void pack() {
		for (Component comp : subs) {
			comp.finalAction();
		}
	}

	protected abstract void finalAction();

	@Override
	public void render(GameContainer container, Graphics graphics) {
		if (isPaused) {
			graphics.setColor(this.color);
			graphics.fill(this);
		}

	}

	@Override
	public void update(GameContainer container, int arg) {

		if (isPaused != container.isPaused()) {
			isPaused = container.isPaused();
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
}
