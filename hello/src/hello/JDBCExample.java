package hello;
//Import required packages
import java.sql.*;

public class JDBCExample {
// JDBC driver name and database URL
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
static final String DB_URL = "jdbc:mysql://localhost:3306/t";
static Connection conn = null;
static Statement stmt = null;
static Connection conn2 = null;

//  Database credentials
static final String USER = "root";
static final String PASS = "";

public static void main(String[] args) {
try{
   // Register JDBC driver
   Class.forName("com.mysql.jdbc.Driver");

   // Open a connection
   System.out.println("Connecting to database...");
   conn = DriverManager.getConnection(DB_URL,USER,PASS);

   // Create statement
   System.out.println("Creating statement...");
   stmt = conn.createStatement();
   Statement stmt2 = conn.createStatement();

   // Set auto-commit to false
   conn.setAutoCommit(false);

   // First, let us select all the records and display them.

   // Create SQL statement
   //String SQL = "INSERT INTO 5k (pk) " + 
   //             "VALUES(200000000)";
   // Add above SQL statement in the batch.
   //stmt.addBatch(SQL);

   // Create one more SQL statement
   

   // Create one more SQL statement
   //SQL = "UPDATE 5k SET pk = 35 " +
   //      "WHERE pk = 100";
   // Add above SQL statement in the batch.
   //stmt.addBatch(SQL);

   // Create an int[] to hold returned values
   //int[] count = stmt.executeBatch();

   //Explicitly commit statements to apply changes
   //conn.commit();

   // Again, let us select all the records and display them.
   long t1=System.nanoTime();

   printRows( stmt2 );
   System.out.println("time=" + ((System.nanoTime()-t1)/1000000000.0));

   // Clean-up environment
   //stmt.close();
   //conn.close();
}catch(SQLException se){
   //Handle errors for JDBC
   se.printStackTrace();
}catch(Exception e){
   //Handle errors for Class.forName
   e.printStackTrace();
}finally{
   //finally block used to close resources
   try{
      if(stmt!=null)
         stmt.close();
   }catch(SQLException se2){
   }// nothing we can do
   try{
      if(conn!=null)
         conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }//end finally try
}//end try
System.out.println("Goodbye!");
}//end main

public static void printRows(Statement stmt2) throws SQLException{
System.out.println("Displaying available rows...");
// Let us select all the records and display them.
String sql = "SELECT pk FROM 5k";
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){
   //Retrieve by column name
   int id  = rs.getInt("pk");
   System.out.println("ID: " + id);
	//String SQL ="UPDATE "+tablename+" SET "+stratt+" =  "+Ai.toString() + " WHERE pk = "+pk.toString();
   String SQL = "UPDATE 5k SET pk ="+ id+ " WHERE pk="+(id+500);
   stmt2.addBatch(SQL);

   // Create an int[] to hold returned values
   //Explicitly commit statements to apply changes
   //Display values
}   
int[] count = stmt2.executeBatch();
conn.commit();
System.out.println();
rs.close();
}//end printRows()
}//end JDBCExample