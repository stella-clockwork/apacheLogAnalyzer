package apacheLogAnalyzer;

import java.util.Collection;

public class DebugPrint {
	private static final int debug_mode = 0;
	public static void println(String str) {
		if (debug_mode == 1)
			System.out.println(str);
	}

	public static void println(Collection c) {
		if (debug_mode == 1)
			System.out.println(c);
	}
}
