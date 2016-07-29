package se.BaseUlterior.Actions;

import java.lang.reflect.InvocationTargetException;

import org.newdawn.slick.Input;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.PartForce;
import se.BaseUlterior.Entity.PartFriction;
import se.BaseUlterior.Entity.PartSolid;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Physics.Density;

public class WorldCreator implements ActionListenable {

	private boolean choosingState = false;

	private Entity wb = null;
	private Entity wbl = null;
	private Entity wbf = null;

	Entity currentBuilder = null;

	public WorldCreator() {
		wb = new PartSolid(new float[0]);
		wbl = new PartFriction(new float[0], Density.WATER);
		wbf = new PartForce(new float[0], 0.0f, 0.0f);
		currentBuilder = wb;
		ParallaxPhysicsEngine.objsToAdd.add(currentBuilder);

	}

	@Override
	public void wasDoubleClicked(int button, int x, int y) {
		System.out.println();
		if (!choosingState) {
			choosingState = true;
			return;
		}
		betweenCreationHandler();
		choosingState = false;
	}

	private void betweenCreationHandler() {

		Class<? extends Entity> currentInstance = currentBuilder.getClass();
		Entity newCurrentInstance = null;
		try {
			newCurrentInstance = currentInstance.getDeclaredConstructor(float[].class).newInstance(new float[0]);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ParallaxPhysicsEngine.objsToAdd.add(newCurrentInstance);
		currentBuilder = newCurrentInstance;
	}

	@Override
	public void wasSingleClicked(int button, int x, int y) {
		if (choosingState) {
			return;
		}
		if (button == Input.MOUSE_LEFT_BUTTON) {
			currentBuilder.addPoint(x, y);
			System.out.println((int) ((x * Constants.CANVAS_EXTENTION_FACTOR)) + ", "
					+ (int) ((y * Constants.CANVAS_EXTENTION_FACTOR)) + ",");
		}
	}

	@Override
	public void wasWasKeyPressed(int button, char c) {
		if (!choosingState) {
			switch (button) {
			case Input.KEY_1:
				currentBuilder = wb;
				ParallaxPhysicsEngine.objsToAdd.add(currentBuilder);
				break;
			case Input.KEY_2:
				currentBuilder = wbl;
				ParallaxPhysicsEngine.objsToAdd.add(currentBuilder);
				break;
			case Input.KEY_3:
				currentBuilder = wbf;
				ParallaxPhysicsEngine.objsToAdd.add(currentBuilder);
				break;
			}
		} else {
			if (currentBuilder instanceof PartFriction) {
				switch (button) {
				case Input.KEY_1:
					((PartFriction) currentBuilder).setDensity(Density.WATER);
					break;
				case Input.KEY_2:
					((PartFriction) currentBuilder).setDensity(Density.GREECE);
					break;
				case Input.KEY_3:
					((PartFriction) currentBuilder).setDensity(Density.SOIL);
					break;
				case Input.KEY_4:
					((PartFriction) currentBuilder).setDensity(Density.MUDD);
					break;
				}
			}
			choosingState = false;
			betweenCreationHandler();
		}

	}

	@Override
	public void wasWasKeyReleased(int button, char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void wasMouseWheelMoved(int change) {
		// TODO Auto-generated method stub

	}
}
