package main.java.assesments.strategies;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;


import main.java.assesments.adapters.VersionAdapter;
import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.AdapterNotFoundException;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.AdapterUtility;
import main.java.assesments.utilities.FlowLogUtility;

public class DefaultLogFormatStrategy implements LogFormatStrategy {
	
	VersionAdapter defaultAdapter;

	@Override
	public void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException, AdapterNotFoundException {
		
		String version = line.split("")[0];

		if (!StringUtils.equals(version, "2")) {
			throw new AdapterNotFoundException("Default Format can handle only version 2");
		}
		
		UtilityConstants.logger.log(Level.INFO, "Using version 2 adapter");
		
		defaultAdapter = AdapterUtility.defaultAdapter();
		
		defaultAdapter.parcingLogLineData(line, tagger);
	}
	
	public VersionAdapter getAdapter() {
		return defaultAdapter;
	}
}