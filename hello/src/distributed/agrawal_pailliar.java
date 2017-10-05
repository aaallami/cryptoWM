package distributed;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class agrawal_pailliar {
//public static String tablename="5k";
//public static int pk;
public static int Ai;
public static int next;
public static void main(String[] str) {
	for(int T=30;T<=30;T=T+5){
	String tablename=T+"k";
	
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		//System.out.println("enter the secret key");
		//BigInteger k= input.nextBigInteger();
		//System.out.println("Enter the Freaction of tuples ");
		//BigInteger y= input.nextBigInteger();
		//System.out.println("Enter the jth bit to be marked");
		//BigInteger e2= input.nextBigInteger();
		//System.out.println("Enter the number of attributes");
		BigInteger v= new BigInteger("43");
		//System.out.println("Enter the lenght of the watermark");
		int breaks=0;
		 

	
	
/* instantiating an object of Paillier cryptosystem*/
Paillier paillier = new Paillier();
/* instantiating two plaintext msgs*/
BigInteger k1 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger K2 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger K3 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger K4 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
/* encryption*/
BigInteger r1 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger Epk1 = paillier.Encryption(k1,r1);
BigInteger r2 = new BigInteger(Paillier.getbitL(),ThreadLocalRandom.current() );
BigInteger Epk2 = paillier.Encryption(K2,r2);
BigInteger r3 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger Epk3 = paillier.Encryption(K3,r3);
BigInteger r4 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger Epk4 = paillier.Encryption(K4,r4);
BigInteger R = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
//Pi combines the received encrypted share
BigInteger K= (Epk1.multiply(Epk2).multiply(Epk3).multiply(Epk4)).mod(paillier.nsquare);
BigInteger EPKMAIN=paillier.Encryption(K,R);
// for t-paillier generate a1 and a2 randomly and R-stare
BigInteger a1 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger a2 = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
BigInteger R_Stare = new BigInteger(Paillier.getbitL(), ThreadLocalRandom.current());
//Encrypt a1 and a2
BigInteger Epk_a1 = paillier.Encryption(K.add(a1),R_Stare);
BigInteger Epk_R_Stare = paillier.Encryption(R.add(a2),R_Stare);
BigInteger Dpk_a1 = paillier.Decryption(Epk_a1);
BigInteger Dpk_a2 = paillier.Decryption(Epk_R_Stare);

//n-sahres generation of K+a1
final int CERTAINTY = 256;
final BigInteger prime1 = new BigInteger(Dpk_a1.bitLength() + 1, CERTAINTY, ThreadLocalRandom.current());
final SecretShare[] shares1 = Shamir.split(Dpk_a1, 2, 4, prime1, ThreadLocalRandom.current());
// we can use any combination of 2 or more parts of secret
SecretShare[] sharesToViewSecret = new SecretShare[] {shares1[0],shares1[1]}; // 0 & 1
BigInteger result1 = Shamir.combine(sharesToViewSecret, prime1);
//------------------------
//n-sahres generation of R+a2

final BigInteger prime2 = new BigInteger(Dpk_a2.bitLength() + 1, CERTAINTY, ThreadLocalRandom.current());
final SecretShare[] shares2 = Shamir.split(Dpk_a2, 2, 4, prime2, ThreadLocalRandom.current());
//we can use any combination of 2 or more parts of secret
SecretShare[] sharesToViewSecret2 = new SecretShare[] {shares2[0],shares2[1]}; // 0 & 1
BigInteger result2 = Shamir.combine(sharesToViewSecret2, prime2);
//------------------------

//System.out.println("compined ki by multipliaction and get K= "+ K);
//long t1=System.nanoTime();
//BigInteger Seckey = new BigInteger("115065887865727276393100408401497917356200");
System.out.println("The secrit key = "+ K);
System.out.println("Table="+ T+"K");
WMCATEGSION.Sion(K,K, tablename);
//agrawal.W_agrawal(Seckey, y, e2, v, tablename);
//System.out.println("time of wall clock of the whole Agraawal call= "+ (System.nanoTime()-t1)/1000000000.0);
}
	}
}
	




















/* printout encrypted text*/
/*System.out.println("r= "+r);
System.out.println("em1= "+em1);
System.out.println("--------------------------------------------------");
System.out.println("r= "+r2);
System.out.println("em2= "+em2);
System.out.println("--------------------------------------------------");
System.out.println("r= "+r3);
System.out.println("em3= "+em3);
System.out.println("--------------------------------------------------");
System.out.println("r= "+r4);
System.out.println("em4= "+em4);
System.out.println("--------------------------------------------------");

/* printout decrypted text */
//System.out.println(paillier.Decryption(em1).toString());
//System.out.println(paillier.Decryption(em2).toString());
//System.out.println(paillier.Decryption(em3).toString());
//System.out.println(paillier.Decryption(em4).toString());
//System.out.println("--------------------------------------------------");
//BigInteger add= m1.add(m2);
//BigInteger rprim= (r.multiply(r2)).mod(n);

//BigInteger emoriginal = paillier.Encryption(add,rprim);
//System.out.println("emoriginal="+emoriginal);
//System.out.println("--------------------------------------------------");*/


 