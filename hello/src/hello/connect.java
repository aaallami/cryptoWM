package hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class connect {

	public static void main(String[] args) {
		tupleupdate(2000,1,"A");
		System.out.println("hello"+selecttuple("1000"));
		// TODO Auto-generated method stub
		//System.out.println("class of worker= "+getRowNumber());
		try{ 
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/t","root","");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		//ResultSet rs=stmt.executeQuery("select * from Sheet1");  
		ResultSet rs2=stmt.executeQuery("select * from 5k");
		while(rs2.next())  
		//System.out.println("class of worker= "+rs.getString(3));
			System.out.println("class of worker= "+rs2.getInt(1));  
		con.close();  
		}catch(Exception e){ System.out.println(e);
		  
		
		}
	}
	
	


	
	public static void tupleupdate(int set, int where, String att){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con4=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			//Object mysqlConn;
		       	PreparedStatement st = con4.prepareStatement("UPDATE "+"5k"+" SET "+att+" = ? " + " WHERE pk = ?");
		    	st.setInt(1, set);
				st.setInt(2, where);//499 is the one that will be udated bu the new value 2 

				st.executeUpdate();

				System.out.println("Record is updated to DBUSER table!");
				con4.close();
		}
		catch (Exception ex){
		    System.out.println(ex.getMessage());

		}	
	}
	
	//***************************************************************************************
	
	public static String selecttuple(String pk){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			Statement stmt=con.createStatement();  
			ResultSet rs2=stmt.executeQuery("SELECT C FROM 5k WHERE pk ="+pk);
			Class.forName("com.mysql.jdbc.Driver");  
			
			while(rs2.next())     	
				return(rs2.getString(1));
		}catch (Exception ex){
		    System.out.println(ex.getMessage());

		}
		return "";
		}
	//******************************************************************************





	private static void Return(String string) {
		// TODO Auto-generated method stub
		
	}
	
}


		

	/*public static int getRowNumber(){

		   int numberRow = 0;
		   //Connection mysqlConn = DriverManager.getConnection(HOST, USER_ID, PASSWORD);

		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/newdatatest","root","");  
			//here sonoo is database name, root is username and password  
		    //String query = "select count(*) from dataTable";
		    Object mysqlConn;
		    Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select count(*) from Sheet1");
		    while(rs.next()){
		        numberRow = rs.getInt("count(*)");
		    }
		}catch (Exception ex){
		    System.out.println(ex.getMessage());
		}
		return numberRow;
		}
*/
	

