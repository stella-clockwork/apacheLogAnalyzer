package apacheLogAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCollector {

	public static CollectedData forEachFile(ArrayList<String> filenames, CollectOpAbstract method,
											Calendar[] collect_period) {
		CollectedData result = new CollectedData();

		for(int i=0; i < filenames.size(); i++) {
			DebugPrint.println(filenames.get(i), DebugPrint.DEBUGING);
			forEachLine(filenames.get(i), method, result, collect_period);
		}
		return result;
	}

	public static void forEachLine(String file, CollectOpAbstract method,
								   CollectedData result, Calendar[] collect_period) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));

			while (reader.ready()) {
				String line = reader.readLine();
				DebugPrint.println(line, DebugPrint.DEBUGING);

				String[] elements = line.split(" ");
				// タイムゾーン部分が分離されてしまっているので結合しなおす
				elements[3] = elements[3]+" "+elements[4];

				try {
					if (!isDataCorrect(elements)) {
						DebugPrint.println("incorrect log line: " + line, DebugPrint.ERROR);
						continue;
					}
					if (!inCorrectPeriod(DateStyle.timeToCalendar(elements[3]), collect_period)) continue;
					method.collecting(elements, result);
				} catch (RuntimeException re) {
					DebugPrint.println(re.getMessage(), DebugPrint.ERROR);
				}
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			System.out.printf("Failed to open file: %s\n", file);
		} catch (IOException ioe) {
			System.out.printf("Error occurres when reading: %s\n", file);
		}
	}

	protected static boolean inCorrectPeriod(Calendar time, Calendar[] collect_period) {
		if (collect_period[0] != null
			&& collect_period[0].compareTo(time) > 0) return false;

		if (collect_period[1] != null
			&& collect_period[1].compareTo(time) < 0) return false;
		return true;
	}

	static boolean isDataCorrect(String[] elements) {
		if (!IPv4Address.isCorrestIPv4(elements[0])) return false;

		Pattern p = Pattern.compile("^\\[[0-9]{2}/.{3}/[0-9]{4}:[0-9]{2}:[0-9]{2}:[0-9]{2} [\\+\\-][0-9]{4}\\]$");
		Matcher m = p.matcher(elements[3]);
		if (!m.find()) {
			return false;
		}

		return true;
	}
}
