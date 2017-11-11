package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
	private long threadId; 
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private String clientSentence,capitalizedSentence;
	public ServerThread(ServerSocket serverSocket) {
		this.serverSocket=serverSocket;
	}
	@Override
	public void run() {
		this.threadId = Thread.currentThread().getId();
		System.out.println("["+this.threadId+"] Server thread started");
		startAccepting();
	}
	private void startAccepting() {
		try {
			this.connectionSocket = this.serverSocket.accept();
			this.inFromClient = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
			this.outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());
			System.out.println("Received: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
			connectionSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private String readInput() {
		try {
			clientSentence = inFromClient.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientSentence;
	}
	

}
