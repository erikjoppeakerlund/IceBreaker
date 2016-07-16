package se.BaseUlterior.GUI;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import se.BaseUlterior.Game.BreakingPoint;

public class ToolBox extends Panel {

	private List<Button> buttons = null;

	private static final float styleHeigth = 200;
	private static final float styleWidth = 500;

	private final static float tabSize = 50f;

	Color colorAlpha = new Color(0.7f, 0.7f, 0.8f, 0.39f);

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
		buttons.add(new Button("WEAPONS::SCROLL", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

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
		buttons.add(new Button("MAP SELECT", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, true) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		buttons.add(new Button("(CHEET)", Button.PREFERED_WIDTH, Button.PREFERED_HEIGHT, !BreakingPoint.insertMode) {

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

}
