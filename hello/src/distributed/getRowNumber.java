package distributed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getRowNumber {
	public static int getRowNumber(String tablename){
		Connection con2=null;
		   int numberRow = 0;
		   //Connection mysqlConn = DriverManager.getConnection(HOST, USER_ID, PASSWORD);

		try{
			Class.forName("com.mysql.jdbc.Driver");  
			 con2=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			//here sonoo is database name, root is username and password  
		    //String query = "select count(*) from dataTable";
		    //Object mysqlConn;
		    Statement st = con2.createStatement();
		    ResultSet rs = st.executeQuery("select count(*) from "+tablename);
		    while(rs.next()){
		        numberRow = rs.getInt("count(*)");
		    }con2.close();
		}catch (Exception ex){
		    System.out.println(ex.getMessage());
		}
		finally{
			 /*This block should be added to your code
			  * You need to release the resources like connections
			  */
			 if(con2!=null)
				try {
					con2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return numberRow;
		}
}
