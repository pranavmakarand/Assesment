package main.java.assesments.strategies;

import main.java.assesments.adapters.VersionAdapter;
import main.java.assesments.exceptions.AdapterNotFoundException;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

public interface LogFormatStrategy {
	
	public void parcingLogLineData(String logLine, FlowLogUtility tagger) throws LogLineParseException, AdapterNotFoundException;
	public VersionAdapter getAdapter();
}