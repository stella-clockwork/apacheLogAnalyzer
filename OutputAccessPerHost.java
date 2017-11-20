package fixPointPrograming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputAccessPerHost extends OutputAbstract {

	@Override
	public void PrintHeader(ArrayList<String> filenames) {
		outputln("解析手法：ホスト別アクセス数");
		output("解析対象ログ：");
		for (int i = 0; i < filenames.size(); i++) output(filenames.get(i)+" ");
		output("\n");
	}

	@Override
	public void PrintLog(CollectedData result) {
		List<Map.Entry<String, Integer>> hosts = new ArrayList<Map.Entry<String, Integer>>(result.access_per_host.entrySet());
		Collections.sort( hosts, new Comparator<Map.Entry<String, Integer>>() {
			public int compare( Map.Entry<String, Integer> tuple1, Map.Entry<String, Integer> tuple2 ) {
		        return (tuple2.getValue().compareTo(tuple1.getValue()));
			}
		});

		for (Map.Entry<String, Integer> host:hosts) {
			outputln(host.getKey() + " -> " + host.getValue().toString());
		}
	}
}
