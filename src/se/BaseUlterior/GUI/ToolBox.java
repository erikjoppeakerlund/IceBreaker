package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import se.BaseUlterior.Game.BreakingPoint;

public class ToolBox extends Panel {

	private List<Button> buttons = null;

	private static final float styleHeigth = 269;
	private static final float styleWidth = 169;

	private final static float tabSize = 0.0f;

	public ToolBox(Alignment alignment) {
		super(new float[] { 0, 0, styleWidth, 0, styleWidth, styleHeigth - tabSize, styleWidth - tabSize, styleHeigth,
				0, styleHeigth }, alignment);

		buttons = new ArrayList<>();
		init();
		for (Button b : buttons) {
			add(b);
		}
		setForcedHeight((buttons.size() + 1) * Button.PREFERED_HEIGHT);
		setForcedWidth(Button.PREFERED_WIDTH);

	}

	private void init() {
		buttons.add(new Button("STATS", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("COUNTER:FORCES", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("SETTINGS", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("LEVELS", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("(GROUND)", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, !BreakingPoint.insertMode) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("(FRICTION)", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, !BreakingPoint.insertMode) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("(GRAVITY)", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, !BreakingPoint.insertMode) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});

		buttons.add(new Button("MODE:ESC", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				BreakingPoint.insertMode = !BreakingPoint.insertMode;

			}
		});
	}

}
