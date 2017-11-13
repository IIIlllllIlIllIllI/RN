package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class ServerThread implements Runnable {
	private long threadId;
	private Socket connectionSocket;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private String clientSentence;
	//zustand
	private boolean authorization=true,user=false,pass=false;
	private ArrayList<Integer> marked; 

	//initialiesiert Objectvariablen
	public ServerThread(Socket serverSocket) {
		this.marked=new ArrayList<>();
		this.connectionSocket = serverSocket;
		try {
			this.inFromClient = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
			this.outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());
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
			execCmd();
		}
	}

	private void execCmd() {
		String cmd;
		if (!this.clientSentence.contains(" "))
			//befehl hat keine parameter
			cmd = this.clientSentence;
		else
			cmd = this.clientSentence.split(" ")[0];
		cmd = cmd.toLowerCase();
		if (this.authorization&&cmd.equals("user")) {
			write("+OK");
			this.user=true;
			this.authorization=!(this.user&&this.pass);
		} else if (this.authorization&&cmd.equals("pass")) {
			write("+OK");
			this.pass=true;
			this.authorization=!(this.user&&this.pass);
		} else if (cmd.equals("capa")) {
			write(capa());
		} else if (!this.authorization&&cmd.equals("stat")) {
			write(stat());
		} else if (!this.authorization&&cmd.equals("top")) {
			write(top());
		} else if (!this.authorization&&cmd.equals("retr")) {
			write(retr());
		} else if (!this.authorization&&cmd.equals("list")) {
			write(list());
		} else if (!this.authorization&&cmd.equals("dele")) {
			write(dele());
		} else if (!this.authorization&&cmd.equals("noop")) {
			write(noop());
		} else if (!this.authorization&&cmd.equals("rset")) {
			write(rset());
		} else if (cmd.equals("quit")) {
			write("+OK closing...");
			try {
				for(int i:marked) {
					SampleDataBase.messages.remove(i - 1);
					for(int j:marked) {
						if(j>i) {
							marked.set(marked.indexOf(j),j-1);
						}
					}
				}				
				this.connectionSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			write("-ERR");
		}

	}

	//Alle Markierungen werden gelöscht
	private String rset() {
		marked.clear();
		return "+OK";
	}

	private String noop() {
		return "+OK";
	}

	private String capa() {
		String ret = "";
		ret += "+OK Capability list follows";
		ret += "\r\nUSER";
		ret += "\r\nPASS";
		ret += "\r\nCAPA";
		ret += "\r\nSTAT";
		ret += "\r\nTOP";
		ret += "\r\nRETR";
		ret += "\r\nLIST";
		ret += "\r\nDELE";
		ret += "\r\nQUIT";
		ret += "\r\n.";
		return ret;
	}

	//head und anfang der nachricht wird ausgegeben
	private String top() {
		int idx = -1, lines = -1;
		if (!this.clientSentence.contains(" "))
			return "-ERR no param";
		else if (this.clientSentence.split(" ").length < 3)
			return "-ERR no param";
		else {
			idx = Integer.parseInt(this.clientSentence.split(" ")[1]);
			lines = Integer.parseInt(this.clientSentence.split(" ")[2]);
		}
		String ret = "";
		ret += "+OK ";
		String msg = SampleDataBase.messages.get(idx - 1);
		String head = msg.substring(0, msg.indexOf("\n\n"));
		String body = msg.substring(msg.indexOf("\n\n") + 2);
		ret += head;
		String arr[] = body.split("\n");
		for (int i = 0; i < Math.min(lines, arr.length); i++) {
			ret += "\r\n" + arr[i];
		}
		ret += "\r\n.";
		return ret;
	}

	//markiert Nachricht zum Löschen
	private String dele() {
		int idx = -1;
		if (!this.clientSentence.contains(" "))
			return "-ERR no param";
		else
			idx = Integer.parseInt(this.clientSentence.split(" ")[1]);
		String ret = "";
		if(!marked.contains(idx))
		marked.add(idx);
		ret += "+OK message " + idx + " marked for deletion";
		return ret;
	}

	//Listet alle Mails auf
	private String list() {
		String ret = stat();
		ret += "\r\n";
		int idx = 1;
		for (String msg : SampleDataBase.messages) {
			ret += "" + idx++;
			ret += " " + msg.getBytes().length;
			ret += "\r\n";
		}
		ret = ret.substring(0, ret.length() - 2);
		ret += "\r\n.";
		return ret;
	}

	//Gibt eine Mail zurück
	private String retr() {
		int idx = -1;
		if (!this.clientSentence.contains(" "))
			return "-ERR no param";
		else
			idx = Integer.parseInt(this.clientSentence.split(" ")[1]);
		String ret = "";
		ret += "+OK ";
		ret += SampleDataBase.messages.get(idx - 1).getBytes().length + "\r\n";
		ret += SampleDataBase.messages.get(idx - 1);
		ret += "\r\n.";
		return ret;
	}

	//Status mit Anzahl Mails und Größe
	private String stat() {
		String ret = "";
		ret += "+OK ";
		ret += SampleDataBase.messages.size() + " ";
		int size = 0;
		for (String msg : SampleDataBase.messages) {
			size += msg.getBytes().length;
		}
		ret += size;
		return ret;
	}

	//sendet String zum Client
	private void write(String string) {
		System.out.println("Writing: " + string);
		try {
			this.outToClient.writeBytes(string + "\r\n");
			this.outToClient.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Liest Clientinput
	private String readInput() {
		try {
			this.clientSentence = this.inFromClient.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Received: " + this.clientSentence);
		if (this.clientSentence == null) {
			this.clientSentence = "";
		}
		return this.clientSentence;
	}

}
