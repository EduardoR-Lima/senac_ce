package quatorzeDeAgosto;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
