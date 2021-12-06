package day.three;

public class BinaryCounts {

    private int ones = 0;
    private int zeros = 0;

    public void incrementValue(String binary) {
        switch (binary) {
        case "1":
            ones++;
            break;
        case "0":
            zeros++;
            break;
        }
    }

    public int getOnes() {
        return ones;
    }

    public int getZeros() {
        return zeros;
    }

    public String getGamma() {
        if (ones > zeros) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getEpsilon() {
        if (ones > zeros) {
            return "0";
        } else {
            return "1";
        }
    }
}
