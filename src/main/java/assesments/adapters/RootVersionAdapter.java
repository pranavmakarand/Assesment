package main.java.assesments.adapters;

import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

/*
 * Base adapter class for default flow log
 */
public abstract class RootVersionAdapter implements VersionAdapter {

	/* The VPC Flow Logs version*/
	private int version;
	
	/*The AWS account ID of the owner of the source network interface*/
	private String account_id;
	
	/* The ID of the network interface for which the traffic is recorded*/
	private String interface_id;
	
	/* The source address for incoming traffic */
	private String srcAddr;
	
	/*The destination address for outgoing traffic */
	private String dstAddr;
	
	/* The source port of the traffic */
	private int srcPort;
	
	/* The destination port of the traffic */
	private int desPort;
	
	/* The IANA protocol number of the traffic */
	private int protocol;
	
	/* The number of packets transferred during the flow */
	private long packets;
	
	/* The number of bytes transferred during the flow*/
	private long bytes;
	
	/* Time when first packet of flow was received within*/
	private long start;
	
	/* Time when last packet of flow was received within*/
	private long end;
	
	/* Action associated with traffic*/
	private String action;
	
	/* Logging status of flow log*/
	private String logStatus;

	/* Method to parse log data line by line*/
	public abstract void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException;

	/* Method to parse common fields*/
	protected void parseCommonFields(String[] fields) throws LogLineParseException {
		
	    int defaultVersion = 2;
	    String defaultAccountId = "UNKNOWN_ACCOUNT_ID";
	    String defaultInterfaceId = "UNKNOWN_INTERFACE_ID";
	    String defaultSrcAddr = "UNKNOWN_SRC_ADDR";
	    String defaultDstAddr = "UNKNOWN_DST_ADDR";
	    int defaultSrcPort = 0;
	    int defaultDesPort = 0;
	    int defaultProtocol = 0;
	    long defaultPackets = 0;
	    long defaultBytes = 0;
	    long defaultStart = 0;
	    long defaultEnd = 0;
	    String defaultAction = "UNKNOWN_ACTION";
	    String defaultLogStatus = "UNKNOWN_STATUS";
	    
	    // Check if the fields array has at least 14 elements
	    if (fields.length < 14) {
	    	UtilityConstants.logger.warn("Log line is incomplete. Expected at least 14 fields");
	    	throw new LogLineParseException("Log line is incomplete. Expected at least 14 fields");
	    }
		
	    // Assign values or default values
	    version = fields.length > 0 ? Integer.parseInt(fields[0].trim()) : defaultVersion;
	    account_id = fields.length > 1 ? fields[1].trim() : defaultAccountId;
	    interface_id = fields.length > 2 ? fields[2].trim() : defaultInterfaceId;
	    srcAddr = fields.length > 3 ? fields[3].trim() : defaultSrcAddr;
	    dstAddr = fields.length > 4 ? fields[4].trim() : defaultDstAddr;
	    srcPort = fields.length > 5 ? Integer.parseInt(fields[5].trim()) : defaultSrcPort;
	    desPort = fields.length > 6 ? Integer.parseInt(fields[6].trim()) : defaultDesPort;
	    protocol = fields.length > 7 ? Integer.parseInt(fields[7].trim()) : defaultProtocol;
	    packets = fields.length > 8 ? Long.parseLong(fields[8].trim()) : defaultPackets;
	    bytes = fields.length > 9 ? Long.parseLong(fields[9].trim()) : defaultBytes;
	    start = fields.length > 10 ? Long.parseLong(fields[10].trim()) : defaultStart;
	    end = fields.length > 11 ? Long.parseLong(fields[11].trim()) : defaultEnd;
	    action = fields.length > 12 ? fields[12].trim() : defaultAction;
	    logStatus = fields.length > 13 ? fields[13].trim() : defaultLogStatus;
	}

	/* Method to get version*/
	public int getVersion() {
		return version;
	}

	/* Method to get account ID*/
	public String getAccount_id() {
		return account_id;
	}

	/* Method to get interface ID*/
	public String getInterface_id() {
		return interface_id;
	}

	/* Method to get source address*/
	public String getSrcAddr() {
		return srcAddr;
	}

	/* Method to get destination address*/
	public String getDstAddr() {
		return dstAddr;
	}

	/* Method to get source port*/
	public int getSrcPort() {
		return srcPort;
	}

	/* Method to get destination port*/
	public int getDesPort() {
		return desPort;
	}

	/* Method to get protocol*/
	public int getProtocol() {
		return protocol;
	}

	/* Method to get transferred packets*/
	public long getPackets() {
		return packets;
	}

	/* Method to get transferred bytes*/
	public long getBytes() {
		return bytes;
	}

	/* Time when first packet arrives*/
	public long getStart() {
		return start;
	}

	/* Time when last packet arrives*/
	public long getEnd() {
		return end;
	}

	/* Get action associated with traffic*/
	public String getAction() {
		return action;
	}
	
	/* Get log status associated with traffic*/
	public String getLogStatus() {
		return logStatus;
	}
}