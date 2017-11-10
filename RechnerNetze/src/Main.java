import java.io.IOException;

import Client.Client;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Client c = new Client(110,"pop3.126.com");
				
				try {
					c.connection();
					System.out.println(c.getInfo());
					c.anmelden("qwertyhot@126.com", "ll");
					System.out.println(c.getInfo());
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
