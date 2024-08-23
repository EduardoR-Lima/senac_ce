package UmDeAgosto;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresConnection implements Closeable{
	private static final String host_url = "jdbc:postgresql://localhost:5432/";
	
	private final String url;
	private final String user;
	private final String password;
	
	private Connection conn;
	private ResultSet result;
	
	
	public PostgresConnection(String dataBaseName, String user, String password) {
		this.url = host_url.concat(dataBaseName);
		this.user = user;
		this.password = password;
		this.connect();
	}
	
	public PostgresConnection() {
		this("Atividade", "postgres", "postgres");
	}
	
	private void connect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeQuery(String query) {
		try {
			this.result = conn.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getResult() throws Exception{
		if (result != null) {
			return this.result;
		} else {
			throw new Exception("Não há resultados disponíveis");
		}
	}
	
	

	@Override
	public void close() throws IOException {
		try {
			if (conn != null) conn.close();
			if (result != null) result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
