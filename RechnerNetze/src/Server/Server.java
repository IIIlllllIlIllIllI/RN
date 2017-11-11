package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
	// port von pop3 ist 110
	static private final int pop3_port = 110;
	private int port;
	private ServerSocket socket;
	private ArrayList<Thread> threads;

	public Server() {
		this.port = pop3_port;
		this.threads=new ArrayList<>();
		createSocket();
		while(true) {
			try {
				threads.add(new Thread(new ServerThread(this.socket.accept())));
				threads.get(threads.size()-1).start();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
//		for(Thread t:threads) {
//			t.join();
//		}
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
