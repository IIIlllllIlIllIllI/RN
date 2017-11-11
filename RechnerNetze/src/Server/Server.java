package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
	// port von pop3 ist 110
	static private final int pop3_port = 110;
	private int port;
	private ServerSocket socket;

	public Server() {
		this.port = pop3_port;
		createSocket();
		Thread test=new Thread(new ServerThread(socket));
		test.start();
		try {
			test.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Server(int port) {
		this.port = port;
		createSocket();
	}

	private void createSocket() {
		try {
			this.socket = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
