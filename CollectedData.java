package fixPointPrograming;

import java.util.HashMap;

public class CollectedData {
	public HashMap<String, Integer> access_per_hour;
	public HashMap<String, Integer> access_per_host;

	public CollectedData() {
		access_per_hour = new HashMap<String, Integer>();
		access_per_host = new HashMap<String, Integer>();
	}
}
