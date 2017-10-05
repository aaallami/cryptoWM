package distributed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class tupleupdate {
	public static void tupleupdate1(int set, int where, String att, String tablename){
		Connection con4=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			con4=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			//Object mysqlConn;
		       	PreparedStatement st = con4.prepareStatement("UPDATE "+tablename+" SET "+att+" = ? " + " WHERE pk = ?");
		    	st.setInt(1, set);
				st.setInt(2, where);//499 is the one that will be udated bu the new value 2 

				st.executeUpdate();

				System.out.println("Record is updated to DBUSER table!");
				con4.close();
		}
		catch (Exception ex){
		    System.out.println(ex.getMessage());

		}	
		finally{
			 /*This block should be added to your code
			  * You need to release the resources like connections
			  */
			 if(con4!=null)
				try {
					con4.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
