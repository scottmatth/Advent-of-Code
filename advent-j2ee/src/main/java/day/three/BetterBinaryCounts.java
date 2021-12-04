package three;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BetterBinaryCounts {

    enum BitType {
        ONE("1"), ZERO("0");

        private final String bit;
        BitType(String s) {
            bit = s;
        }

        public String getBit() {
            return bit;
        }
    }

    private final List<String> binariesAtThisLevel;

    private final BetterBinaryCounts zerosAtThisLevel;
    private final BetterBinaryCounts onesAtThisLevel;

    public BetterBinaryCounts() {
        binariesAtThisLevel = new ArrayList<>();
        this.zerosAtThisLevel = new BetterBinaryCounts();
        this.onesAtThisLevel = new BetterBinaryCounts();
    }

    public void addForThisLevel(String binaryValue, int charPosition) {
        binariesAtThisLevel.add(binaryValue);
        if(charPosition < binaryValue.length()) {
            if(binaryValue.charAt(charPosition) == BitType.ONE.bit.charAt(0)) {
                onesAtThisLevel.addForThisLevel(binaryValue, charPosition+1);
            } else {
                zerosAtThisLevel.addForThisLevel(binaryValue, charPosition+1);
            }
        }
    }

    public List<String> getBinariesAtThisLevel() {
        return binariesAtThisLevel;
    }

    public BetterBinaryCounts getZerosAtThisLevel() {
        return zerosAtThisLevel;
    }

    public BetterBinaryCounts getOnesAtThisLevel() {
        return onesAtThisLevel;
    }

    public int binaryCount() {
        return binariesAtThisLevel.size();
    }

    public Optional<BitType> determineMajorValue() {
        Optional<BitType> result = Optional.empty();
        if(zerosAtThisLevel.binaryCount()>0 ||onesAtThisLevel.binaryCount()>0) {
            if (zerosAtThisLevel.binaryCount() > onesAtThisLevel.binaryCount()) {
                result = Optional.of(BitType.ZERO);
            } else {
                result = Optional.of(BitType.ONE);
            }
        }
        return result;
    }

    public Optional<BitType> determineMinorValue() {
        Optional<BitType> result = Optional.empty();
        if(zerosAtThisLevel.binaryCount() > 0 || onesAtThisLevel.binaryCount() > 0) {
            if (zerosAtThisLevel.binaryCount() > onesAtThisLevel.binaryCount()) {
                result = Optional.of(BitType.ONE);
            } else {
                result = Optional.of(BitType.ZERO);
            }
        }
        return result;
    }

    public String buildGammaBinary() {
         StringBuilder result = new StringBuilder("");

         Optional<BitType> majorBit = determineMajorValue();

         if(majorBit.isPresent()) {
             BitType bitTypeValue = majorBit.get();
             result.append(bitTypeValue.bit);
             switch (bitTypeValue) {
             case ONE:
                 result.append(onesAtThisLevel.buildGammaBinary());
                 break;
             case ZERO:
                 result.append(zerosAtThisLevel.buildGammaBinary());
                 break;
             }
         }

         return result.toString();
    }

    public String buildEpsilonBinary() {
        StringBuilder result = new StringBuilder("");

        Optional<BitType> minorBit = determineMinorValue();

        if(minorBit.isPresent()) {
            BitType bitTypeValue = minorBit.get();
            result.append(bitTypeValue.bit);
            switch (bitTypeValue) {
            case ONE:
                result.append(onesAtThisLevel.buildEpsilonBinary());
                break;
            case ZERO:
                result.append(zerosAtThisLevel.buildEpsilonBinary());
                break;
            }
        }
        return result.toString();
    }
}
