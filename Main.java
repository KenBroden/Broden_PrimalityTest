import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {
        MillerRabin millerRabin = new MillerRabin();
        SecureRandom random = new SecureRandom();
        int s = 50; // security parameter

        // Original testing with known prime and composite numbers
        BigInteger[] testNumbers = {
            new BigInteger("7841"), // prime
            new BigInteger("239489"), // prime
            new BigInteger("14161729"), // prime
            new BigInteger("75361"), // composite
            new BigInteger("1729"), // composite (Hardy-Ramanujan number)
            new BigInteger("41041"), // composite (Euler pseudoprime)
            new BigInteger("133935148040471474143177847583971017625099552967827177777497049755335618245560725769906127113011401290104291099199324383570847636310619315254957324735160761870065225835907567976300120840990418997006294226970491361524986739888117553127359935461524057493651687078579260830065377608463863934969134696588994061863"),
            new BigInteger("105793124698287056898954869396329113561449603909842498109245445116764370051179360068736843880875769153631406957234954697313086186646979144893992625024474232804044668285833202275211686499206860864886227316886517428645393309186850389672409520271820425237940740016067980312072870032457664938577288368454759706511") 
        };

        System.out.println("Testing known prime and composite numbers:");
        for (BigInteger p : testNumbers) {
            System.out.println("\nTesting number: " + p);
            PrimalityResult result = millerRabin.primality(p, s);
            System.out.println(result.getResult());
            System.out.println("Number of candidates a tried: " + result.getCandidateCounter());
            System.out.println("Number of BigInteger operations: " + result.getBigIntegerOperations());
        }

        // Testing with randomly generated large primes
        int bitLength = 1024;
        System.out.println("\nGenerating large primes:");
        for (int i = 0; i < 2; i++) {
            BigInteger p;
            int candidateCounter = 0;
            int totalBigIntegerOperations = 0;
            do {
                p = new BigInteger(bitLength, random);
                candidateCounter++;
                PrimalityResult result = millerRabin.primality(p, s);
                totalBigIntegerOperations += result.getBigIntegerOperations();
            } while (!millerRabin.primality(p, s).getResult().equals("p is likely prime"));

            double averageBigIntegerOperations = (double) totalBigIntegerOperations / candidateCounter;

            System.out.println("\nPrime " + (i + 1) + ": " + p);
            System.out.println("Number of random candidates tried: " + candidateCounter);
            System.out.println("Average number of BigInteger operations per candidate a: " + String.format("%.2f", averageBigIntegerOperations));
        }
    }
}