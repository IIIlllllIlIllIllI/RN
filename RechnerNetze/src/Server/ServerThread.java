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
	private static final String GET = "GET";
	private static final String INDEXFILE = "index.html";
	private String request;
	private FileInputStream Datain;
	private OutputStream out;
	private Socket connectionSocket;
	private BufferedReader inFromClient;
	private BufferedWriter outToClient;
	private String documentRoot;

	// initialiesiert Objectvariablen
	public ServerThread(Socket clientSocket, String documentRoot) {
		this.documentRoot = documentRoot;
		this.connectionSocket = clientSocket;
		try {
			this.inFromClient = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
			this.out = this.connectionSocket.getOutputStream();
			this.outToClient = new BufferedWriter(new OutputStreamWriter(out));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		System.out.println("Client Connected to thread " + Thread.currentThread().getId());
		// write("Connected to Severs");
		// solange die Verbindung offen ist:
		// Befehl auslesen
		// Befehl bearbeiten
		int timeout = 15 * 1000;
		while (!this.connectionSocket.isClosed()) {
			if (timeout <= 0) {
				// write("timeout: no request");
				System.out.println("Timeout thread " + Thread.currentThread().getId());
				quit();
				continue;
			}
			try {
				if (this.inFromClient.ready()) {
					this.request = readInput();
					handleRequest();
				} else {
					try {
						// versuche 100 ms zu schalafen
						Thread.sleep(100);
						timeout -= 100;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Closing thread " + Thread.currentThread().getId());
	}

	private void doget(File e) throws IOException {
		//write("HTTP/0.9 200 OK");
		System.out.println(e.getAbsolutePath());
		this.Datain = new FileInputStream(e);
		byte[] buf = new byte[Datain.available()];
		Datain.read(buf);
//		buf=("<html>\r\n" + 
//				"	<head><title>Test</title></head>\r\n" + 
//				"	<body>\r\n" +  
//				"		<p>It works...!</p>\r\n" + 
//				"	</body>\r\n" + 
//				"</html>\r\n").getBytes();
		out.write(buf);
		out.flush();
	}

	private void quit() {

		try {
			this.inFromClient.close();
			this.out.close();
			this.outToClient.close();
			this.connectionSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// sendet String zum Client
	private void write(String string) {
		if (!this.connectionSocket.isClosed()) {
			try {
				System.out.println("Writing: " + string);
				this.outToClient.write(string + "\r\n");
				this.outToClient.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// Liest Clientinput
	private String readInput() {
		String ret = "";
		try {
			ret = this.inFromClient.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Received: " + ret);
		return ret;
	}

	private void handleRequest() {
		String[] a = this.request.split(" ");
		File file;
		String cmd;
		try {
			// alles nach dem 2ten leerzeichen wird ignoriert
			cmd = a[0];
			System.out.println("a[0]= " + a[0] + "\na[1]= " + a[1]);
			file = new File(documentRoot + a[1]);

		} catch (ArrayIndexOutOfBoundsException A) {
			write("HTTP/0.1 400 Bad Request");
			quit();
			return;
		}

		try {
			// wenn es ein ordner ist:
			// suche index datei
			if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					if (f.getName().equals(INDEXFILE)) {
						file = f;
					}
				}
			}
			if (cmd.equals(GET) && file.exists() && !file.isDirectory()) {
				doget(file);
			} else {
				write("HTTP/0.1 400 Bad Request");
				quit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// write("Closing Connection");
		quit();
	}

}
