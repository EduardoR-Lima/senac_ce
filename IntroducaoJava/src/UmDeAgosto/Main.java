package UmDeAgosto;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		PostgresConnection connection = new PostgresConnection();
		
		connection.executeQuery("SELECT * FROM casa");
		try {
			System.out.println(connection.getResult().next());
			System.out.println(connection.getResult().getString(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
