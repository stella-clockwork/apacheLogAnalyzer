package apacheLogAnalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv4Address extends IPAddress implements Comparable<IPv4Address>{
	public short[] ipaddress = new short[4]; //0～255なので8bit幅にしたいがunsighnedにできないので16bit幅

	@Override
	public String toString() {
		return ipaddress[0] + "." + ipaddress[1] + "." + ipaddress[2] + "." + ipaddress[3];
	}

	@Override
	public int hashCode() {
		return ipaddress[0]*256*256*256 + ipaddress[1]*256*256 + ipaddress[2]*256 + ipaddress[3];
	}

    public boolean equals(Object obj) {
    	if (obj instanceof IPv4Address) if (this.compareTo((IPv4Address) obj) == 0) return true;
        return false;
    }

	public IPv4Address(String ip) {
		String[] ips = ip.split("\\.");
		for (int i = 0; i < 4; i++) ipaddress[i] = Short.parseShort(ips[i]);
	}

	public static boolean isCorrestIPv4(String ip) {
		String one_elem = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
		Pattern p = Pattern.compile("^"+ one_elem +"\\."+ one_elem +"\\."+ one_elem +"\\."+ one_elem +"$");
		Matcher m = p.matcher(ip);
		return m.find();
	}

	public int compareTo(IPv4Address ip) {
		if (this.ipaddress[0] > ip.ipaddress[0]) return 1;
		else if (this.ipaddress[0] < ip.ipaddress[0]) return -1;

		if (this.ipaddress[1] > ip.ipaddress[1]) return 1;
		else if (this.ipaddress[1] < ip.ipaddress[1]) return -1;

		if (this.ipaddress[2] > ip.ipaddress[2]) return 1;
		else if (this.ipaddress[2] < ip.ipaddress[2]) return -1;

		if (this.ipaddress[3] > ip.ipaddress[3]) return 1;
		else if (this.ipaddress[3] < ip.ipaddress[3]) return -1;


		return 0;
	}

}
