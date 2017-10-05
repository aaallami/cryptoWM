package distributed;

import java.math.BigInteger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.sql.*;
public class agrawal {

	public static void W_agrawal(BigInteger K, BigInteger y, BigInteger e2, BigInteger v, String tablename){
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/t";

		//  Database credentials
		final String USER = "root";
		final String PASS = "";

		//read primary key and concatenate it with the secret key k;
	    //BigInteger result = 
		int ex=0,w=0,pk2=0;long n;
		BigInteger concat, G,Ai,pk,iremider, Grem, mod2= new BigInteger("2");
		String stratt="";
		int intatt=0;
		double benchtime[];
		benchtime=new double[10];
		for(int l=0;l<10;l++){
			try{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			// Register JDBC driver
			   Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			   System.out.println("Connecting to database...");
			   conn= DriverManager.getConnection(DB_URL,USER,PASS);
			// Set auto-commit to false
			   conn.setAutoCommit(false);
			   
			Statement stmt=conn.createStatement();
			Statement stmt2=conn.createStatement();  

			ResultSet rs=stmt.executeQuery("select * from "+tablename);  
			long walclock=System.nanoTime();
			/*long startUserTimeNano=time_benchmarking.getCpuTime();
			long startSystem=time_benchmarking.getSystemTime();
			long startUser=time_benchmarking.getUserTime();*/
			while(rs.next())  {
				pk=new BigInteger(String.valueOf(rs.getInt(1)));
				//pk2=pk.intValue();
				System.out.println("primarykey= "+pk);
				
				//pk2=pk.intValue();
				concat= new BigInteger((String.valueOf(pk).concat(K.toString())));	//concatenation process of primary key and 1st secret key k1
					//seed G with primary key
				//G=new BigInteger(sha256.sha256(concat.toString()),16);
				

				Random random = new Random();
				G = new BigInteger(concat.bitLength(), random);
				//using paillier hashing function
				//G=(K.modPow(pk, Paillier.nsquare));
				//System.out.println("generate next G= " + G);
				 Grem =(G.mod(y));
				 //System.out.println("generate reminder= " + Grem);
				if(Grem.equals(BigInteger.ZERO))	{
					//mark the tuple
					BigInteger i= G.remainder(v) ; //mark attribut Ai
					//System.out.println("The value of i="+i);
					BigInteger j=G.remainder(e2);// mark jth bit					
					 iremider=(i.mod(mod2));
					 
			 			//mark jth bit as 0
					if( iremider.equals(BigInteger.ZERO)){
						stratt="A";
						intatt=2;
						Ai=new BigInteger(String.valueOf(selecttuple.selecttuple(pk.toString(),stratt,intatt,tablename))); // return the value of attribute Ai that has been determined by g.reminder(v)
						//System.out.println("Ai= "+Ai);
						if (Ai.testBit(j.intValue())==false){
			 				//System.out.print("return Ai when j=0");
			 				w++;ex++;
			 			// Create one more SQL statement
			 				//String SQL = "UPDATE "+tablename+" SET "+stratt+" ="+ Ai.intValue()+ " WHERE "+pk+"="+pk.intValue();
			 				//stmt2.addBatch(SQL);

			 			
			 				tupleupdate.tupleupdate1(Ai.intValue(),pk.intValue(),stratt,tablename);
			 				//System.out.println("updated Ai when j=0= "+Ai);
			 				}
			 			else{
			 				//System.out.print("return Ai when j=0 and fliped to 1");
			 				w++;ex++;
			 				Ai=Ai.setBit(j.intValue());
			 			// Create one more SQL statement
			 				//String SQL = "UPDATE "+tablename+" SET "+stratt+" ="+ Ai.intValue()+ " WHERE "+pk+"="+pk.intValue();
			 				//stmt2.addBatch(SQL);

				 			   // Add above SQL statement in the batch.
			 				tupleupdate.tupleupdate1(Ai.intValue(),pk.intValue(),stratt,tablename);
			 				//System.out.println("updated Ai when j=0 and fliped to 1= "+Ai);
			 			}
			 		}
			 		else{
			 			stratt="AP";
			 			intatt=43;
			 			Ai=new BigInteger(String.valueOf(selecttuple.selecttuple(pk.toString(),stratt,intatt,tablename))); // return the value of attribute Ai that has been determined by g.reminder(v) // return the value of attribute Ai that has been determined by g.reminder(v)
			 			//System.out.println("Ai= "+Ai);
			 			//mark jth bit as 1
			 			if (Ai.testBit(j.intValue())==true){
			 				w++;ex++;
			 				//System.out.print("return Ai when j=1");
			 				//String SQL = "UPDATE "+tablename+" SET "+stratt+" ="+ Ai.intValue()+ " WHERE "+pk+"="+pk.intValue();
			 				//stmt2.addBatch(SQL);

			 				tupleupdate.tupleupdate1(Ai.intValue(),pk.intValue(),stratt,tablename);
			 				//System.out.println("updated Ai when j=1= "+Ai);
			 				}
			 			else{
			 				//System.out.print("return Ai when j=1 and flipped to 0");
			 				w++;ex++;
			 				Ai=Ai.clearBit(j.intValue());
			 				//String SQL = "UPDATE "+tablename+" SET "+stratt+" ="+ Ai.intValue()+ " WHERE "+pk+"="+pk.intValue();
			 				//stmt2.addBatch(SQL);

			 				tupleupdate.tupleupdate1(Ai.intValue(),pk.intValue(),stratt,tablename);
			 				//System.out.println("updated Ai when j=1 and flipped to 0= "+Ai);
			 			}
			 		
	 		}
		}
			if (ex>=10)
				System.out.println("ex>=10 then execute the batch "+w);
					stmt2.executeBatch();
					ex=0;
			}
			// Create an int[] to hold returned values
	 		
			//Explicitly commit statements to apply changes
			   
			System.out.println("# of water mark bits= "+w);
			long duration    = System.nanoTime() - walclock;
			//long taskcpu    = time_benchmarking.getCpuTime() - startUserTimeNano;
			//long startSystem2    = time_benchmarking.getSystemTime() - startSystem;
			//long startuser2    = time_benchmarking.getUserTime() - startUser;
			//long taskSystemTimeNano  = getSystemTime( ) - startSystemTimeNano;
			//System.out.println(" total time in seconds taskcpu = "+(taskcpu)/1000000000.0);
			//System.out.println(" total time in seconds user= "+(startuser2)/1000000000.0);
			//System.out.println(" total time in seconds system= "+(startSystem2)/1000000000.0);
			
			benchtime[l]=duration/1000000000.0;
			long tt=System.nanoTime();
			int[] count = stmt2.executeBatch();
			conn.commit();
			//System.out.println("time for batching"+(System.nanoTime()-tt)/1000000000.0);
			//System.out.println(" total time in seconds wallclock inside= "+(duration)/1000000000.0);
			conn.close();
		} catch(Exception e){ System.out.println(e);} 
			
		}
				double av=0.0;
				for(int i=0;i<10;i++){
					av=av+benchtime[i];
				}
				System.out.println(" Amount of average time to run the algorithm in seconds= "+av/10);	

}
}
