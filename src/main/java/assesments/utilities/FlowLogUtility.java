package main.java.assesments.utilities;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.logging.log4j.Level;

import main.java.assesments.adapters.VersionAdapter;
import main.java.assesments.constants.UtilityConstants;
import main.java.assesments.exceptions.AdapterNotFoundException;
import main.java.assesments.exceptions.CustomCalculationException;
import main.java.assesments.exceptions.LogLineParseException;
import main.java.assesments.strategies.*;

/* Flow log utility */
public class FlowLogUtility {

	LogFormatStrategy strat;

	public FlowLogUtility(LogFormatStrategy strat) {
		this.strat = strat;
	}

	// A map to store the lookup table (dstport + protocol) -> tag.
	public Map<String, String> lookupTable = new HashMap<>();

	// Maps to store the counts for tags and port/protocol combinations.
	public Map<String, Integer> tagCounts = new HashMap<>();

	// Maps to store the counts port/protocol combinations.
	public Map<String, Integer> portProtocolCounts = new HashMap<>();

	// Method to load the lookup table from the CSV file.
	public void loadLookupTable(String filePath) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;

			// Ignoring the first line as it is header
			br.readLine();

			UtilityConstants.logger.log(Level.INFO, "Starting lookup table loading");

			while ((line = br.readLine()) != null) {

				String[] parts = line.split(",");

				if (parts.length == 3) {
					String key = parts[0].trim() + "," + parts[1].trim().toLowerCase();
					String tag = parts[2].trim();

					lookupTable.put(key, tag);
					UtilityConstants.logger.log(Level.INFO, "Key : " + key + "Tag : " + tag);
				}
			}

		} catch (IOException e) {

			UtilityConstants.logger.error(" Failed to read the loadlookup csv file " + e.getMessage());
			
			throw new IOException(" Failed to read the loadlookup csv file " + e.getMessage());
		}
	}

	/* Parse log utility */
	public void parseAndComputeData(String filePath) throws LogLineParseException, AdapterNotFoundException, CustomCalculationException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String logLine;

			UtilityConstants.logger.log(Level.INFO, "Reading the flow log data : ");

			while ((logLine = br.readLine()) != null) {

				if (logLine.trim().isEmpty()) {
					continue;
				}

				try {

					strat.parcingLogLineData(logLine, this);

				} catch (LogLineParseException e) {

					UtilityConstants.logger.error(" Failed to parse log line : " + e.getMessage());
					
					throw new LogLineParseException(logLine);
				}

				VersionAdapter adapter = null;

				adapter = strat.getAdapter();

				try {
					
					calculateTagAndPortProtocolFreq(adapter);
					
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					
					throw new CustomCalculationException(" Error during tag, port-protocol frequency calculation ", e);
				}
			}
		} catch (IOException e) {

			UtilityConstants.logger.error("Failed to read the flow log file " + e.getMessage());
			
			throw new IOException("Failed to read the flow log file " + e.getMessage());
		}
	}

	/* Method to calculate the Tag and Port-Protocol frequency. */
	private void calculateTagAndPortProtocolFreq(VersionAdapter adapter)
		throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException {

		Class<?> clazz = Class.forName(adapter.getClass().getName());
		Object concreteAdapter = clazz.cast(adapter);

		Method method = clazz.getMethod("getProtocol"); // Add constants o enums

		String protocolMapped = ProtocolUtility.mapProtocol((int) method.invoke(concreteAdapter));

		Method method2 = clazz.getMethod("getDesPort");

		int desPort = (int) method2.invoke(concreteAdapter);

		String key = desPort + "," + protocolMapped;

		UtilityConstants.logger.log(Level.INFO, "Created Key : " + key);

		String tag = lookupTable.getOrDefault(key, "Untagged"); // Need to change this

		UtilityConstants.logger.log(Level.INFO, "Tag lookup : " + tag);

		// Update tag counts
		tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);

		// Update port/protocol combination counts
		portProtocolCounts.put(key, portProtocolCounts.getOrDefault(key, 0) + 1);
	}

	// Method to generate output and write it to file
	public void generateOutputs(String filePath) throws IOException {

		UtilityConstants.logger.log(Level.INFO, "Wrting to file : " + filePath);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

			writer.write("Tag Counts:");
			writer.newLine();
			writer.write("Tag,Count");
			writer.newLine();

			for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
				UtilityConstants.logger.log(Level.INFO, " EntrySet Key : " + entry.getKey() + " EntrySet Value : " + entry.getValue());
				writer.write(entry.getKey() + "," + entry.getValue());
				writer.newLine();
			}

			writer.newLine();

			UtilityConstants.logger.log(Level.INFO, " Port/Protocol Combination Counts: ");

			writer.write(" Port/Protocol Combination Counts : ");

			writer.newLine();

			UtilityConstants.logger.log(Level.INFO, " Port, Protocol, Count ");

			writer.write(" Port, Protocol, Count ");

			writer.newLine();

			for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
				String[] portProtocol = entry.getKey().split(",");
				UtilityConstants.logger.log(Level.INFO,
					portProtocol[0] + "," + portProtocol[1] + "," + entry.getValue());
				writer.write(portProtocol[0] + "," + portProtocol[1] + "," + entry.getValue());
				writer.newLine();
			}

			UtilityConstants.logger.log(Level.INFO, " Output successfully written to file: " + filePath);

		} catch (IOException e) {
			UtilityConstants.logger.error(" Error writing to file " + e.getMessage());
			System.err.println(" Error writing to file: " + e.getMessage());
			throw new IOException(" Error writing to file: " + e.getMessage());
		}
	}
}