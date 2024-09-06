package main.java.assesments.adapters;

import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.utilities.FlowLogUtility;

/*
 * Version 7 adapter
 */
public class Version7Adapter extends Version5Adapter {

	/* AWS Resource Name of the ECS cluser */
	private String ecs_Cluster_Arn;
	
	/* Name of the ECS cluster */
	private String ecs_Cluster_Name;
	
	/* Arn of the ECS container instance */
	private String ecs_Container_Instance_Arn;
	
	/* ID of the ECS container */
	private String ecs_Container_Instance_ID;
	
	/* Docker runtime ID of the container */
	private String ecs_Container_ID;
	
	/* Docker runtime ID of the container */
	private String ecs_Second_Container_ID;
	
	/* Name of ECS service */
	private String ecs_Service_Name;
	
	/* ARN of ECS task */
	private String ecs_Task_Definition_Arn;
	
	/* ID of ECS task */
	private String ecs_Task_ID;

	@Override
	public void parcingLogLineData(String line, FlowLogUtility tagger) throws LogLineParseException {
		
		String[] fields = line.split(" ");
		parseVersion7Feilds(fields);
	}
	
	protected void parseVersion7Feilds(String[] fields) throws LogLineParseException {
		
		super.parseVersion5Feilds(fields);
		
	    // Check if the fields array has at least 36 elements
	    if (fields.length < 36) {
	    	UtilityConstants.logger.warn("Log line is incomplete. Expected at least 36 fields");
	        throw new LogLineParseException("Log line is incomplete. Expected at least 36 fields");
	    }
		
	    // Default values
	    String defaultClusterArn = "UNKNOWN_CLUSTER_ARN";
	    String defaultClusterName = "UNKNOWN_CLUSTER_NAME";
	    String defaultContainerInstanceArn = "UNKNOWN_CONTAINER_INSTANCE_ARN";
	    String defaultContainerInstanceID = "UNKNOWN_CONTAINER_INSTANCE_ID";
	    String defaultContainerID = "UNKNOWN_CONTAINER_ID";
	    String defaultSecondContainerId = "UNKNOWN_SECOND_CONTAINER_ID";
	    String defaultServiceName = "UNKNOWN_SERVICE_NAME";
	    String defaultTaskDefinitionArn = "UNKNOWN_TASK_DEFINITION_ARN";
	    String defaultTaskID = "UNKNOWN_TASK_ID";
	     
	    // Assign values or default values
	    ecs_Cluster_Arn = fields.length > 27 ? fields[27].trim() : defaultClusterArn;
	    ecs_Cluster_Name = fields.length > 28 ? fields[28].trim() : defaultClusterName;
	    ecs_Container_Instance_Arn = fields.length > 29 ? fields[29].trim() : defaultContainerInstanceArn;
	    ecs_Container_Instance_ID = fields.length > 30 ? fields[30].trim() : defaultContainerInstanceID;
	    ecs_Container_ID = fields.length > 31 ? fields[31].trim() : defaultContainerID;
	    ecs_Second_Container_ID = fields.length > 32 ? fields[32].trim() : defaultSecondContainerId;
	    ecs_Service_Name = fields.length > 33 ? fields[33].trim() : defaultServiceName;
	    ecs_Task_Definition_Arn = fields.length > 34 ? fields[34].trim() : defaultTaskDefinitionArn;
	    ecs_Task_ID = fields.length > 35 ? fields[35].trim() : defaultTaskID;
	}
	
	/* Gets ARN of the ECS cluser */
	public String getEcs_Cluster_Arn() {
		return ecs_Cluster_Arn;
	}

	/* Gets ARN of the ECS cluser */
	public String getEcs_Cluster_Name() {
		return ecs_Cluster_Name;
	}

	/* Gets ARN of the ECS container */
	public String getEcs_Container_Instance_Arn() {
		return ecs_Container_Instance_Arn;
	}
	
	/* Gets ARN of the ECS container instance ID */
	public String getEcs_Container_Instance_ID() {
		return ecs_Container_Instance_ID;
	}

	/* Gets ID of the ECS container*/
	public String getEcs_Container_ID() {
		return ecs_Container_ID;
	}

	/* Gets Second ID of the ECS container*/
	public String getEcs_Second_Container_ID() {
		return ecs_Second_Container_ID;
	}

	/* Gets ECS service name*/
	public String getEcs_Service_Name() {
		return ecs_Service_Name;
	}

	/* Gets arn of ECS task definition*/
	public String getEcs_Task_Definition_Arn() {
		return ecs_Task_Definition_Arn;
	}

	/* Gets ID of ECS task*/
	public String getEcs_Task_ID() {
		return ecs_Task_ID;
	}
}