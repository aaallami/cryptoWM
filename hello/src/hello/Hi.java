package hello;
import java.util.Scanner;
import java.util.Random;

public class Hi {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		
		Scanner input = new Scanner(System.in);
		System.out.println("enter the secret key");
		int k= input.nextInt();
		System.out.println("the secret key "+ k);
		System.out.println("Enter the Freaction of tuples ");
		int y= input.nextInt();
		System.out.println("Enter the jth bit to be marked");
		int e= input.nextInt();
		System.out.println("Enter the number of attributes");
		int v= input.nextInt();
		System.out.println("Call the fuction");
			System.out.println("watermarked value="+W_agrawal(k, y, e, v));
		System.out.println("Call the fuction");
			System.out.println("watermarked value="+W_agrawal(k, y, e, v));
		System.out.println("Call the fuction");
			System.out.println("watermarked value="+W_agrawal(k, y, e, v));
				
		
		
		//System.out.println("enter the primary key ");
		//int pk= input.nextInt();
		//System.out.println("the primary key "+ pk);
		//int next = Integer.valueOf(String.valueOf(k) + String.valueOf(pk));
		//System.out.println("concatenation pk+k "+ next);
		//int G= randomGenerator(next);
		//System.out.println("next SRN "+ G);
		
		// TODO Auto-generated method stub
		//System.out.println("hi");
		//System.out.println();
		//System.out.println(turnOffK(5,4));
		//System.out.println(15<<1);
		//System.out.println(0.3173339220961546 % 1000);
		//System.out.println(randomGenerator(concatenation(1,1)));
		//Random rnd1 = new Random(1);
		//System.out.println(rnd1.nextInt(10));
		//Integer obj1 = new Integer(2009);
		//String obj2 = new String("2009");
		//System.out.println("hashCode for an integer is " + obj1.hashCode());
		//System.out.println("hashCode for a string is " + obj2.hashCode());
		  /*for(int i =10; i<50; i=i+10){
			  //Random generator = new Random(i+100);
			  Random generator = new Random(i);
			int randomInteger = generator.nextInt(1000000);
			if (randomInteger<0) {
			System.out.println("less than 0");}
		      System.out.println("Random Integer in Java: " + (randomInteger));
		      if ((randomInteger*-1) % 2==0){
		    	  System.out.println(randomInteger % 5);
		    	  System.out.println("MARKTHIS TUBLE: " );
		      }
		 }*/

		//Read more: http://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-inimport java.util.Scanner;-java-between-range.html#ixzz4a8TxF7WY

	}
	// turnoffk is a function to flip kth bit in n integer number.
	public static int turnOffK(int n, int k)
	{
	    // k must be greater than 0
	    if (k <= 0) return n;
	 
	    // Do & of n with a number with all set bits except
	    // the k'th bit
	    return (n ^ (1 << (k - 1)));
	}
	public static int W_agrawal(int k, int y, int e, int v){
		//read primary key and concatenate it with the secret key k;
		@SuppressWarnings("resource")
		Scanner input1= new Scanner(System.in);
		System.out.println("enter the primary key ");
		int pk= input1.nextInt();
		//seed G with primary key
		int next= Integer.valueOf(String.valueOf(k) + String.valueOf(pk));
		System.out.println("concatenation= "+next);
		int G = randomGenerator(next);
		System.out.println("generat next RGN "+ G);
		int Ai=0;
		if (G % y ==0) {
			//mark the tuple
			int i= G % v; //mark attribut Ai
			System.out.println("The value of i="+i);
			int j=G % e;// mark jth bit
	 		System.out.println("the jth bit to be marked="+j);
	 		System.out.println("Enter the Ai attribute");
			Ai=input1.nextInt();
			if (i % 2==0){
	 			//mark jth bit as 0
	 			if (getBit(Ai, j)==0){
	 				System.out.print("return Ai when j=0");
	 				return(Ai);
	 				}
	 			else{
	 				System.out.print("return Ai when j=0 and fliped to 1");
	 				return((turnOffjthbit(Ai,j)));
	 			}
	 		}
	 		else{
	 			//mark jth bit as 1
	 			if (getBit(Ai, j)==1){
	 				System.out.print("return Ai when j=1");
	 				return(Ai);
	 				}
	 			else{
	 				System.out.print("return Ai when j=1 and flipped to 0");
	 				return((turnOffjthbit(Ai,j)));
	 			}
	 		}
		}
	 		return(0);
	 		//return the value of the marked attribute
}
	public static int randomGenerator(int seed) {
	    Random generator = new Random(seed);
	    int num = generator.nextInt() ;//* (0.5)
	    return (num*-1) ;
	   
	}
	public static int concatenation(int i, int j) {
		int k = Integer.valueOf(String.valueOf(i) + String.valueOf(j));
		System.out.println(k);
		return k;
}
	public static int turnOffjthbit(int n, int k)
	{
	    // k must be greater than 0
	    if (k <= 0) return n;
	 
	    // Do & of n with a number with all set bits except
	    // the k'th bit
	    return (n ^ (1 << (k - 1)));
	}
	public static int getBit(int n, int k) {
		int jthbit=((n >> k) & 1);
		return (jthbit);
		//System.out.print("bit="+((n >> k) & 1)+"\n");
		
	}
}