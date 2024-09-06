package main.java.assesments.adapters;

import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

/*
 * Version 3 adapter
 */
public class Version3Adapter extends Version2Adapter {

	/* ID of the VPC */
	private String vpc_id;

	/* Subnet ID of the VPC */
	private String subnet_id;

	/* Instance ID of network interface */
	private String instance_id;

	/* Tcp_flags */
	private int tcp_Flags;

	/* Type of traffic */
	private String trafficType;

	/* Packet level source IP */
	private String pkt_srcAddr;

	/* Packet level destination IP */
	private String pkt_dstAddr;

	/* Parsing the log data */
	@Override
	public void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException {

		String[] fields = line.split(" ");

		parseVersion3Fields(fields);
	}

	/* Parsing version 3 fields */
	protected void parseVersion3Fields(String[] fields) throws LogLineParseException {

		super.parseCommonFields(fields);

		String defaultVpcId = "UNKNOWN_VPC_ID";
		String defaultSubnetId = "UNKNOWN_SUBNET_ID";
		String defaultInstanceId = "UNKNOWN_INSTANCE_ID";
		int defaultTcpFlags = 1;
		String defaultTrafficType = "UNKNOWN_TRAFFIC_TYPE";
		String defaultPktSrcAddr = "UNKNOWN_PKT_SRC_ADDR";
		String defaultPktDstAddr = "UNKNOWN_PKT_DST_ADDR";
		
		if (fields.length < 20) {
			UtilityConstants.logger.warn("Log line is incomplete, expected at least 19");
			throw new LogLineParseException("Log line is incomplete, expected at least 19");
		}

		// Assign values or default values
		vpc_id = fields.length > 14 ? fields[14].trim() : defaultVpcId;
		subnet_id = fields.length > 15 ? fields[15].trim() : defaultSubnetId;
		instance_id = fields.length > 16 ? fields[16].trim() : defaultInstanceId;
		tcp_Flags = fields.length > 17 ? Integer.parseInt(fields[17].trim()) : defaultTcpFlags;
		trafficType = fields.length > 18 ? fields[18].trim() : defaultTrafficType;
		pkt_srcAddr = fields.length > 19 ? fields[19].trim() : defaultPktSrcAddr;
		pkt_dstAddr = fields.length > 20 ? fields[20].trim() : defaultPktDstAddr;
	}

	/* Get the VPC ID */
	public String getVpc_id() {
		return vpc_id;
	}

	/* Get the Subnet ID */
	public String getSubnet_id() {
		return subnet_id;
	}

	/* Get the instance ID */
	public String getInstance_id() {
		return instance_id;
	}

	/* Get the TCP Flag */
	public int getTcp_Flags() {
		return tcp_Flags;
	}

	/* Get the Traffic Type */
	public String getTrafficType() {
		return trafficType;
	}

	/* Get the packet source IP address */
	public String getPkt_srcAddr() {
		return pkt_srcAddr;
	}

	/* Get the packet destination IP address */
	public String getPkt_dstAddr() {
		return pkt_dstAddr;
	}
}