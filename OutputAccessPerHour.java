package fixPointPrograming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputAccessPerHour extends OutputAbstract {

	@Override
	public void PrintHeader(ArrayList<String> filenames) {
		outputln("解析手法：時間帯別アクセス数");
		output("解析対象ログ：");
		for (int i = 0; i < filenames.size(); i++) output(filenames.get(i));
		output("\n");
	}

	@Override
	public void PrintLog(CollectedData result) {
		List<Map.Entry<String, Integer>> hours = new ArrayList<Map.Entry<String, Integer>>(result.access_per_hour.entrySet());
		Collections.sort( hours, new Comparator<Map.Entry<String, Integer>>() {
			public int compare( Map.Entry<String, Integer> tuple1, Map.Entry<String, Integer> tuple2 ) {
				return DateStyle.compare(tuple1.getKey(), tuple2.getKey());
			}
		});

		for (Map.Entry<String, Integer> hour:hours) {
			outputln(hour.getKey() + " -> " + hour.getValue().toString());
		}
	}
}
