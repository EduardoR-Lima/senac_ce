package DoisDeAgosto;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection implements Closeable {
	private static final String host = "jdbc:postgresql://localhost:5432/";
	private static final String user = "postgres";
	private static final String password = "postgres";
	
	private Connection conn;
	private String tableName;
	
	public DataBaseConnection(String dataBaseName, String tableName) {
		this.tableName = tableName;
		
		String url = host.concat(dataBaseName);
		connect(url);
	}
	
	public DataBaseConnection(String tableName) {
		this("Atividade", tableName);
	}
	
	private void connect(String url) {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
