import Helpers.ParameterChecker;
import Server.Server;

public class Main {

	public static void main(String[] args) {
		ParameterChecker parameterChecker=new ParameterChecker(args);
		Server server=new Server(parameterChecker.getDocumentRoot(), parameterChecker.getPort());
		
	}
	
}
