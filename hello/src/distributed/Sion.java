package distributed;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Sion {
	public static void sion (BigInteger K, BigInteger y, String tablename) {
		int pk;
		long startTime=0; //benchmarking time
		long startSystemTimeNano=0;
		long startUserTimeNano=0;
		
		Scanner inputint = new Scanner(System.in);
		//System.out.println("Enter the first secret key");
		BigInteger k1= new BigInteger("115065887865727276393100408401497917356200");	// enter the first secret key
		//System.out.println("Enter the second secret key");
		BigInteger k2=new BigInteger("115065887865727276393100408401497917356200");	// enter the second secret key
		System.out.println("Enter the percentage of considered tupls to be encoded secret key");
for(int e1=250;e1<=750;e1=e1+250){ 
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
				
				pk=rs.getInt(1);
				//System.out.println("primarykey= "+pk);
				//System.out.println(rs.getString(3));	// get the class of worker attribute from the table
				concat= new BigInteger((String.valueOf(pk) + k1.toString()));	//concatenation process of primary key and 1st secret key k1
				//System.out.println("concatenation pk and k1= "+concat);
				
				//System.out.println("concatenation pk and k2= "+concat2);
				hash1=new BigInteger(sha256.sha256(concat.toString()), 16);
				 //hash1=new BigInteger(getMD5(concat.toString()), 16);		//hashing pk and k1\
				//System.out.println("hash1= "+hash1);
				 
				//System.out.println("hash2= "+hash2);
				 hash1rem = hash1.remainder(e2);
				//System.out.println("hash1 reminder after module of e2= "+hash1rem);
				//System.out.println("---------------------------------------------------------------------------------");

				
				if( hash1rem.equals(BigInteger.ZERO))
					{
					concat2= new BigInteger((String.valueOf(pk) + k2.toString()));	//concatenation process of primary key and k1
					hash2=new BigInteger(sha256.sha256(concat2.toString()), 16);	//hashing pk and k2
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
					tupleupdate(selecttuple(""+t2, tablename),pk,tablename);
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


	
}
