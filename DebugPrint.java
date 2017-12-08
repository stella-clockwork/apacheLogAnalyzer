package apacheLogAnalyzer;

import java.util.Collection;

public class DebugPrint {

	public static final int DEBUGING = 3;
	public static final int WARNING = 2;
	public static final int ERROR = 1;
	public static final int NONE = 0;

	private static final int debug_mode = NONE;
	public static void println(String str, int level) {
		if (debug_mode >= level)
			System.out.println(str);
	}

	public static void println(Collection c, int level) {
		if (debug_mode >= level) {
			System.out.println(c);
		}
	}
}
