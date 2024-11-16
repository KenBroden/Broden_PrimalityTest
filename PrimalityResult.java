public class PrimalityResult {
    private final String result;
    private final int candidateCounter;
    private final int bigIntegerOperations;

    public PrimalityResult(String result, int candidateCounter, int bigIntegerOperations) {
        this.result = result;
        this.candidateCounter = candidateCounter;
        this.bigIntegerOperations = bigIntegerOperations;
    }

    public String getResult() {
        return result;
    }

    public int getCandidateCounter() {
        return candidateCounter;
    }

    public int getBigIntegerOperations() {
        return bigIntegerOperations;
    }
}