package se.BaseUlterior.Context.JSON;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSONFile {

	public static final JSONObject parseFromPath(String path) {
		return parseFromString(TextFile.readFromPath(path));
	}

	public static final JSONObject parseFromResource(String resource) {
		return parseFromString(TextFile.readFromResource(resource));
	}

	public static final JSONObject parseFromURL(String url) {
		return parseFromString(TextFile.readFromURL(url));
	}

	public static final JSONObject parseFromString(String json) {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(TextFile.readFromResource("res/lib/jsonparser.js"));
			return new JSONObject(((Invocable) engine).invokeFunction("parseJSON", json));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}

}