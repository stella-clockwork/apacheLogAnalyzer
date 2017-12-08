package apacheLogAnalyzer;

public class DataSeparaterHour extends DataSeparaterAbstract{

	@Override
	public boolean nextPeriod() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	/*
	public String[] separatePeriod;
	public String[] collect_period;
	public String max, min;

	public DataSeparaterHour(ArrayList<String> filenames, String[] cp) {
		this.collect_period = cp;
		this.max = "01/Jan/0000:00";
		this.min = "31/Dec/9999:23";
		if (this.collect_period[0].equals("") || this.collect_period[1].equals("")) {
			for(int i=0; i < filenames.size(); i++) {
				BufferedReader reader;
				String file = filenames.get(i);
				try {
					reader = new BufferedReader(new FileReader(file));
					while (reader.ready()) {
						String line = reader.readLine();

						String[] elements = line.split(" ");
						// タイムゾーン部分が分離されてしまっているので結合しなおす
						elements[3] = elements[3]+" "+elements[4];
						if (!DataCollector.isDataCorrect(elements)) continue;

						String time = DateStyle.timeToDS(elements[3]);
						if (DateStyle.compare(min, time) > 0) min = time;
						if (DateStyle.compare(time, max) > 0) max = time;
					}
					reader.close();
				} catch (FileNotFoundException fnfe) {
					System.out.printf("Failed to open file: %s\n", file);
				} catch (IOException ioe) {
					System.out.printf("Error occurres when reading: %s\n", file);
				}
			}
		}

		this.separatePeriod[0] = min;
		this.separatePeriod[1] = DateStyle.addOneMonth(min);
	}

	@Override
	public boolean nextPeriod() {

		return false;
	}
	*/
}
