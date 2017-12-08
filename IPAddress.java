package apacheLogAnalyzer;

public class IPAddress {
	public static IPAddress getIPAddress(String ip) throws RuntimeException{
		if (IPv4Address.isCorrestIPv4(ip)) {
			return new IPv4Address(ip);
		}

		throw new RuntimeException("invalid ip address");
	}

	public static boolean isCorrectIPAddress(String ip) {
		if (IPv4Address.isCorrestIPv4(ip)) return true;
		return false;
	}

	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

    public boolean equals(Object obj) {
        return (this == obj);
    }
}