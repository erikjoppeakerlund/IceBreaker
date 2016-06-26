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

	public Normal makePerpendicular() {
		float newVal1 = -val2;
		val2 = val1;
		val1 = newVal1;
		return this;
	}

	@Override
	public String toString() {
		return "Normal [val1=" + val1 + ", val2=" + val2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(val1);
		result = prime * result + Float.floatToIntBits(val2);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Normal other = (Normal) obj;
		if (Float.floatToIntBits(val1) != Float.floatToIntBits(other.val1))
			return false;
		if (Float.floatToIntBits(val2) != Float.floatToIntBits(other.val2))
			return false;
		return true;
	}

}
