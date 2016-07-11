package se.BaseUlterior.Context.JSON;

public class JSONArray {

	private Object[] array;

	public JSONArray(Object[] array) {
		this.array = array;
	}

	public int length() {
		return array.length;
	}

	public Object get(int index) {
		return array[index];
	}

	public String getString(int index) {
		return (String) array[index];
	}

	public boolean getBoolean(int index) {
		return (boolean) array[index];
	}

	public int getInteger(int index) {
		return (int) array[index];
	}

	public float getFloat(int index) {
		return (float) array[index];
	}

	public JSONObject getObject(int index) {
		return new JSONObject(array[index]);
	}

	public JSONArray getArray(int index) {
		return new JSONArray((Object[]) array[index]);
	}

}