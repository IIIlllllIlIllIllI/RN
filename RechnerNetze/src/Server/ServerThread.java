package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Path;

public class ServerThread implements Runnable {
	private long threadId;
	private String request;
	private FileInputStream Datain;
	private OutputStream out;
	
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
			this.out = this.connectionSocket.getOutputStream();
			this.outToClient =new BufferedWriter(new OutputStreamWriter(out));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void run() {
		this.threadId = Thread.currentThread().getId();
		System.out.println("1 Client Connected");
		write("Connected to Severs");
		//solange die Verbindung offen ist:
		//Befehl auslesen
		//Befehl bearbeiten
		while (!this.connectionSocket.isClosed()) {
			this.request = readInput();
		
			
			
			try {
				Thread.currentThread().sleep(15000);
				write("no request");
				quit();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void doget(File e) throws IOException{
		write("HTTP/0.1  200 OK");
		this.Datain = new FileInputStream(e);
		byte[] buf = new byte[Datain.available()];
		Datain.read(buf);
		out.write(buf);
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
		if(!this.connectionSocket.isClosed()){
			try {
				System.out.println("Writing: " + string);
				this.outToClient.write(string + "\r\n");
				this.outToClient.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		String[] a = ret.split(" ");
		System.out.println("a[0]= " + a[0] +"a[1]= "+a[1]);
		try{
			if(a[0].toUpperCase().equals("GET") && new File(documentRoot+"/"+a[1]).exists()){
				doget(new File(documentRoot+"/"+a[1]));
			}else{
				write("HTTP/0.1 400 Bad Request");
				quit();
			}
		}catch(ArrayIndexOutOfBoundsException A){
			write("HTTP/0.1 400 Bad Request");
			quit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
