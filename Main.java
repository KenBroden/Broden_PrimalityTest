import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        MillerRabin millerRabin = new MillerRabin();
        //BigInteger p = new BigInteger("7841");
        //BigInteger p = new BigInteger("239489");
        //BigInteger p = new BigInteger("14161729");
        //BigInteger p = new BigInteger("75361");
        BigInteger p = new BigInteger("1729"); // Hardy-Ramanujan number
        int s = 50; // security parameter
        System.out.println(millerRabin.primality(p, s));
    }
    
}
