package apacheLogAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class MainControler {
	/*
	enum collect_methods_list {
		UNDEFINED,
		TIME,
		HOST,
	}*/

	private static String collect_method;
	private static ArrayList<String> filenames = new ArrayList<String>();
	private static Calendar[] collect_period = new Calendar[2];


	public static void main(String[] args) {
		getArgs(args);

		try {
			initialize();
		} catch(IOException IOe) {
			System.out.println("入出力が異常終了しました");
			System.exit(1);
		}

		CollectOpAbstract method = null;
		OutputAbstract output = null;
		if (collect_method.equals("hour")) {
			method = new CollectAccessPerHour();
			output = new OutputAccessPerHour();
		}else if (collect_method.equals("host")) {
			method = new CollectAccessPerHost();
			output = new OutputAccessPerHost();
		}
		else System.exit(1);

		output.PrintHeader(filenames);

		CollectedData result = DataCollector.forEachFile(filenames, method, collect_period);

		output.PrintLog(result);
	}

	private static void getArgs(String args[]) {
		for (int i=0; i < args.length; i++) {
			if (args[i].equals("-h") || args[i].equals("--help")) {
				System.out.println("Useage: \"collector options\"");
				System.out.println("options:");
				System.out.println("  [(-m | --method) (hour | host)]");
				System.out.println("  [(-f | --file) (\"filename\")* \"filename\"]");
				System.out.println("  [(-s | --start) \"period\"]");
				System.out.println("  [(-e | --end) \"period\"]");
				System.out.println("    period style: day/month/year:hour");
				System.out.println("      (example: 18/Apr/2017:03)");
				System.exit(0);
			} else if (args[i].equals("-m") || args[i].equals("--method")) {
				if (i == args.length - 1) {
					System.out.println("please input collecting method");
					System.exit(0);
				}
				if (args[i+1].equals("hour")
					|| args[i+1].equals("host")) {
					collect_method = args[i+1];
				} else {
					System.out.printf("unknown collect method given by command line: %s\n", args[i+1]);
				}
				i = i+1;
			} else if (args[i].equals("-f") || args[i].equals("--file")) {
				for (i = i+1; i < args.length; i++) {
					if (args[i].equals("-m") || args[i].equals("--method")
						|| args[i].equals("-s") || args[i].equals("--start")
						|| args[i].equals("-e") || args[i].equals("--end")) {
						i = i-1;
						break;
					}

					File fp = new File(args[i]);
					if (fp.exists()) {
						filenames.add(args[i]);
					} else {
						System.out.printf("unknown file name given by command line\n");
					}
				}
			} else if (args[i].equals("-s") || args[i].equals("--start")) {
				if (i == args.length - 1) {
					System.out.println("please input start of collect period");
					System.exit(0);
				}
				try {
					collect_period[0] = DateStyle.timeToCalendar(args[i+1]);
				} catch (RuntimeException re) {
					System.out.println("please input start of collect period with following style:");
					System.out.println("  dd/MMM/yyyy:hh (e.g. 18/Apr/2017:03)");
				}
				i = i+1;
			} else if (args[i].equals("-e") || args[i].equals("--end")) {
				if (i == args.length - 1) {
					System.out.println("please input end of collect period");
					System.exit(0);
				}
				try {
					collect_period[1] = DateStyle.timeToCalendar(args[i+1]);
				} catch (RuntimeException re) {
					System.out.println("please input end of collect period with following style:");
					System.out.println("  dd/MMM/yyyy:hh (e.g. 18/Apr/2017:03)");
				}
				i = i+1;
			}
		}
	}


	private static void initialize() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;

		if (collect_method.equals("")) {
			System.out.println("集計手法を選択してください");
			System.out.println("時間帯別アクセス数:hour");
			System.out.println("ホスト名別アクセス数:host");

			while (collect_method.equals("")) {
				line = reader.readLine();
				if (line.equals("hour") || line.equals("host")) {
					collect_method = line;
				} else if (line.equals("exit")){
					System.exit(0);
				} else {
					System.out.println("集計手法を正しく指定してください");
				}
			}
		}

		if (filenames.isEmpty()) {
			System.out.println("解析するログファイルの名前を入力してください");
			System.out.println("ログファイルを複数使用する場合は\' \'(半角スペース)で区切ってください");
			line = reader.readLine();
			String files[] = line.split(" ");
			for (int i = 0; i < files.length; i++) {
				//DebugPrint.println(files[i]);
				if (files[i].equals("-m") || files[i].equals("--method")) {
					i = i-1;
					break;
				}

				File fp = new File(files[i]);
				if (fp.exists()) {
					filenames.add(files[i]);
				} else {
					System.out.printf("unknown file name given by command line\n");
				}
			}
		}
	}

}
