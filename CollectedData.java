package apacheLogAnalyzer;

import java.util.Calendar;
import java.util.HashMap;

public class CollectedData {
	public HashMap<Calendar, Integer> access_per_hour;
	public HashMap<IPAddress, Integer> access_per_host;

	public CollectedData() {
		access_per_hour = new HashMap<Calendar, Integer>();
		access_per_host = new HashMap<IPAddress, Integer>();
	}
}
