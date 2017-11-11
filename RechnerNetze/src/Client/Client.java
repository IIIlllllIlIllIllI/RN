package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Client {
	// port von pop3 ist 110
	static private final int pop3_port = 110;
	private int port;
	private String hostname;
	private Socket socket;
	private BufferedReader inFromServer;
	// private DataOutputStream outToServer;
	private BufferedWriter outToServer;

	public Client() {
		this.port = pop3_port;
	}

	public void connect(String hostname) throws Exception {
		this.hostname = hostname;
		// Socket implementieren
		this.socket = new Socket(this.hostname, this.port);
		System.out.println("Verbinden...");
		// Informationen zu Server
		// this.outToServer = new DataOutputStream(this.socket.getOutputStream());
		this.outToServer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		// Informationen aus Server
		this.inFromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}

	public void connect(String hostname, int port) throws Exception {
		this.port = port;
		this.hostname = hostname;
		connect(this.hostname, this.port);
	}

	public String getInfo() {
		String a = "";
		try {
			a = this.inFromServer.readLine();
			System.out.println("Server: " + a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	// Hier wir nehmen das erste word -- "+OK" oder "-ERR"
	public String getErsteInfo(String a) {

		StringTokenizer str = new StringTokenizer(a, " ");

		return str.nextToken();

	}

	public String send(String message) throws IOException {
		this.outToServer.write((message + "\r\n"));
		// flush all Info zu Server, wichtig für kleine Message!

		this.outToServer.flush();
		String re = getInfo();

		return re;
	}

	public void quit() throws IOException {
		send("QUIT");
	}

	public void close() {
		try {
			inFromServer.close();
			outToServer.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void user(String Uname) throws IOException {
		String Info = "";
		Info = getErsteInfo(getInfo());
		// Richtig Verbindung
		if (!"+OK".equals(Info)) {
			System.out.println("Server nicht ersteht ");
		}
		Info = getErsteInfo(send("user " + Uname));

		if (!"+OK".equals(Info)) {
			System.out.println("Username ist falsch");
		}
	}

	public void pass(String pass) throws IOException {
		String Info = "";
		Info = getErsteInfo(send("pass " + pass));
		if (!"+OK".equals(Info)) {
			System.out.println("Psw ist falsch");
		}
	}

	// Anzahl von mail
	public void stat() throws IOException {
		String get = "";
		get = send("STAT");
		int anzahl = 0;
		// get anzahl, erste word.
		StringTokenizer st = new StringTokenizer(get, " ");
		st.nextToken();
		anzahl = Integer.parseInt(st.nextToken());
		System.out.println("Hat " + anzahl + " emails.");
	}

	// list
	public void list() throws IOException {
		String Info = "";
		Info = send("list");
		String message = "";
		while (!".".equals(Info)) {
			message = message + Info + "\n";
			Info = inFromServer.readLine().toString();
		}
		System.out.println("list:" + message);
	}

	// eine Email auswaehlen
	public void retr(int index) throws IOException {
		String Info = "";
		Info = send("retr" + " " + index);
		String message = "";
		while (!".".equals(Info)) {
			message = message + Info + "\n";
			Info = inFromServer.readLine().toString();
		}
		System.out.println(index + "te email ist :" + message);

	}
}
