package hello;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class testing {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Integer obj1 = 114;
		String obj2 = "1010";
		System.out.println("getting number of bits "+ logg2(1507454));
		//long i =  (int) ((Math.log(1507454))/Math.log(2) +1);
		//System.out.println("i="+i);
		
		System.out.println("////////////////////////////////////////////////////////////////");
		BigInteger r = new BigInteger("145019531716763988792417567944786997130832351888");
	    BigInteger f = r.shiftRight(r.bitLength() - 14);
	    System.out.println(""+ f.testBit(3));
		System.out.println("return the msb of biginteger= " +f);

		System.out.println();
		System.out.println();
		System.out.print("converting 1600 to binary= ");
		convertDeciamlToBinary(1265912181,14);
		System.out.println();
		System.out.println("hashCode for an integer is using hashcode= " + obj1.hashCode());
		System.out.println("hashCode for a string is using hashcode= " + obj2.hashCode());
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		String sha1_ad1 = AeSimpleSHA1.SHA1("1010");                
		//String sha1_ad2 = AeSimpleSHA1.SHA1("1");
		System.out.println("1010 using SHA1= "+sha1_ad1);
		System.out.println( "1010 using SHA1 with converting to string= "+sha1_ad1.toString());
		//System.out.println(sha1_ad2);
		System.out.println("1010 using MD5="+getMD5("1010"));
			    
			    System.out.println("converting to decimal="+ hex2Decimal(getMD5("1010")));
			    BigInteger strhas= hex2Decimal(getMD5("1010"));
			    System.out.println("**********************");
			    System.out.println("number of bits in biginteger="+ strhas.bitLength());
			    
			    
			    
			    System.out.println();
			    BigInteger b= new BigInteger("36");
			    BigInteger result2 = strhas.remainder(b);
			    System.out.println("reminder operation= "+result2);
			    System.out.println("modulo operation= "+ (!strhas.mod(new BigInteger("36")).equals(BigInteger.ZERO)));
			    			    
			    
			    
			    BigInteger result = hex2Decimal("1966e694bad90686516f99cdf432800fdca39290");
			    System.out.println("reminder= "+ result.remainder(result));
			    System.out.println("bigint converting to decimal="+ (hex2Decimal("1966e694bad90686516f99cdf432800fdca39290")));

			    /*System.out.println("converting to paied value="+ hex2Decimal("b43914cc9980ff65d3f54031d0f908e72"));
			    System.out.println("converting to decimal using the variavle ="+ hex2Decimal(sha1_ad1));
			    System.out.println("converting to hex="+ decimal2Hex(hex2Decimal(sha1_ad1)));*/
			    
			  }
			

    /*public static long hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        long val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }*/
	
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
	
	
    
    public static String decimal2Hex(long d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = (int) (d % 16);                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
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
    public static int logg2(int value) {
        return Integer.SIZE-Integer.numberOfLeadingZeros(value);
    }
    
    public static void convertDeciamlToBinary(long num, int index2){
    	 
    	  int binary[] = new int[40];
    	         int index = 0;
    	 while(num > 0){
    	           binary[index++] = (int) (num%2);
    	           num = num/2;
    	 }

    	for(int i = index-1;i >=(index-index2);i--){
    	           System.out.print(binary[i]);
    	}
    	 
    	}
    
    /*public static int MSBLog(BigInteger i)
    {
        int bit;
        if (i.equals(0))
            bit = -1;
        else
            bit = (int)BigInteger.Log(i, 2.0);

        return bit;
    }*/
    
	}


