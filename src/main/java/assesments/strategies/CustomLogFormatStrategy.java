package main.java.assesments.strategies;

import org.apache.logging.log4j.Level;

import main.java.assesments.adapters.VersionAdapter;
import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.AdapterNotFoundException;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.AdapterUtility;
import main.java.assesments.utilities.FlowLogUtility;

public class CustomLogFormatStrategy implements LogFormatStrategy {

	VersionAdapter customAdapter;

	@Override
	public void parcingLogLineData(String logLine, FlowLogUtility tag) throws AdapterNotFoundException, LogLineParseException {
		
		String version = logLine.split("")[0];
		
		UtilityConstants.logger.log(Level.INFO, "Using adapter with version " + version);
		
		customAdapter = AdapterUtility.customAdapter(version);
		
		customAdapter.parcingLogLineData(logLine, tag);
	}

	@Override
	public VersionAdapter getAdapter() {
		return customAdapter;
	}
}