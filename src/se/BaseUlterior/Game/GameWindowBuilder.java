package se.BaseUlterior.Game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;

import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.util.Log;

public class GameWindowBuilder {
	protected Panel toolbox;
	protected Frame mainFrame;

	public GameWindowBuilder(CanvasGameContainer container) {
		init();
		try {
			mainFrame.add(toolbox, BorderLayout.LINE_END);
			toolbox.setLayout(new BoxLayout(toolbox, BoxLayout.PAGE_AXIS));

			Button buttonGround = new Button("GROUND");
			buttonGround.setMaximumSize(new Dimension(95, 45));
			toolbox.add(buttonGround);

			Button buttonForce = new Button("FORCE");
			buttonForce.setMaximumSize(new Dimension(95, 45));
			toolbox.add(buttonForce);

			Button buttonFriction = new Button("FRICTION");
			buttonFriction.setMaximumSize(new Dimension(95, 45));
			toolbox.add(buttonFriction);

			mainFrame.add(container);
			mainFrame.pack();
			mainFrame.setVisible(true);

			container.start();
		} catch (Exception e) {
			Log.error(e);
		}

	}

	private void init() {
		mainFrame = new Frame("[ICE BREAKER]");

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		toolbox = new Panel();
		toolbox.setBackground(Color.BLACK);
	}

	public void inserMode() {
		toolbox.setVisible(true);

	}

	public void actionMode() {
		toolbox.setVisible(false);
	}
}
