package se.BaseUlterior.ParallaX;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;

public class ParallaxSquares extends ParallaxDefault {
	private static final long serialVersionUID = -8657229360844031751L;
	float parallaxEffect;

	private float[][] squares;

	private float width;
	private float height;

	private static final int NR_OF_SQUARES = 155;

	private static final float MAX_SIZE_SQUARE = 1000f;

	private static final int LINE_WIDTH_PARALLAX = 41;

	private Color[] colors;

	public ParallaxSquares(float[] edges, float parallaxEffect) {
		super(edges, true, true, true, false, false, true, true);
		this.parallaxEffect = parallaxEffect;
		width = getWidth();
		height = getHeight();
		initSquares();
	}

	private void initSquares() {
		squares = new float[NR_OF_SQUARES][5];

		colors = new Color[NR_OF_SQUARES];
		for (int i = 0; i < NR_OF_SQUARES; i++) {
			float startX = (float) (Math.random() * width);
			float startY = (float) (Math.random() * height);

			float squareWidth = (float) (Math.random() * MAX_SIZE_SQUARE * parallaxEffect);
			float squareHeight = (float) (Math.random() * MAX_SIZE_SQUARE * parallaxEffect);

			squares[i][0] = startX;
			squares[i][1] = startY;

			squares[i][2] = squareWidth;
			squares[i][3] = squareHeight;

			// colors[i] = new Color((float) Math.random(), 0.21f, 0.21f, 0.1f +
			// (float) (Math.random() * 0.9f) * 0.71f);
			colors[i] = new Color(0, 0.3f, 0, 0.6f);
		}
	}

	public void moveParallax(float xParam, float yParam) {
		x = -xParam * parallaxEffect;
		y = -yParam * parallaxEffect;
	}

	@Override
	public void update(GameContainer container, int arg) {
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		// graphics.setColor(colorBg);
		//
		// graphics.fillRect(0, 0, Constants.CANVAS_WIDTH,
		// Constants.CANVAS_HEIGHT);

		graphics.setLineWidth(LINE_WIDTH_PARALLAX * parallaxEffect);
		int i = 0;
		for (float[] sq : squares) {
			if (x + sq[0] > -sq[2] && x + sq[0] < Constants.CANVAS_WIDTH && y + sq[1] > -sq[3]
					&& y + sq[1] < Constants.CANVAS_HEIGHT) {
				// graphics.setColor(colorSquare);
				graphics.setColor(colors[i]);
				graphics.fillRect(x + sq[0], y + sq[1], sq[2], sq[3]);
			}
			i++;
		}

	}

	public void setParallaxEffect(float effect) {
		parallaxEffect = effect;
	}
}
