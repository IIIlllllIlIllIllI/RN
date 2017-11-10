package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	// port von pop3 ist 110
	static private final int pop3_port=110;
	private int port;
	private String hostname;
	private Socket socket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	
	
	public Client(){
		this.port= pop3_port;
	}
	public void connect(String hostname) throws Exception {
		this.hostname = hostname;
		//Socket implementieren
		this.socket = new Socket(this.hostname,this.port);
		//Informationen zu Server
		this.outToServer = new DataOutputStream(this.socket.getOutputStream());
		//Informationen aus Server
		this.inFromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}
	public void connect(String hostname,int port) throws Exception {
		this.port=port;
		this.hostname = hostname;
		connect(this.hostname, this.port);
	}
	public String getInfo() {
		String a="";
		try {
			a = this.inFromServer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	public void send(String message) throws IOException{
		this.outToServer.writeBytes(message+"\r\n");
		// flush all Info zu Server, wichtig für kleine Message!
		this.outToServer.flush();
	}
	public void anmelden(String name, String psw){
		try {
			send("USER "+name);
			send("PASS "+psw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
