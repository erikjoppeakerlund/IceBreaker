package se.BaseUlterior.Actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.newdawn.slick.Input;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuiderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class WorldCreator implements ActionListenable {

	private boolean choosingState = false;

	private GameObject wb = null;
	private GameObject wbl = null;
	private GameObject wbf = null;

	GameObject currentBuilder = null;

	private ArrayList<GameObject> worldBuilders = null;

	public WorldCreator() {
		worldBuilders = new ArrayList<>();
		wb = new WorldBuilderGround(new float[0]);
		wbl = new WorldBuilderLiquid(new float[0], Density.WATER);
		wbf = new WorldBuiderForce(new float[0], 0.0f, 0.0f);
		currentBuilder = wb;
		BreakingPoint.objsToAdd.add(currentBuilder);

	}

	@Override
	public void wasDoubleClicked(int button, int x, int y) {
		if (!BreakingPoint.insertMode) {
			return;
		}
		if (!choosingState) {
			choosingState = true;
			return;
		}
		betweenCreationHandler();
		choosingState = false;
	}

	private void betweenCreationHandler() {

		// if (choosingState) {
		// currentBuilder.setPoints(new float[0]);
		// choosingState = false;
		// }

		Class<? extends GameObject> currentInstance = currentBuilder.getClass();
		GameObject newCurrentInstance = null;
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
		BreakingPoint.objsToAdd.add(newCurrentInstance);
		currentBuilder = newCurrentInstance;
	}

	@Override
	public void wasSingleClicked(int button, int x, int y) {
		if (!BreakingPoint.insertMode || choosingState) {
			return;
		}
		if (button == Input.MOUSE_LEFT_BUTTON) {
			currentBuilder.addPoint(x, y);
		}
	}

	@Override
	public void wasWasKeyPressed(int button, char c) {
		if (!BreakingPoint.insertMode) {
			return;
		}
		if (!choosingState) {
			switch (button) {
			case Input.KEY_1:
				currentBuilder = wb;
				BreakingPoint.objsToAdd.add(currentBuilder);
				break;
			case Input.KEY_2:
				currentBuilder = wbl;
				BreakingPoint.objsToAdd.add(currentBuilder);
				break;
			case Input.KEY_3:
				currentBuilder = wbf;
				BreakingPoint.objsToAdd.add(currentBuilder);
				break;
			}
		} else {
			if (currentBuilder instanceof WorldBuilderLiquid) {
				switch (button) {
				case Input.KEY_1:
					((WorldBuilderLiquid) currentBuilder).setDensity(Density.WATER);
					break;
				case Input.KEY_2:
					((WorldBuilderLiquid) currentBuilder).setDensity(Density.GREECE);
					break;
				case Input.KEY_3:
					((WorldBuilderLiquid) currentBuilder).setDensity(Density.SOIL);
					break;
				case Input.KEY_4:
					((WorldBuilderLiquid) currentBuilder).setDensity(Density.MUDD);
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