package hello;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class paillier_and_sharmin {
	//pailer
	 /**
	    * p and q are two large primes. 
	    * lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1).
	    */
	    private BigInteger p, q, lambda;
	    /**
	    * n = p*q, where p and q are two large primes.
	    */
	    public BigInteger n;
	    /**
	    * nsquare = n*n
	    */
	    public BigInteger nsquare;
	    /**
	    * a random integer in Z*_{n^2} where gcd (L(g^lambda mod n^2), n) = 1.
	    */
	    private BigInteger g;
	    /**
	    * number of bits of modulus
	    */
	    private int bitLength;

	    /**
	    * Constructs an instance of the Paillier cryptosystem.
	    * @param bitLengthVal number of bits of modulus
	    * @param certainty The probability that the new BigInteger represents a prime number will exceed (1 - 2^(-certainty)). The execution time of this constructor is proportional to the value of this parameter.
	    */
	    public paillier_and_sharmin(int bitLengthVal, int certainty) {
	    KeyGeneration(bitLengthVal, certainty);
	    }

	    /**
	    * Constructs an instance of the Paillier cryptosystem with 512 bits of modulus and at least 1-2^(-64) certainty of primes generation.
	    */
	    public paillier_and_sharmin() {
	    KeyGeneration(512, 64);
	    }

	    /**
	    * Sets up the public key and private key.
	    * @param bitLengthVal number of bits of modulus.
	    * @param certainty The probability that the new BigInteger represents a prime number will exceed (1 - 2^(-certainty)). The execution time of this constructor is proportional to the value of this parameter.
	    */
	    public void KeyGeneration(int bitLengthVal, int certainty) {
	    bitLength = bitLengthVal;
	    Random rnd= ThreadLocalRandom.current();
	    /*Constructs two randomly generated positive BigIntegers that are probably prime, with the specified bitLength and certainty.*/
	    p = new BigInteger(bitLength / 2, certainty, rnd);
	    q = new BigInteger(bitLength / 2, certainty, rnd);

	    n = p.multiply(q);
	    nsquare = n.multiply(n);

	    g = new BigInteger("2");
	    lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(
	    p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
	    /* check whether g is good.*/
	    if (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
	    System.out.println("g is not good. Choose g again.");
	    System.exit(1);
	    }
	    }

	    /**
	    * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function explicitly requires random input r to help with encryption.
	    * @param m plaintext as a BigInteger
	    * @param r random plaintext to help with encryption
	    * @return ciphertext as a BigInteger
	    */

	    public BigInteger Encryption(BigInteger m, BigInteger r) {
	    return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);

	    }

	    /**
	    * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function automatically generates random input r (to help with encryption).
	    * @param m plaintext as a BigInteger
	    * @return ciphertext as a BigInteger
	    */
	    public BigInteger Encryption(BigInteger m) {
	    BigInteger r = new BigInteger(bitLength,  ThreadLocalRandom.current());
	    return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);

	    }

	    /**
	    * Decrypts ciphertext c. plaintext m = L(c^lambda mod n^2) * u mod n, where u = (L(g^lambda mod n^2))^(-1) mod n.
	    * @param c ciphertext as a BigInteger
	    * @return plaintext as a BigInteger
	    */
	    public BigInteger Decryption(BigInteger c) {
	    BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
	    return c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
	    }

	//shamir
    public static SecretShare[] split(final BigInteger secret, int needed, int available, BigInteger prime, Random random)
    {
        System.out.println("Prime Number: " + prime);

        final BigInteger[] coeff = new BigInteger[needed];
        coeff[0] = secret;
        for (int i = 1; i < needed; i++)
        {
            BigInteger r;
            while (true)
            {
                r = new BigInteger(prime.bitLength(), ThreadLocalRandom.current());
                if (r.compareTo(BigInteger.ZERO) > 0 && r.compareTo(prime) < 0)
                {
                    break;
                }
            }
            coeff[i] = r;
        }

        final SecretShare[] shares = new SecretShare[available];
        for (int x = 1; x <= available; x++)
        {
            BigInteger accum = secret;

            for (int exp = 1; exp < needed; exp++)
            {
                accum = accum.add(coeff[exp].multiply(BigInteger.valueOf(x).pow(exp).mod(prime))).mod(prime);
            }
            shares[x - 1] = new SecretShare(x, accum);
            System.out.println("Share " + shares[x - 1]);
        }

        return shares;
    }

    public static BigInteger combine(final SecretShare[] shares, final BigInteger prime)
    {
        BigInteger accum = BigInteger.ZERO;

        for(int formula = 0; formula < shares.length; formula++)
        {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for(int count = 0; count < shares.length; count++)
            {
                if(formula == count)
                    continue; // If not the same value

                int startposition = shares[formula].getNumber();
                int nextposition = shares[count].getNumber();

                numerator = numerator.multiply(BigInteger.valueOf(nextposition).negate()).mod(prime); // (numerator * -nextposition) % prime;
                denominator = denominator.multiply(BigInteger.valueOf(startposition - nextposition)).mod(prime); // (denominator * (startposition - nextposition)) % prime;
            }
            BigInteger value = shares[formula].getShare();
            BigInteger tmp = value.multiply(numerator) . multiply(modInverse(denominator, prime));
            accum = prime.add(accum).add(tmp) . mod(prime); //  (prime + accum + (value * numerator * modInverse(denominator))) % prime;
        }

        System.out.println("The secret is: " + accum + "\n");

        return accum;
    }

    private static BigInteger[] gcdD(BigInteger a, BigInteger b)
    { 
        if (b.compareTo(BigInteger.ZERO) == 0)
            return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO}; 
        else
        { 
            BigInteger n = a.divide(b);
            BigInteger c = a.mod(b);
            BigInteger[] r = gcdD(b, c); 
            return new BigInteger[] {r[0], r[2], r[1].subtract(r[2].multiply(n))};
        }
    }

    private static BigInteger modInverse(BigInteger k, BigInteger prime)
    { 
        k = k.mod(prime);
        BigInteger r = (k.compareTo(BigInteger.ZERO) == -1) ? (gcdD(prime, k.negate())[2]).negate() : gcdD(prime,k)[2];
        return prime.add(r).mod(prime);
    }

    public static void main(final String[] args)
    {
        final int CERTAINTY = 256;
        final Random random = ThreadLocalRandom.current();

        final BigInteger secret = new BigInteger("123");

        // prime number must be longer then secret number
        final BigInteger prime = new BigInteger(secret.bitLength() + 1, CERTAINTY, random);

        // 2 - at least 2 secret parts are needed to view secret
        // 5 - there are 5 persons that get secret parts
        final SecretShare[] shares = Shamir.split(secret, 2, 5, prime, random);


        // we can use any combination of 2 or more parts of secret
        SecretShare[] sharesToViewSecret = new SecretShare[] {shares[0],shares[1]}; // 0 & 1
        BigInteger result = Shamir.combine(sharesToViewSecret, prime);

        sharesToViewSecret = new SecretShare[] {shares[1],shares[4]}; // 1 & 4
        result = Shamir.combine(sharesToViewSecret, prime);

        sharesToViewSecret = new SecretShare[] {shares[0],shares[1],shares[3]}; // 0 & 1 & 3
        result = Shamir.combine(sharesToViewSecret, prime);
    

        //paillier
       /* instantiating an object of Paillier cryptosystem*/
    paillier_and_sharmin paillier = new paillier_and_sharmin();
    /* instantiating two plaintext msgs*/
    BigInteger m1 = new BigInteger("10");
    BigInteger m2 = new BigInteger("20");
    BigInteger m3 = new BigInteger("30");
    BigInteger m4 = new BigInteger("40");
    /* encryption*/
    BigInteger em1 = paillier.Encryption(m1);
    BigInteger em2 = paillier.Encryption(m2);
    BigInteger em3 = paillier.Encryption(m3);
    BigInteger em4 = paillier.Encryption(m4);

    /* printout encrypted text*/

    System.out.println("em1= "+em1);
    System.out.println("em2= "+em2);
    System.out.println("em3= "+em3);
    System.out.println("em4= "+em4);

    /* printout decrypted text */
    System.out.println(paillier.Decryption(em1).toString());
    System.out.println(paillier.Decryption(em2).toString());
    System.out.println(paillier.Decryption(em3).toString());
    System.out.println(paillier.Decryption(em4).toString());

    /* test homomorphic properties -> D(E(m1)*E(m2) mod n^2) = (m1 + m2) mod n */
    BigInteger product_em1em2 = em1.multiply(em2).mod(paillier.nsquare);
    BigInteger sum_m1m2 = m1.add(m2).mod(paillier.n);
    System.out.println("original sum: " + sum_m1m2.toString());
    System.out.println("decrypted sum: " + paillier.Decryption(product_em1em2).toString());

    BigInteger product_em3em4 = em3.multiply(em4).mod(paillier.nsquare);
    BigInteger sum_m3m4 = m3.add(m4).mod(paillier.n);
    System.out.println("original sum: " + sum_m3m4.toString());
    System.out.println("decrypted sum: " + paillier.Decryption(product_em3em4).toString());

    /* test homomorphic properties -> D(E(m1)^m2 mod n^2) = (m1*m2) mod n */
    BigInteger expo_em1m2 = em1.modPow(m2, paillier.nsquare);
    BigInteger prod_m1m2 = m1.multiply(m2).mod(paillier.n);
    System.out.println("original product: " + prod_m1m2.toString());
    System.out.println("decrypted product: " + paillier.Decryption(expo_em1m2).toString());

    BigInteger expo_em3m4 = em3.modPow(m4, paillier.nsquare);
    BigInteger prod_m3m4 = m3.multiply(m4).mod(paillier.n);
    System.out.println("original product: " + prod_m3m4.toString());
    System.out.println("decrypted product: " + paillier.Decryption(expo_em3m4).toString());

    }
   
}