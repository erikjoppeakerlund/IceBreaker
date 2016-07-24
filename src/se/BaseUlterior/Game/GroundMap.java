package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuilderForce;
import se.BaseUlterior.GameObject.WorldBuilderGroundSolid;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class GroundMap {
	public List<GameObject> levelPieces;

	public GroundMap() {
		levelPieces = new ArrayList<>();
		try {
			Image BackgroundStars = new Image("res/img/Stars.png");
			float backgrounHeight = BackgroundStars.getHeight();
			float backgrounWidth = BackgroundStars.getWidth();
			float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
			Parallax parallaxStars = new Parallax(edges, BackgroundStars, 0.03f);
			levelPieces.add(parallaxStars);
			IceBreaker.parallaxList.add(parallaxStars);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			Image city = new Image("res/img/CityFar.png");
			float backgrounHeight = city.getHeight();
			float backgrounWidth = city.getWidth();
			float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
			Parallax parallaxStars = new Parallax(edges, city, 0.4f);
			levelPieces.add(parallaxStars);
			IceBreaker.parallaxList.add(parallaxStars);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			Image city = new Image("res/img/City.png");
			float backgrounHeight = city.getHeight();
			float backgrounWidth = city.getWidth();
			float[] edges = { 0, 0, 0, backgrounHeight, backgrounWidth, backgrounHeight, backgrounWidth, 0 };
			Parallax parallaxStars = new Parallax(edges, city, 0.5f);
			levelPieces.add(parallaxStars);
			IceBreaker.parallaxList.add(parallaxStars);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		float thickness = 150.0f;

		float[] wallLeftPoints = new float[] { -thickness, -thickness, -thickness, Constants.CANVAS_HEIGHT_FULL + thickness, 0, Constants.CANVAS_HEIGHT_FULL + thickness, 0, -thickness };

		GameObject wallLeft = new WorldBuilderGroundSolid(wallLeftPoints);
		levelPieces.add(wallLeft);

		float[] wallRightPoints = new float[] { Constants.CANVAS_WIDTH_FULL, -thickness, Constants.CANVAS_WIDTH_FULL, Constants.CANVAS_HEIGHT_FULL + thickness, Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL + thickness, Constants.CANVAS_WIDTH_FULL + thickness, -thickness };

		GameObject wallRight = new WorldBuilderGroundSolid(wallRightPoints);
		levelPieces.add(wallRight);

		float[] floorPoints = new float[] { -thickness, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL + thickness, -thickness, Constants.CANVAS_HEIGHT_FULL + thickness, -thickness, Constants.CANVAS_HEIGHT_FULL };

		GameObject floor = new WorldBuilderGroundSolid(floorPoints);
		levelPieces.add(floor);

		float[] roofPoints = new float[] { -thickness, 0, Constants.CANVAS_WIDTH_FULL + thickness, 0, Constants.CANVAS_WIDTH_FULL + thickness, -thickness, -thickness, -thickness };

		GameObject roof = new WorldBuilderGroundSolid(roofPoints);
		levelPieces.add(roof);

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0.0f };

		WorldBuilderForce generalGravity = new WorldBuilderForce(wholeScene, 0, Constants.GENERAL_GRAVITY, Color.transparent);

		levelPieces.add(generalGravity);

		levelPieces.add(new WorldBuilderLiquid(new float[] { 0, 0, 0, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0 }, Density.AIR, true));

	}

}
