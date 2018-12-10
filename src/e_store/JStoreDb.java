package e_store;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class JStoreDb {
	Connection con=null;
	public static Connection getConnection() {
		String url="jdbc:oracle:thin:@//localhost:1521/xe";
		String uname="jpmurr1998";
		String pass="xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,uname,pass);
			return con;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
