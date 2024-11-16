import java.math.BigInteger;
import java.security.SecureRandom;

public class MillerRabin {

    private static final BigInteger THREE = new BigInteger("3");

    public PrimalityResult primality(BigInteger p, int s) {
        if (p == null) {
            throw new IllegalArgumentException("Input number p cannot be null");
        }

        if (p.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("Input number p must be greater than zero");
        }

        if (s <= 0) {
            throw new IllegalArgumentException("Security parameter s must be greater than zero");
        }

        // If p is 2 or 3, then it is likely prime
        if (p.equals(BigInteger.TWO) || p.equals(THREE)) {
            return new PrimalityResult("p is likely prime", 0, 0);
        }

        // if p is less than 2 or even, then it is composite
        if (p.compareTo(BigInteger.TWO) < 0 || p.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return new PrimalityResult("p is composite", 0, 0);
        }

        // p - 1 as (2^u) * r
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        int u = pMinusOne.getLowestSetBit();
        BigInteger r = pMinusOne.shiftRight(u);

        // random secure number s times
        SecureRandom random = new SecureRandom();
        int candidateCounter = 0; // Counter for number of candidates a tried
        int bigIntegerOperations = 0; // Counter for number of BigInteger operations
        for (int i = 0; i < s; i++) {

            // random number a between 2 and p - 2
            BigInteger a;
            do {
                a = new BigInteger(p.bitLength(), random);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(p.subtract(BigInteger.TWO)) > 0);

            candidateCounter++; // Increment candidate counter for each candidate a tried

            BigInteger z = a.modPow(r, p); // modPow is used to calculate (a^r) mod p
            bigIntegerOperations++;

            // if z is 1 or p - 1, then p is likely prime
            if (!z.equals(BigInteger.ONE) && !z.equals(pMinusOne)) {
                boolean composite = true;
                for (int j = 1; j < u; j++) {
                    z = z.modPow(BigInteger.TWO, p); // modPow is used to calculate (z^2) mod p
                    bigIntegerOperations++;
                    if (z.equals(BigInteger.ONE)) {
                        return new PrimalityResult("p is composite", candidateCounter, bigIntegerOperations);
                    }

                    if (z.equals(pMinusOne)) {
                        composite = false;
                        break;
                    }
                }
                if (composite) {
                    return new PrimalityResult("p is composite", candidateCounter, bigIntegerOperations);
                }
            }
        }

        return new PrimalityResult("p is likely prime", candidateCounter, bigIntegerOperations);
    }
}