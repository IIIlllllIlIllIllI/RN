import Client.Client;
import Server.Server;

public class Main {

	public static void main(String[] args) {
		testServer();
//testClient();
	}
	private static void testServer() {
		Server server=new Server();
	}
	private static void testClient() {
		String host = "firemail.de";
		String user = "RN2017RN@firemail.de";
		String pwd = "pmZQWFHsxmuzAqbhaUxW";
		Client c = new Client();
		try {
			c.connect(host);
			c.user(user);
			c.pass(pwd);
			c.stat();
			c.list();
			c.retr(1);
			c.quit();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
