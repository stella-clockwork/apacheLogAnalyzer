package fixPointPrograming;

public class CollectAccessPerHour extends CollectOpAbstract {

	@Override
	public void collecting(String[] elements, CollectedData result) {

		String timeArea = DateStyle.timeToDS(elements[3]);
	    DebugPrint.println(timeArea);

		if (result.access_per_hour.containsKey(timeArea)) {
			result.access_per_hour.put(timeArea, result.access_per_hour.get(timeArea)+1);
		} else {
			result.access_per_hour.put(timeArea, 1);
		}
	}
}
