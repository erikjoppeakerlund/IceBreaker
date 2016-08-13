package se.BaseUlterior.ParallaX;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;

public class ParallaxHolder extends Entity {
	private List<ParallaxDefault> parallaxes;
	protected Parallax startCity;

	public ParallaxHolder(int i) {
		super(new float[0], false, false, false, false, false, true, true);
		parallaxes = new ArrayList<>();
		switch (i) {
		case 0:
			try {
				Image Stars = new Image("res/img/Stars.png");
				float backgrounHeight = Stars.getHeight();
				float backgrounWidth = Stars.getWidth();
				float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
				Parallax parallaxStars = new Parallax(edges, Stars, 0.03f);
				parallaxes.add(parallaxStars);
				ParallaxPhysicsEngine.parallaxBackground.add(parallaxStars);
			} catch (SlickException e) {
				e.printStackTrace();
			}

			try {
				Image cityFar = new Image("res/img/CityFar.png");
				float backgrounHeight = cityFar.getHeight();
				float backgrounWidth = cityFar.getWidth();
				float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
				Parallax parallaxCityFar = new Parallax(edges, cityFar, 0.4f);
				parallaxes.add(parallaxCityFar);
				ParallaxPhysicsEngine.parallaxBackground.add(parallaxCityFar);
			} catch (SlickException e) {
				e.printStackTrace();
			}

			try {
				Image city = new Image("res/img/City.png");
				float backgrounHeight = city.getHeight();
				float backgrounWidth = city.getWidth();
				float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
				Parallax parallaxCity = new Parallax(edges, city, 0.5f);
				parallaxes.add(parallaxCity);
				ParallaxPhysicsEngine.parallaxBackground.add(parallaxCity);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;

		case 1:
			try {
				Image clouds = new Image("res/img/WhiteClouds.png");
				float backgrounHeight = clouds.getHeight();
				float backgrounWidth = clouds.getWidth();
				float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
				Parallax parallaxClouds = new Parallax(edges, clouds, 1.8f);
				parallaxes.add(parallaxClouds);
				ParallaxPhysicsEngine.parallaxBackground.add(parallaxClouds);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			try {
				Image clouds = new Image("res/img/backgroundTexture.jpg");
				float backgrounHeight = clouds.getHeight();
				float backgrounWidth = clouds.getWidth();
				float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
				Parallax parallaxClouds = new Parallax(edges, clouds, 0.3f);
				parallaxes.add(parallaxClouds);
				ParallaxPhysicsEngine.parallaxBackground.add(parallaxClouds);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			float[] edges = { 0, 0, 0, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
					Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0 };
			ParallaxSquares parallaxSquares = new ParallaxSquares(edges, 0.2f);
			parallaxes.add(parallaxSquares);
			ParallaxPhysicsEngine.parallaxBackground.add(parallaxSquares);
			float[] edges2 = { 0, 0, 0, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
					Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0 };
			ParallaxSquares parallaxSquares2 = new ParallaxSquares(edges2, 0.3f);
			parallaxes.add(parallaxSquares2);
			ParallaxPhysicsEngine.parallaxBackground.add(parallaxSquares2);
			float[] edges3 = { 0, 0, 0, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
					Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0 };
			ParallaxSquares parallaxSquares3 = new ParallaxSquares(edges3, 0.4f);
			parallaxes.add(parallaxSquares3);
			ParallaxPhysicsEngine.parallaxBackground.add(parallaxSquares3);
			float[] edges4 = { 0, 0, 0, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
					Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0 };
			ParallaxSquares parallaxSquares4 = new ParallaxSquares(edges4, 0.5f);
			parallaxes.add(parallaxSquares4);
			ParallaxPhysicsEngine.parallaxBackground.add(parallaxSquares4);
			break;
		default:
			break;

		}
	}

	public List<ParallaxDefault> getGameObjects() {
		return this.parallaxes;
	}

	@Override
	public void update(GameContainer container, int arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		for (ParallaxDefault parallax : parallaxes) {
			parallax.render(container, graphics);
		}
	}

}
