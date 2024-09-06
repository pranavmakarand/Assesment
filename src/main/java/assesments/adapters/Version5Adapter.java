package main.java.assesments.adapters;

import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

/*
 * Version 5 adapter
 */
public class Version5Adapter extends Version4Adapter {

	/* Name of subset of IP for the pkt-scr_addr */
	private String pkt_src_aws_service;
	
	/* Name of subset of IP for the pkt-dst_addr */
	private String pkt_dst_aws_service;
	
	/* Direction of flow wrt interface */
	private String flow_Direction;
	
	/* Path that egress traffic takes to destination */
	private String traffic_Path;

	@Override
	public void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException {

		String[] fields = line.split(" ");
		
		parseVersion5Feilds(fields);
	}
	
	/* Parsing V5 fields */
	protected void parseVersion5Feilds(String[] fields) throws LogLineParseException {
		
		super.parseVersion4Feilds(fields);
		
	    if (fields.length < 28) {
	    	UtilityConstants.logger.warn("Log line is incomplete. Expected at least 27 fields");
	        throw new LogLineParseException("Log line is incomplete. Expected at least 27 fields");
	    }
	    
	    String defaultPktSrcAwsService = "UNKNOWN_SRC_AWS_SERVICE";
	    String defaultPktDstAwsService = "UNKNOWN_DST_AWS_SERVICE";
	    String defaultFlowDirection = "UNKNOWN_FLOW_DIRECTION";
	    String defaultTrafficPath = "UNKNOWN_TRAFFIC_PATH";
	    
	    pkt_src_aws_service = fields.length > 24 ? fields[24].trim() : defaultPktSrcAwsService;
	    pkt_dst_aws_service = fields.length > 25 ? fields[25].trim() : defaultPktDstAwsService;
	    flow_Direction = fields.length > 26 ? fields[26].trim() : defaultFlowDirection;
	    traffic_Path = fields.length > 27 ? fields[27].trim() : defaultTrafficPath;
	}

	/* Gets pkt-scr_addr */
	public String getPkt_src_aws_service() {
		return pkt_src_aws_service;
	}

	/* Gets pkt-dest_addr */
	public String getPkt_dst_aws_service() {
		return pkt_dst_aws_service;
	}

	/* Gets flow direction */
	public String getFlow_Direction() {
		return flow_Direction;
	}

	/* Gets traffic path */
	public String getTraffic_Path() {
		return traffic_Path;
	}
}