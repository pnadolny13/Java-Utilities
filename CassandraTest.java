import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CassandraTest {

	public static void main(String[] args) {
		Connection con = null;
		String url = "jdbc:cassandra//HOST:PORT/keyspace";
		String host = "localhost";
		String port = "9160";
		String schema = "system";
		String username = "cassandra";
		String password = "cassandra";
		String CASSANDRA_DRIVER = "org.apache.cassandra.cql.jdbc.CassandraDriver";

		try {
			Class.forName(CASSANDRA_DRIVER);
			url = "jdbc:cassandra://HOST:PORT/SCHEMA".replace("HOST", host).replace("SCHEMA", schema);
			
			url = "jdbc:cassandra://127.0.0.1:9042/keyspace1".replace("HOST", host).replace("SCHEMA", schema);
			url = "jdbc:cassandra://127.0.0.1:9160/system";

			if (port != null && !port.isEmpty()) {
				url = url.replace(":PORT", ":" + port);
			} else {
				url = url.replace(":PORT", "");
			}

			con = DriverManager.getConnection(url, username, new String(password));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(">>>>>DRIVER NOT FOUND. PLEASE ENSURE YOU HAVE ACCESS TO JDBC DRIVER");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(">>>>> SQL EXCEPTION");
			e.printStackTrace();
		}

	}

}





C:\Users\pnadolny\Documents\Deloitte Information\USPTO\Cassandra Data\VM_Interface\Search_Results_Insert.txt
