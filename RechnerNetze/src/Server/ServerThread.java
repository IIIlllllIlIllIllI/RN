package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Path;

public class ServerThread implements Runnable {
	private long threadId;
	private Socket connectionSocket;
	private BufferedReader inFromClient;
	private BufferedWriter outToClient;
	private Path documentRoot;

	//initialiesiert Objectvariablen
	public ServerThread(Socket clientSocket,Path documentRoot) {
		this.documentRoot=documentRoot;
		this.connectionSocket = clientSocket;
		try {
			this.inFromClient = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
			this.outToClient =new BufferedWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.threadId = Thread.currentThread().getId();
		System.out.println("[" + this.threadId + "] Server thread started");
		write("+OK connected to Server");
		//solange die Verbindung offen ist:
		//Befehl auslesen
		//Befehl bearbeiten
		while (!this.connectionSocket.isClosed()) {
			readInput();
			
		}
	}


	private void quit() {
		try {
			this.inFromClient.close();
			this.outToClient.close();
			this.connectionSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//sendet String zum Client
	private void write(String string) {
		System.out.println("Writing: " + string);
		try {
			this.outToClient.write(string + "\r\n");
			this.outToClient.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Liest Clientinput
	private String readInput() {
		String ret="";
		try {
			ret = this.inFromClient.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Received: " + ret);
		return ret;
	}

}
