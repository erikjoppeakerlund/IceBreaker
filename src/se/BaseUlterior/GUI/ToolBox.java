package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * @author Johan Akerlund
 */

public class ToolBox extends Component {

	private List<Button> buttons = null;

	private static final int styleHeigth = 149;
	private static final int styleWidth = 550;

	private final static int tabSize = 50;

	// Color colorAlpha = new Color(0.7f, 0.7f, 0.8f, 0.39f);
	Color colorAlpha = new Color(0.0f, 0.0f, 0.0f, 0.59f);

	public ToolBox(Alignment alignment) {
		super(new float[] { 0, 0, styleWidth, 0, styleWidth, styleHeigth - tabSize, styleWidth - tabSize, styleHeigth,
				0, styleHeigth });
		color = colorAlpha;

		buttons = new ArrayList<>();
		init();
		for (Button b : buttons) {
			add(b);
		}
		// setForcedHeight(300);
		// setForcedWidth(600);

	}

	private void init() {
		buttons.add(new Button("STATS", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("WEAPONS::Q", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("EXIT", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				System.exit(0);
			}
		});
		buttons.add(new Button("MAP SELECT", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});

		buttons.add(new Button(ParallaxPhysicsEngine.MODE_LATEST_ACTION.toString(), Button.PREFERED_WIDTH,
				Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				if (ParallaxPhysicsEngine.MODE_LATEST_ACTION == Action.ACTION_MODE_DESKTOP) {
					ParallaxPhysicsEngine.setSprite(Action.ACTION_MODE_CELL);
					ParallaxPhysicsEngine.MODE_LATEST_ACTION = Action.ACTION_MODE_CELL;
					Aim.mobile = true;
				} else if (ParallaxPhysicsEngine.MODE_LATEST_ACTION == Action.ACTION_MODE_CELL) {
					ParallaxPhysicsEngine.setSprite(Action.ACTION_MODE_DESKTOP);
					ParallaxPhysicsEngine.MODE_LATEST_ACTION = Action.ACTION_MODE_DESKTOP;
					Aim.mobile = false;
				}
				updateText(ParallaxPhysicsEngine.MODE_LATEST_ACTION.toString());
			}
		});
		buttons.add(new Button("ABOUT", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("INSTRUCTIONS", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("DIFFICULTY", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void finalAction() {

	}

}
