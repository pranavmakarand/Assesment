package main.java.assesments.utilities;

/* Utility that maps that protocol with the number*/
public class ProtocolUtility {
	public static String mapProtocol(int protocolNumber) {
		switch (protocolNumber) {
		case 6:
			return "tcp";
		case 17:
			return "udp";
		case 1:
			return "icmp";
		default:
			return "unknown";
		}
	}
}
