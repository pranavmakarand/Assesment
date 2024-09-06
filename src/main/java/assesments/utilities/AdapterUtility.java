package main.java.assesments.utilities;

import main.java.assesments.adapters.Version2Adapter;
import main.java.assesments.adapters.Version3Adapter;
import main.java.assesments.adapters.Version4Adapter;
import main.java.assesments.adapters.Version5Adapter;
import main.java.assesments.adapters.Version7Adapter;
import main.java.assesments.adapters.VersionAdapter;
import main.java.assesments.exceptions.AdapterNotFoundException;

/* Utility class for different version adapter */

public class AdapterUtility {

	/* Returns a default adapter */
	public static VersionAdapter defaultAdapter() {
		return new Version2Adapter();
	}

	/* Returns a custom adapters */
	public static VersionAdapter customAdapter(String version) throws AdapterNotFoundException {
		switch (version) {
		case "2":
			return new Version2Adapter();
		case "3":
			return new Version3Adapter();
		case "4":
			return new Version4Adapter();
		case "5":
			return new Version5Adapter();
		case "7":
			return new Version7Adapter();
		default:
			throw new AdapterNotFoundException("Adapter not found for version: " + version);
		}
	}

}