package se.BaseUlterior.Context.JSON;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextFile {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static final String readFromPath(String path) {
		try {
			return read(Files.newInputStream(Paths.get(path), StandardOpenOption.READ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final String readFromResource(String resource) {
		try {
			return read(new FileInputStream(resource));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error :/";
	}

	public static final String readFromURL(String url) {
		try {
			return read(new URL(url).openStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String read(InputStream is) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
				builder.append(line + LINE_SEPARATOR);
			reader.close();
			System.gc();
			return builder.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}