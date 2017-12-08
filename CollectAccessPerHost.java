package apacheLogAnalyzer;

public class CollectAccessPerHost extends CollectOpAbstract{

	@Override
	public void collecting(String[] elements, CollectedData result) throws RuntimeException{
		IPAddress ip = IPAddress.getIPAddress(elements[0]);
		DebugPrint.println(ip.toString());

		if (result.access_per_host.containsKey(ip)) {
			result.access_per_host.put(ip, result.access_per_host.get(ip)+1);
		} else {
			result.access_per_host.put(ip, 1);
		}
	}


}
