import Helpers.ParameterChecker;
import Server.Server;

public class Main {

	public static void main(String[] args) {
//		String a[] = {"aa","5678", "/Users/wenboda/Desktop/5Semester/Rechnernetze/documentRoot"};
		String a[] = {"aa","5678", "G:\\Temp\\documentRoot"};
		ParameterChecker parameterChecker=new ParameterChecker(a);
		Server server=new Server(parameterChecker.getDocumentRoot(), parameterChecker.getPort());
		
	}
	
}
