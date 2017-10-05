package distributed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class selecttuple {
	public static int selecttuple(String pk, String stratt, int intatt, String tablename){
		Connection con3=null;
			try{
				Class.forName("com.mysql.jdbc.Driver");  
				 con3=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/t","root","");  
				Statement stmt=con3.createStatement();  
				ResultSet rs2=stmt.executeQuery("SELECT "+stratt+" FROM "+tablename+" WHERE pk ="+pk);
				Class.forName("com.mysql.jdbc.Driver");  
				
				while(rs2.next()) {    	
					return(rs2.getInt(1));
					}con3.close();
				
			}catch (Exception ex){
			    System.out.println(ex.getMessage());

			}finally{

				if(con3!=null)
					try {
						con3.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
			return 0;
			}


}
