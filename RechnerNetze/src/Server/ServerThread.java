package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {
	private long threadId;
	private Socket connectionSocket;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private String clientSentence, capitalizedSentence;

	public ServerThread(Socket serverSocket) {
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
		while (!this.connectionSocket.isClosed()) {
			readInput();
			execCmd();
		}
	}

	private void execCmd() {
		String cmd;
		if (!this.clientSentence.contains(" "))
			cmd = this.clientSentence;
		else
			cmd = this.clientSentence.split(" ")[0];
		cmd = cmd.toLowerCase();
		if (cmd.equals("user")) {
			write("+OK");
		} else if (cmd.equals("pass")) {
			write("+OK");
		} else if (cmd.equals("capa")) {
			write(capa());
		} else if (cmd.equals("stat")) {
			write(stat());
		} else if (cmd.equals("top")) {
			write(top());
		} else if (cmd.equals("retr")) {
			write(retr());
		} else if (cmd.equals("list")) {
			write(list());
		} else if (cmd.equals("dele")) {
			write(dele());
		} else if (cmd.equals("quit")) {
			write("+OK closing...");
			try {
				this.connectionSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private String capa() {
		String ret = "";
		ret += "+OK Capability list follows";
		ret += "\nUSER";
		ret += "\nPASS";
		ret += "\nCAPA";
		ret += "\nSTAT";
		ret += "\nTOP";
		ret += "\nRETR";
		ret += "\nLIST";
		ret += "\nDELE";
		ret += "\nQUIT";
		ret += "\r\n.";
		return ret;
	}

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
			ret += "\n" + arr[i];
		}
		ret += "\r\n.";
		return ret;
	}

	private String dele() {
		int idx = -1;
		if (!this.clientSentence.contains(" "))
			return "-ERR no param";
		else
			idx = Integer.parseInt(this.clientSentence.split(" ")[1]);
		String ret = "";
		SampleDataBase.messages.remove(idx - 1);
		ret += "+OK message " + idx + " deleted";
		return ret;
	}

	private String list() {
		String ret = stat();
		ret += "\n";
		int idx = 1;
		for (String msg : SampleDataBase.messages) {
			ret += "" + idx++;
			ret += " " + msg.getBytes().length;
			ret += "\n";
		}
		ret = ret.substring(0, ret.length() - 2);
		ret += "\r\n.";
		return ret;
	}

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

	private void write(String string) {
		this.capitalizedSentence = string;
		System.out.println("Writing: " + this.capitalizedSentence);
		try {
			this.outToClient.writeBytes(this.capitalizedSentence + "\r\n");
			this.outToClient.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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
