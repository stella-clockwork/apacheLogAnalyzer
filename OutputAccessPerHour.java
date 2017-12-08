package apacheLogAnalyzer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputAccessPerHour extends OutputAbstract {

	@Override
	public void PrintHeader(ArrayList<String> filenames) {
		outputln("解析手法：時間帯別アクセス数");
		output("解析対象ログ：");
		for (int i = 0; i < filenames.size(); i++) output(filenames.get(i)+" ");
		output("\n");
	}

	@Override
	public void PrintLog(CollectedData result) {
		List<Map.Entry<Calendar, Integer>> hours = new ArrayList<Map.Entry<Calendar, Integer>>(result.access_per_hour.entrySet());
		Collections.sort( hours, new Comparator<Map.Entry<Calendar, Integer>>() {
			public int compare( Map.Entry<Calendar, Integer> tuple1, Map.Entry<Calendar, Integer> tuple2 ) {
				return tuple1.getKey().compareTo(tuple2.getKey());
			}
		});

		for (Map.Entry<Calendar, Integer> hour:hours) {
			outputln(DateStyle.calendarOutput(hour.getKey()) + " -> " + hour.getValue().toString());
		}
	}
}
