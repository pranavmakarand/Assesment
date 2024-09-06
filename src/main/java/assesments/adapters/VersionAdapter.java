package main.java.assesments.adapters;

import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

public interface VersionAdapter {
	void parcingLogLineData(String logLine, FlowLogUtility tagger) throws LogLineParseException;
}
