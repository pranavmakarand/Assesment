package main.java.assesments.adapters;

import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

/*
 * Version 2 adapter
 */
public class Version2Adapter extends RootVersionAdapter {

	@Override
	public void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException {
		 super.parseCommonFields(line.split(" "));
	}
}