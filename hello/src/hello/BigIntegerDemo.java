package hello;

import java.math.*;

public class BigIntegerDemo {

   public static void main(String[] args) {
	   int[] wm = {1,1,0,1,0,1,0,1,0,0};
	   System.out.println( "array 1D="+ wm[0] );
      // create a BigInteger object
      BigInteger bi,bi2, bi3;

      // create 2 boolean objects
      Boolean b1, b2;

      bi = new BigInteger("8"); 

      // perform testbit on bi at index 2 and 3
      b1 = bi.testBit(2);
      b2 = bi.testBit(3);

      String str1 = "Test Bit on " + bi + " at index 2 returns " +b1;
      String str2 = "Test Bit on " + bi + " at index 3 returns " +b2;

      // print b1, b2 values
      System.out.println( str1 );
      System.out.println( str2 );
     //bi2=bi.setBit(3);
     //System.out.println( bi2 );
     bi3=bi.flipBit(3);
     System.out.println( bi3 );
   }
}