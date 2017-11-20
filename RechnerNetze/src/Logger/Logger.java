package Logger;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger implements Runnable {

	private ArrayList<Date> dates;
	private ArrayList<String> requests;
	private ArrayList<String> ips;
	private ArrayList<Integer> ports;
	private SimpleDateFormat dateFormat;

	public Logger() {
		this.dates = new ArrayList<>();
		this.requests = new ArrayList<>();
		this.ips = new ArrayList<>();
		this.ports = new ArrayList<>();
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			printLog();
		}
	}

	synchronized private void printLog() {
		for (int i = 0; i < dates.size(); i++) {
			System.out.println(
					dateFormat.format(dates.get(i)) + " " + requests.get(i) + " " + ips.get(i) + " " + ports.get(i));
		}
		dates.clear();
		requests.clear();
		ips.clear();
		ports.clear();
	}

	synchronized public void addEntry(String request, SocketAddress remoteSocketAddress) {
		String[] split = remoteSocketAddress.toString().split(":");
		String ip = split[0].substring(1);
		int port = Integer.parseInt(split[1]);
		this.dates.add(new Date());
		this.requests.add(request);
		this.ips.add(ip);
		this.ports.add(port);
	}

}
