package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.util.ArrayList;

public class Server {
	// port von http ist 80
	static private final int http_port = 80;
	private ServerSocket serverSocketTCP;
	private ArrayList<Thread> threads;
	Path documentRoot;

	private void startAcceptingConnections() {
		this.threads=new ArrayList<>();
		while(true) {
			//startet f�r jede Verbindung einen neuen Thread
			try {
				Thread severThread = new Thread(new ServerThread(this.serverSocketTCP.accept(),documentRoot));
				threads.add(severThread);
				threads.get(threads.size()-1).start();
			} catch (IOException e) {
				e.printStackTrace();
			} 	
			
		}
	}

	public Server(Path documentRoot,int port) {
		this.documentRoot=documentRoot;
		createServerSocket(port);
		startAcceptingConnections();
	}

	private void createServerSocket(int port) {
		try {
			this.serverSocketTCP = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
