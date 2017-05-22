import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public Connection conn = null;
	
	public DB(){
		try{
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/EWHA";
			
			//Open a connection
			conn = DriverManager.getConnection(url, "root", "qwerty123");
		} catch(SQLException e){
			//Handle errors for JDBC
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	} 
}
