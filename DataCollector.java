package fixPointPrograming;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCollector {

	public static CollectedData forEachFile(ArrayList<String> filenames, CollectOpAbstract method,
											String[] collect_period) {
		CollectedData result = new CollectedData();

		for(int i=0; i < filenames.size(); i++) {
			DebugPrint.println(filenames.get(i));
			forEachLine(filenames.get(i), method, result, collect_period);
		}
		return result;
	}

	public static void forEachLine(String file, CollectOpAbstract method,
									CollectedData result, String[] collect_period) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));

			while (reader.ready()) {
				String line = reader.readLine();
				DebugPrint.println(line);

				String[] elements = line.split(" ");
				// タイムゾーン部分が分離されてしまっているので結合しなおす
				elements[3] = elements[3]+" "+elements[4];

				if (!isDataCorrect(elements)) continue;

				if (!inCorrectPeriod(DateStyle.timeToDS(elements[3]), collect_period)) continue;
				method.collecting(elements, result);
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			System.out.printf("Failed to open file: %s\n", file);
		} catch (IOException ioe) {
			System.out.printf("Error occurres when reading: %s\n", file);
		}
	}

	private static boolean inCorrectPeriod(String time, String[] collect_period) {
		if (!collect_period[0].equals("")
			&& DateStyle.compare(collect_period[0], time) > 0) return false;

		if (!collect_period[1].equals("")
			&& DateStyle.compare(time, collect_period[1]) > 0) return false;
		return true;
	}

	private static boolean isDataCorrect(String[] elements) {
		Pattern p = Pattern.compile("[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]");
		Matcher m = p.matcher(elements[0]);
		if (!m.find()) {
			return false;
		}

		p = Pattern.compile("^\\[[0-9]{2}/.{3}/[0-9]{4}:[0-9]{2}:[0-9]{2}:[0-9]{2} \\+[0-9]{4}\\]$");
		m = p.matcher(elements[3]);
		if (!m.find()) {
			return false;
		}

		return true;
	}
}
