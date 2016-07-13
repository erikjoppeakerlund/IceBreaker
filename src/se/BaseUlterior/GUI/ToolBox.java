package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import se.BaseUlterior.Game.BreakingPoint;

public class ToolBox extends Panel {

	private List<Button> buttons = null;

	private static final float styleHeigth = 500;
	private static final float styleWidth = 400;

	private final static float tabSize = 50f;

	public ToolBox(Alignment alignment) {
		super(new float[] { 0, 0, styleWidth, 0, styleWidth, styleHeigth - tabSize, styleWidth - tabSize, styleHeigth,
				0, styleHeigth }, alignment);
		this.color = Color.transparent;

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
		buttons.add(new Button("ABOUT", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
	}

}
