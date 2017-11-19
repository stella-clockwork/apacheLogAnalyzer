package fixPointPrograming;

import java.util.ArrayList;

public abstract class OutputAbstract {
	public abstract void PrintHeader(ArrayList<String> filenames);
	public abstract void PrintLog(CollectedData result);

	/**
	 * Buffer to determine output direction.
	 * Currently, output is written to stdout.
	 * @param line outputed line
	 */
	protected void output(String line) {
		System.out.print(line);
	}

	protected void outputln(String line) {
		output(line + "\n");
	}
}
