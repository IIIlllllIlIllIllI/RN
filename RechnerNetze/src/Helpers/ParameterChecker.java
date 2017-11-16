package Helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;

public class ParameterChecker {
	private int port;
	private String documentRoot;
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
	public String getDocumentRoot() {
		return documentRoot;
	}
	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}
	private String getPath(String[] args) {
		String path="./";
		if(args.length<2) {
			throw new InvalidParameterException("kein Parameter vorhanden");
		}
		else {
			
			path=args[2];
			System.out.println(path);
			if(!(new File(path).exists())) {
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
