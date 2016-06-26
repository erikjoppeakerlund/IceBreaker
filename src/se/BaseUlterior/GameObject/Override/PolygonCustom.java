package se.BaseUlterior.GameObject.Override;

import org.newdawn.slick.geom.Polygon;

public class PolygonCustom extends Polygon {
	public void setPoints(float[] points) {
		this.points = points;
	}

	public void findCenterPublic() {
		findCenter();
	}

}
