package me.samuel_woronick.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class Utils {
	private Utils() {
		// Private constructor
	}
	
	public static final long MILLIS_PER_DAY = 86400000L;
	
	// Convert minutes to milliseconds.
	public static long toMillis(int minutes) {
		return minutes * 60000L;
	}
	
	// Read the JSON cron tab from the file system. It skips lines beginning with a '#' character.
	public static String readFile(String absPath) {
		StringBuilder strBuilder = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(absPath))) {
			String line = reader.readLine();

			do {
				// Remove all leading and trailing whitespace characters.
				line = line.strip();

				// If the line is null or empty, continue to the next line.
				if (line.length() == 0 || line.charAt(0) == '#') {
					continue;
				}

				strBuilder.append(line);
			} while ((line = reader.readLine()) != null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strBuilder.toString();
	}
}
