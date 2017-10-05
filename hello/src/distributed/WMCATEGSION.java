package distributed;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import distributed.sha256;

import java.lang.management.*;
public class WMCATEGSION {
	//public static String tablename="35k";
	
	public static void Sion (BigInteger K1, BigInteger K2, String tablename) {
		BigInteger pk;
		long startTime=0; //benchmarking time
		long startSystemTimeNano=0;
		long startUserTimeNano=0;
		
		Scanner inputint = new Scanner(System.in);
		//System.out.println("Enter the first secret key");
		BigInteger k1= new BigInteger("115065887865727276393100408401497917356200");	// enter the first secret key
		//System.out.println("Enter the second secret key");
		BigInteger k2=new BigInteger("115065887865727276393100408401497917356200");	// enter the second secret key
		System.out.println("Enter the percentage of considered tupls to be encoded secret key");
for(int e1=750;e1<=750;e1=e1+250){ 
		//long e1= inputint.nextInt();
		System.out.println("e1= "+e1);
		int[] wm,wm0 ;
		wm0=new int[(int) e1];
		wm=new int[(int) e1];
		int[] wm2= {1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0,
					1,1,0,1,0,1,0,1,0,0};
		
		int wm_data;
		double benchtime[];
		benchtime=new double[10];
		boolean boolean1;
		BigInteger t;
		BigInteger concat, concat2, hash1, hash2, hash1rem, msb1, msb2;
		int N,t2=0,w=0;
		N= getRowNumber(tablename);	// get the number of tuples in the database
		//System.out.println("Enter the lenght of the watermark");
		//long breaks=inputint.nextInt();
		System.out.println("N= "+N);
		BigInteger e2= new BigInteger(String.valueOf(e1));
		System.out.println("e2= "+e2);
		System.out.println("e2 bits= "+e2.bitLength());
		BigInteger bigna=new BigInteger(String.valueOf(N));//number of bits of number of instances of attribute a (na)
		System.out.println("N bits= "+bigna.bitLength());
		
		//Ne=bigna.bitLength();
	    //BigInteger bigNe=new BigInteger(String.valueOf(Ne));//number of bits of number of instances of attribute a (na)
		//-------------------------------------------------------------------------------
		//connecting to the database and plugging the water mark;
		//-------------------------------------------------------------------------------
		for(int i=0;i<10;i++){ 
			//System.out.println("time to rundom"+(e1));
			int c=0;
			
			while(c<(e1)){
				for(int j=0; j<10; j++){
					wm0[c]=wm2[j];
					c++;
				}
			}
			//System.out.println("wm0[i]");
			for(int k=0;k<wm0.length;k++){
				//System.out.print(wm0[k]);
			}
			wm=distributed.huffman.generateCode(wm0);
			//System.out.println("wm[i]");
			for(int k=0;k<wm.length;k++){
				//System.out.print(wm[k]);
			}
			System.out.println();
		try{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from "+tablename);  
			startTime = System.nanoTime();
			 //startSystemTimeNano = getSystemTime( );
			 
			
				startUserTimeNano   = getCpuTime( );
				long walclock=System.nanoTime();
			while(rs.next())  {
				//System.out.println(rs.getString(3));
				pk=new BigInteger(String.valueOf(rs.getInt(1)));
				//pk=rs.getInt(1);
				//System.out.println("primarykey= "+pk);
				//System.out.println(rs.getString(3));	// get the class of worker attribute from the table
				concat= new BigInteger((String.valueOf(pk) + k1.toString()));	//concatenation process of primary key and 1st secret key k1
				//hash1=new BigInteger(sha256.sha256(concat.toString()), 16);
				hash1=(K1.modPow(pk, Paillier.nsquare));
				hash1rem = hash1.remainder(e2);
				//System.out.println("hash1 reminder after module of e2= "+hash1rem);
				//System.out.println("---------------------------------------------------------------------------------");

				
				if( hash1rem.equals(BigInteger.ZERO))
					{
					//concat2= new BigInteger((String.valueOf(pk) + k2.toString()));	//concatenation process of primary key and k1
					//hash2=new BigInteger(sha256.sha256(concat2.toString()), 16);	//hashing pk and k2
					hash2=(K2.modPow(pk, Paillier.nsquare));
					 msb1 = hash1.shiftRight(hash1.bitLength() - bigna.bitLength()+1);//get the MSB of H(pk,k1)
					 msb2 = hash2.shiftRight(hash2.bitLength() - e2.bitLength()+1);//get the MSB of H(pk,k1)
					//System.out.println("watermarkbit="+wm[msb2.intValue()]);
					//System.out.println("watermarkbitindex="+msb2.intValue());
					 boolean1=msb1.testBit(0);
					 wm_data=wm[msb2.intValue()];
							if ( (boolean1==false) && (wm_data==1))
								{
									t=msb1.flipBit(0);
									t2=t.intValue();
								}
							else if ((boolean1==true) && (wm_data==0))
								{
									t=msb1.flipBit(0);
									t2=t.intValue();
								}
							else
							{
								t2=msb1.intValue();
							}
					//System.out.println("t2= "+t2);
					//System.out.println(selecttuple(""+t2));
					tupleupdate(selecttuple(""+t2, tablename),pk.intValue(),tablename);
					w++;	//counter of water mark length
					//if (w>=breaks)
					//		break;
					//System.out.println("pk= "+rs.getInt(1)+"   classworker   =   "+rs.getInt(3));;
					//System.out.println("------------------------------------------------------------------------------------------------");
					}
				//System.out.println("no work have been done");
				//System.out.println("------------------------------------------------------------------------------------------------");
				
			}con.close();
			System.out.println("# of water mark bits= "+w);
			long duration    = System.nanoTime() - walclock;
			//long duration = System.nanoTime() - startTime;
			//long taskUserTimeNano    = getCpuTime( ) - startUserTimeNano;
			//long taskSystemTimeNano  = getSystemTime( ) - startSystemTimeNano;
			//System.out.println(" total time in seconds= "+(taskUserTimeNano)/1000000000.0);
			benchtime[i]=duration/1000000000.0;
			//double seconds1= (double)duration/1000000000.0;
			//System.out.println(" Amount of time to run the algorithm in seconds1= "+seconds1);	
			
			} catch(Exception e){ System.out.println(e);}  
		}//end for loop
		double av=0.0;
		for(int i=0;i<10;i++){
			av=av+benchtime[i];
		}
		System.out.println(" Amount of average time to run the algorithm in seconds= "+av/10);	
		
		//System.out.println(" taskUserTimeNano= "+taskUserTimeNano);
		//System.out.println(" taskSystemTimeNano= "+taskSystemTimeNano);
		//System.out.println(" total summation time in seconds= "+(taskSystemTimeNano+taskUserTimeNano));
	
		//System.out.println(" Amount of time to run the algorithm in nanoseconds= "+duration);

	}
	}
	
// huffman error code generation 	
	
