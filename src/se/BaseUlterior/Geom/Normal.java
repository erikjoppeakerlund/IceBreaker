package se.BaseUlterior.Geom;

public class Normal {

	private float val1;
	private float val2;

	public float getVal1() {
		return val1;
	}

	public float getVal2() {
		return val2;
	}

	public Normal(float val1, float val2) {
		this.val1 = val1;
		this.val2 = val2;
	}

	@Override
	public boolean equals(Object obj) {
		Normal otherMyClass = (Normal) obj;
		return (otherMyClass.getVal1() == val1 && otherMyClass.getVal2() == val2);
	}

}
