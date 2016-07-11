package se.BaseUlterior.Context.JSON;

import java.util.Map;

public class JSONObject {

	private Map<String, Object> map;

	@SuppressWarnings("unchecked")
	public JSONObject(Object object) {
		map = (Map<String, Object>) object;
	}

	public Object get(String key) {
		return map.get(key);
	}

	public String getString(String key) {
		return (String) map.get(key);
	}

	public boolean getBoolean(String key) {
		return (boolean) map.get(key);
	}

	public int getInteger(String key) {
		return (int) map.get(key);
	}

	public float getFloat(String key) {
		return (float) map.get(key);
	}

	public JSONObject getObject(String key) {
		return new JSONObject(map.get(key));
	}

	public JSONArray getArray(String key) {
		return new JSONArray((Object[]) map.get(key));
	}

}