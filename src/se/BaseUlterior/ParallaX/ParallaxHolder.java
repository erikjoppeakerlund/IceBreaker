package se.BaseUlterior.ParallaX;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Entity.Entity;

public class ParallaxHolder extends Entity {
	private List<Parallax> parallaxes;
	protected Parallax startCity;

	public ParallaxHolder(int i) {
		super(new float[0], false, false, false, false, false, true);
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
		default:
			break;
		}
	}

	public List<Parallax> getGameObjects() {
		return this.parallaxes;
	}

	@Override
	public void update(GameContainer container, int arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		for (Parallax parallax : parallaxes) {
			parallax.render(container, graphics);
		}
	}

}
