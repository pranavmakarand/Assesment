package main.java.assesments.adapters;

import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

/*
 * Version 4 adapter
 */
public class Version4Adapter extends Version3Adapter {

	/*
	 * Region contains network interface
	 */
	private String region;
	
	/*
	 * ID of the availability zone that contains the network interface.
	 */
	private String az_ID;
	
	/*
	 * Type of sublocation
	 */
	private String sublocation_Type;
	
	/*
	 * ID of sublocation
	 */
	private String sublocation_ID;

	@Override
	public void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException {

		String[] fields = line.split(" ");
		parseVersion4Feilds(fields);
	}
	
	/*
	 * Parsing v4 fields
	 */
	protected void parseVersion4Feilds(String[] fields) throws LogLineParseException {
	
		super.parseVersion3Fields(fields);
		
	    if (fields.length < 24) {
	    	UtilityConstants.logger.warn("Log line is incomplete. Expected at least 23 fields");
	        throw new LogLineParseException("Log line is incomplete. Expected at least 23 fields");
	    }
		
	    String defaultRegion = "UNKNOWN_REGION";
	    String defaultAzID = "UNKNOWN_AZ_ID";
	    String defaultSublocationType = "UNKNOWN_SUBLOCATION_TYPE";
	    String defaultSublocationID = "UNKNOWN_SUBLOCATION_ID";
	    
	    region = fields.length > 20 ? fields[20].trim() : defaultRegion;
	    az_ID = fields.length > 21 ? fields[21].trim() : defaultAzID;
	    sublocation_Type = fields.length > 22 ? fields[22].trim() : defaultSublocationType;
	    sublocation_ID = fields.length > 23 ? fields[23].trim() : defaultSublocationID;
	}

	/*
	 * Get the region of the interface
	 */
	public String getRegion() {
		return region;
	}

	/*
	 * Get availability zone
	 */
	public String getAz_ID() {
		return az_ID;
	}

	/*
	 * Get sublocation type
	 */
	public String getSublocation_Type() {
		return sublocation_Type;
	}

	/*
	 * Get sublocation ID
	 */
	public String getSublocation_ID() {
		return sublocation_ID;
	}
}