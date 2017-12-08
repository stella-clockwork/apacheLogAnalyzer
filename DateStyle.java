package apacheLogAnalyzer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateStyle {
	// expects "01/Jan/2017:00 +0900"
	public static SimpleDateFormat fmt
		= new SimpleDateFormat("dd/MMM/yyyy:HH Z");

	public static Calendar timeToCalendar (String time) throws RuntimeException{
		//ToDo: タイムゾーンが一致しないような範囲でのログ収集を正しく行うにはタイムゾーンを元に足し引きが必要
		Pattern p = Pattern.compile("[0-9]{2}/.{3}/[0-9]{4}:[0-9]{2}:[0-9]{2}:[0-9]{2}\\s\\+[0-9]{4}");
		Matcher m = p.matcher(time);

		if (m.find()) {
			// dd/MMM/yyyy:hh:mm:ss Z　を受け入れ
			String str = m.group();
			int year = Integer.parseInt(str.substring(7, 11));
			int month = mToNum(str.substring(3, 6));
			int day = Integer.parseInt(str.substring(0, 2));
			int hour = Integer.parseInt(str.substring(12, 14));
			TimeZone tz = TimeZone.getTimeZone(str.substring(21,26)); //タイムゾーンはこれでは設定できない
			//System.out.println(tz);
			Calendar cal = new Calendar.Builder().setDate(year, month, day).setTimeOfDay(hour, 0, 0).setTimeZone(tz).build();
			return cal;
		}

		p = Pattern.compile("[0-9]{2}/.{3}/[0-9]{4}:[0-9]{2}");
		m = p.matcher(time);

		if (m.find()) {
			// dd/MMM/yyyy:hh を受け入れ
			String str = m.group();
			int year = Integer.parseInt(str.substring(7, 11));
			int month = mToNum(str.substring(3, 6));
			int day = Integer.parseInt(str.substring(0, 2));
			int hour = Integer.parseInt(str.substring(12, 14));
			Calendar cal = new Calendar.Builder().setDate(year, month, day).setTimeOfDay(hour, 0, 0).build();
			return cal;
		}

		throw new RuntimeException("illegal date format");
	}

	public static String calendarOutput(Calendar cal) {
		return fmt.format(cal.getTime());
	}

	private static Integer mToNum(String month) {
		if (month.equals("Jan")) return 0;
		else if (month.equals("Feb")) return 1;
		else if (month.equals("Mar")) return 2;
		else if (month.equals("Apr")) return 3;
		else if (month.equals("May")) return 4;
		else if (month.equals("Jun")) return 5;
		else if (month.equals("Jul")) return 6;
		else if (month.equals("Aug")) return 7;
		else if (month.equals("Sep")) return 8;
		else if (month.equals("Oct")) return 9;
		else if (month.equals("Nov")) return 10;
		else if (month.equals("Dec")) return 11;
		return -1;
	}

}
