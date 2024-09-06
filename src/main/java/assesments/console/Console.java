package main.java.assesments.console;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.AdapterNotFoundException;
import main.java.assesments.exceptions.CustomCalculationException;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.strategies.CustomLogFormatStrategy;
import main.java.assesments.strategies.DefaultLogFormatStrategy;
import main.java.assesments.strategies.LogFormatStrategy;
import main.java.assesments.utilities.FlowLogUtility;

import java.io.IOException;
import java.nio.file.Paths;

/* Console Utility entry point */
public class Console {

	public static final Logger logger = LogManager.getLogger(Console.class);
	
	public static void main(String[] args) {

		ConsoleOptions options = new ConsoleOptions();

		CmdLineParser parser = new CmdLineParser(options);

		try {

			UtilityConstants.logger.log(Level.INFO, " Parsing the command line arguments ");
			
			logger.log(Level.INFO, " Parsing the command line arguments ");

			parser.parseArgument(args);

		} catch (CmdLineException cle) {

			System.out.println(cle.getMessage());
			System.exit(1); // Exit with code 1 for command-line parsing error
		}

		String format = options.getFormat();

		LogFormatStrategy logFormatStrategy = new DefaultLogFormatStrategy();

		if (StringUtils.isBlank(format) || StringUtils.equals(format, "default")) {
			format = UtilityConstants.defaultFormat;
		} else if (StringUtils.equals(format, "custom")) {
			format = UtilityConstants.customFormat;
		} else {
			UtilityConstants.logger.error(" Invalid format provided ");
			System.exit(2); // Exit with code 2 for invalid format provided
		}

		UtilityConstants.logger.log(Level.INFO, "Tool to be run with the given format : " + format);

		if (format.equals(UtilityConstants.customFormat)) {
			logFormatStrategy = new CustomLogFormatStrategy();
		}

//		String lookupTablePath = Paths.get(options.getLookupTableFilePath()).toString();
//		String flowLogPath = Paths.get(options.getFlowLogsFilePath()).toString();
		
		String lookupTablePath = Paths.get("resources/lookup_table.csv").toString();
		String flowLogPath = Paths.get("resources/flow_log.txt").toString();

		FlowLogUtility tagger = new FlowLogUtility(logFormatStrategy);

		UtilityConstants.logger.log(Level.INFO, " Loading the look up table : " + format);

		try {

			tagger.loadLookupTable(lookupTablePath);

		} catch (IOException e) {

			UtilityConstants.logger.error(" Failed to read lookup table : " + e.getMessage());
			System.exit(3); // Exit with code 3 for invalid format
		}

		UtilityConstants.logger.log(Level.INFO, "Parcing the flow logs and computing the freq : " + format);

		try {

			tagger.parseAndComputeData(flowLogPath);

		} catch (LogLineParseException | AdapterNotFoundException | IOException | CustomCalculationException e) {

			UtilityConstants.logger.error(" Failed to parse log line : " + e.getMessage());
			System.exit(4); // Exit with code 4 for calculation error
		}

		UtilityConstants.logger.log(Level.INFO, " Generating the output file ");

		try {

			tagger.generateOutputs("resources/output.txt");
//			tagger.generateOutputs(options.getOutputFilePath());

		} catch (IOException e) {
			
			UtilityConstants.logger.error(" Failed to create the output file : " + e.getMessage());
			System.exit(5); // Exit with code 5 for writing in the file
		}
	}
}