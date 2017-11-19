package fixPointPrograming;

public class CollectAccessPerHost extends CollectOpAbstract{

	@Override
	public void collecting(String[] elements, CollectedData result) {
		DebugPrint.println(elements[0]);
		if (result.access_per_host.containsKey(elements[0])) {
			result.access_per_host.put(elements[0], result.access_per_host.get(elements[0])+1);
		} else {
			result.access_per_host.put(elements[0], 1);
		}
	}


}
