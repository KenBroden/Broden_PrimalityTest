import java.math.BigInteger;
import java.security.SecureRandom;

public class MillerRabin {
    
    private static final BigInteger THREE = new BigInteger("3");

    public String primality(BigInteger p, int s) {

        // If p is 2 or 3, then it is likely prime
        if (p.equals(BigInteger.TWO) || p.equals(THREE)) {
            return "p is likely prime";
        }

        // if p is less than 2 or even, then it is composite
        if (p.compareTo(BigInteger.TWO) < 0 || p.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return "p is composite";
        }
        
        // p - 1 as (2^u) * r
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        int u = pMinusOne.getLowestSetBit();
        BigInteger r = pMinusOne.shiftRight(u);

        // random secure number s times
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < s; i++) {

            // random number a between 2 and p - 2
            BigInteger a = new BigInteger(p.bitLength() - 1, random).add(BigInteger.TWO);
            BigInteger z = a.modPow(r, p); // modPow is used to calculate (a^r) mod p

            // if z is 1 or p - 1, then p is likely prime
            if (!z.equals(BigInteger.ONE) && !z.equals(pMinusOne)) {
                boolean composite = true;
                for (int j = 1; j < u; j++) {
                    z = z.modPow(BigInteger.TWO, p); // modPow is used to calculate (z^2) mod p
                    if (z.equals(BigInteger.ONE)) {
                        return "p is composite";
                    }

                    if (z.equals(pMinusOne)) {
                        composite = false;
                        break;
                    }
                }
                if (composite) {
                    return "p is composite";
                }
            }
        }

        return "p is likely prime";
    }
}
