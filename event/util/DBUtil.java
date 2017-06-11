package event.util;


import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	static String dbName = "bin\\work";
	static{
		try {
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(protocol + dbName
				+ ";create=true");
	}
	/*public static Connection disConnection() throws SQLException
	{
		return java.sql.DriverManager.getConnection("jdbc:derby:;shutdown=true");
		
	}
	**/
}
