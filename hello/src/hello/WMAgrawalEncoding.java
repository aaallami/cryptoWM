package hello;
import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class WMAgrawalEncoding {
	
	public static String tablename="15k";
	//public static int pk;
	public static int Ai;
	public static int next;
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("enter the secret key");
		BigInteger k= input.nextBigInteger();
		System.out.println("Enter the Freaction of tuples ");
		BigInteger y= input.nextBigInteger();
		System.out.println("Enter the jth bit to be marked");
		BigInteger e2= input.nextBigInteger();
		//System.out.println("Enter the number of attributes");
		BigInteger v= new BigInteger("43");
		//System.out.println("Enter the lenght of the watermark");
		int breaks=0;
		W_agrawal(k, y, e2, v,breaks);

	}
	
	public static void W_agrawal(BigInteger k, BigInteger y, BigInteger e2, BigInteger v, int breaks){
		//read primary key and concatenate it with the secret key k;
		
	    //BigInteger result = 
		int w=0;int pk=0; long n;
		BigInteger concat, G,result,Grem, hash;
		String stratt="";
		int intatt=0;
		double benchtime[];
		benchtime=new double[10];
		for(int l=0;l<1;l++){
			try{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/t","root","");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from "+tablename);  
			//long startSystemTimeNano=0;

			long startUserTimeNano=getCpuTime( );
			long startTime=System.nanoTime();
			while(rs.next())  {
				
				pk=rs.getInt(1);
				System.out.println("primarykey= "+pk);
				concat= new BigInteger((String.valueOf(pk) + k.toString()));	//concatenation process of primary key and 1st secret key k1
					//seed G with primary key
				
				//n=(rand.nextInt(concat.intValue()));
				//G = new BigInteger(String.valueOf(n));//randomGenerator(concat);
				
				Random random = new Random(concat.intValue());
				G = new BigInteger(concat.bitLength(), random);				
				System.out.println("generat next RGN= " + G);
				//BigInteger Ai=new BigInteger("0");
				 //Grem = G.remainder(y);
				//hash=hex2Decimal(getMD5(G.toString()));
				Grem=G.remainder(y);
				if( Grem.equals(BigInteger.ZERO))
				{
					//mark the tuple
					BigInteger i= G.remainder(v) ; //mark attribut Ai
					//System.out.println("The value of i="+i);
					BigInteger j=G.remainder(e2);// mark jth bit
			 		//System.out.println("the jth bit to be marked="+j);
					
					BigInteger iremider=i.remainder(new BigInteger("2"));
			 		//System.out.println("iremider= "+iremider);
		
			 			//mark jth bit as 0
					if( iremider.equals(BigInteger.ZERO)){
						stratt="A";
						intatt=2;
						Ai=selecttuple(""+pk,stratt,intatt); // return the value of attribute Ai that has been determined by g.reminder(v)
						//System.out.println("Ai= "+Ai);
						if (getBit(Ai, j.intValue())==0){
			 				//System.out.print("return Ai when j=0");
			 				w++;
			 				tupleupdate(Ai,pk,stratt);
			 				//System.out.println("updated Ai when j=0= "+Ai);
			 				}
			 			else{
			 				//System.out.print("return Ai when j=0 and fliped to 1");
			 				w++;
			 				Ai=turnOffjthbit(Ai,j.intValue());
			 				tupleupdate(Ai,pk,stratt);
			 				//System.out.println("updated Ai when j=0 and fliped to 1= "+Ai);
			 			}
			 		}
			 		else{
			 			stratt="AP";
			 			intatt=43;
			 			Ai=selecttuple(""+pk,stratt,intatt); // return the value of attribute Ai that has been determined by g.reminder(v)
			 			//System.out.println("Ai= "+Ai);
			 			//mark jth bit as 1
			 			if (getBit(Ai, j.intValue())==1){
			 				w++;
			 				//System.out.print("return Ai when j=1");
			 				tupleupdate(Ai,pk,stratt);
			 				//System.out.println("updated Ai when j=1= "+Ai);
			 				}
			 			else{
			 				//System.out.print("return Ai when j=1 and flipped to 0");
			 				w++;
			 				Ai=turnOffjthbit(Ai,j.intValue());
			 				tupleupdate(Ai,pk,stratt);
			 				//System.out.println("updated Ai when j=1 and flipped to 0= "+Ai);
			 			}
	 		}
		}
	 		
			}con.close();
			
			System.out.println("# of water mark bits= "+w);
			long duration = System.nanoTime() - startTime;
			long taskUserTimeNano    = getCpuTime( ) - startUserTimeNano;
			//long taskSystemTimeNano  = getSystemTime( ) - startSystemTimeNano;
			System.out.println(" total time in seconds= "+(taskUserTimeNano)/1000000000.0);
			benchtime[l]=taskUserTimeNano/1000000000.0;
			System.out.println("Wall clock time= "+duration/1000000000.0);
		
			
		} catch(Exception e){ System.out.println(e);} 
			
		}
		//end for loop
				double av=0.0;
				for(int i=0;i<10;i++){
					av=av+benchtime[i];
				}
				System.out.println(" Amount of average time to run the algorithm in seconds= "+av/10);	

}
		
	// turnoffk is a function to flip kth bit in n integer number.
		public static int turnOffK(int n, int k)
		{
		    if (k <= 0) return n;
		    return (n ^ (1 << (k - 1)));
		}
	
			// Random generator;
	public static BigInteger randomGenerator(BigInteger seed) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(seed.intValue(), rand);
	    return (result) ;
	   	}
	

	public static int turnOffjthbit(int n, int i)
	{
	    // k must be greater than 0
	    if (i <= 0) return n;
	 
	    // Do & of n with a number with all set bits except
	    // the k'th bit
	    return (n ^ (1 << (i - 1)));
	}
	public static int getBit(int n, int k) {
		int jthbit=((n >> k) & 1);
		return (jthbit);
		//System.out.print("bit="+((n >> k) & 1)+"\n");
		
	}
	
	//---------------------------------------------------------------------------------------------------------	
		//get the # of tuples in DB
	//---------------------------------------------------------------------------------------------------------	
		public static int getRowNumber(){
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
		//---------------------------------------------------------------------------------------------------------	
			//update a tuples in DB
		//---------------------------------------------------------------------------------------------------------	
		public static void tupleupdate(int set, int where, String att){
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
		
	//-----------------------------------------------------------------------------------------------------------------
		// get a specific value of an attribute of specific a record
	//-------------------------------------------------------------------------------------------------------------
	public static int selecttuple(String pk, String stratt, int intatt){
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
	

}
	