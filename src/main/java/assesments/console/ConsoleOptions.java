package main.java.assesments.console;

import org.kohsuke.args4j.Option;

/*
 * Different console options
 */
public class ConsoleOptions {

	@Option(name = "-format", usage = "The format allowed for flow logs")
	private String format;

	@Option(name = "-lookupTableFilePath", usage = "The absolute file path with file name for migration")
	private String lookupTableFilePath;

	@Option(name = "-flowLogsFilePath", usage = "The absolute file path with file name for migration")
	private String flowLogsFilePath;

	@Option(name = "-outputFilePath", usage = "The absolute file path with file name for migration")
	private String outputFilePath;

	/**
	 * Get Format.
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Get Lookup Table File Path.
	 */
	public String getLookupTableFilePath() {
		return lookupTableFilePath;
	}

	/**
	 * Get flow logs file path.
	 */
	public String getFlowLogsFilePath() {
		return flowLogsFilePath;
	}

	/**
	 * Gets the output file path.
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}
}