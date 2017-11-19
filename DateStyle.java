package fixPointPrograming;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateStyle {
	public static final String default_timezone = "+0900";

	public static String timeToDS(String time) {
		//ToDo: タイムゾーンが一致しないような範囲でのログ収集を正しく行うにはタイムゾーンを元に足し引きが必要
		Pattern p = Pattern.compile("[0-9]{2}/.{3}/[0-9]{4}:[0-9]{2}");
		Matcher m = p.matcher(time);

		if (m.find()) {
			return m.group();
		} else {
			System.exit(1);
		}

		return null;
	}

	public static int compare(String key, String key2) {
		Integer y1 = Integer.parseInt(key.substring(7, 11));
		Integer y2 = Integer.parseInt(key2.substring(7, 11));
		int compy = y1.compareTo(y2);
		if (compy != 0) return compy;

		Integer m1 = mToNum(key.substring(3, 6));
		Integer m2 = mToNum(key2.substring(3, 6));
		int compm = m1.compareTo(m2);
		if (compm != 0) return compm;

		Integer d1 = Integer.parseInt(key.substring(0, 2));
		Integer d2 = Integer.parseInt(key2.substring(0, 2));
		int compd = d1.compareTo(d2);
		if (compd != 0) return compd;

		Integer h1 = Integer.parseInt(key.substring(12, 14));
		Integer h2 = Integer.parseInt(key2.substring(12, 14));
		int comph = h1.compareTo(h2);
		return comph;
	}

	private static Integer mToNum(String month) {
		if (month.equals("Jan")) return 1;
		else if (month.equals("Feb")) return 2;
		else if (month.equals("Mar")) return 3;
		else if (month.equals("Apr")) return 4;
		else if (month.equals("May")) return 5;
		else if (month.equals("Jun")) return 6;
		else if (month.equals("Jul")) return 7;
		else if (month.equals("Aug")) return 8;
		else if (month.equals("Sep")) return 9;
		else if (month.equals("Oct")) return 10;
		else if (month.equals("Nov")) return 11;
		else if (month.equals("Dec")) return 12;
		return -1;
	}
}