	//------------------------------------------------
	
			
//-----------------------------------------------------------------------------------------------------
// hashing function using MD5
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
//---------------------------------------------------------------------------------------------------------
	
//---------------------------------------------------------------------------------------------------------	
// converting Hex 
	public static BigInteger hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        BigInteger val = BigInteger.ZERO;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = val.multiply(BigInteger.valueOf(16));
            val=val.add(BigInteger.valueOf(d));
        }
        //BigInteger b1 = new BigInteger(String.valueOf(val));
        return val;
    }	
//	
//---------------------------------------------------------------------------------------------------------	
	//Modulo operation
	public static boolean modulo(BigInteger val, String e) {
	    if(!val.mod(new BigInteger(e)).equals(BigInteger.ZERO))
	    	
	        return true;
	    return false;
	}
	
//---------------------------------------------------------------------------------------------------------	
	//get the # of tuples in DB
//---------------------------------------------------------------------------------------------------------	
	public static int getRowNumber(String tablename){

		   int numberRow = 0;
		   //Connection mysqlConn = DriverManager.getConnection(HOST, USER_ID, PASSWORD);

		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con2=DriverManager.getConnection(  
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
		return numberRow;
		}
	//---------------------------------------------------------------------------------------------------------	
		//update a tuples in DB
	//---------------------------------------------------------------------------------------------------------	
	public static void tupleupdate(String set, int where, String tablename){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con4=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			//Object mysqlConn;
		       	PreparedStatement st = con4.prepareStatement("UPDATE "+tablename+" SET C = ? " + " WHERE pk = ?");
		    	st.setString(1, set);
				st.setInt(2, where);//499 is the one that will be udated bu the new value 2 

				st.executeUpdate();

				//System.out.println("Record is updated to DBUSER table!");
				con4.close();
		}
		catch (Exception ex){
		    System.out.println(ex.getMessage());

		}	
	}
	
//-----------------------------------------------------------------------------------------------------------------
	// get a specific value of an attribute of specific a record
//-------------------------------------------------------------------------------------------------------------
public static String selecttuple(String pk, String tablename){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con3=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			Statement stmt=con3.createStatement();  
			ResultSet rs2=stmt.executeQuery("SELECT C FROM "+tablename+" WHERE pk ="+pk);
			Class.forName("com.mysql.jdbc.Driver");  
			
			while(rs2.next()) {    	
				return(rs2.getString(1));
				}con3.close();
			
		}catch (Exception ex){
		    System.out.println(ex.getMessage());

		}
		return "";
		}
//-----------------------------------------------------------------------------------------------------------------
	// get a specific value of an attribute of specific a record
//-------------------------------------------------------------------------------------------------------------

/** Get CPU time in nanoseconds. */
public static long getCpuTime( ) {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
    return bean.isCurrentThreadCpuTimeSupported( ) ?
        bean.getCurrentThreadCpuTime( ) : 0L;
}
 
/** Get user time in nanoseconds. */
public static long getUserTime( ) {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
    return bean.isCurrentThreadCpuTimeSupported( ) ?
        bean.getCurrentThreadUserTime( ) : 0L;
}

/** Get system time in nanoseconds. */
public static long getSystemTime( ) {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
    return bean.isCurrentThreadCpuTimeSupported( ) ?
        (bean.getCurrentThreadCpuTime( ) - bean.getCurrentThreadUserTime( )) : 0L;
}

}
	

