import java.io.IOException;

import Client.Client;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				String host="pop3.126.com";
				Client c = new Client();
				try {
					c.connect(host);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(c.getInfo());
	}
}
