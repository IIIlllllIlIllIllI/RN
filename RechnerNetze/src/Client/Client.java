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
	private int port;
	private String hostname;
	private Socket socket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	
	
	public Client(int port, String hostname){
		this.port= port;
		this.hostname = hostname;
	}
	public void connection() throws Exception {
		//Socket implementieren
		socket = new Socket(hostname,port);
		//Informationen zu Server
		outToServer = new DataOutputStream(socket.getOutputStream());
		//Informationen aus Server
		inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public String getInfo() throws IOException{
		String a;
		a = inFromServer.readLine();
		return a;
	}
	public void send(String message) throws IOException{
		outToServer.writeBytes(message);
		// flush all Info zu Server, wichtig für kleine Message!
		outToServer.flush();
	}
	public void anmelden(String name, String psw){
		
		try {
			send("user"+name);
			send("pass"+psw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
