package Helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;

public class ParameterChecker {
	private int port;
	private Path documentRoot;
	public ParameterChecker(String args[]) {
		this.port=getPort(args);
		this.documentRoot=getPath(args);
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Path getDocumentRoot() {
		return documentRoot;
	}
	public void setDocumentRoot(Path documentRoot) {
		this.documentRoot = documentRoot;
	}
	private Path getPath(String[] args) {
		Path path=new File("./").toPath();
		if(args.length<2) {
			throw new InvalidParameterException("kein Parameter vorhanden");
		}
		else {
			
			path=new File(args[2]).toPath();
			System.out.println(path.toString());
			if(!(new File(path.toString()).exists())) {
				throw new InvalidParameterException("Pfad ist nicht Vorhanden");
			}
			
		}
		return path;
	}
	private int getPort(String[] args) {
		int port=80;
		if(args.length<2) {
			throw new InvalidParameterException("kein Parameter vorhanden");
		}
		else {
			try {
			port=Integer.parseInt(args[1]);
			}catch (NumberFormatException e) {
				throw new InvalidParameterException("Port ist kein Integer");
			}
		}
		return port;
	}
}
