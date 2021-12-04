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

    private final List<String> binariesAtThisLevel = new ArrayList<>();

    private BetterBinaryCounts zerosAtThisLevel = null;
    private BetterBinaryCounts onesAtThisLevel = null;

    public BetterBinaryCounts() {
    }

    public void addForThisLevel(String binaryValue, int charPosition) {
        binariesAtThisLevel.add(binaryValue);
        if(charPosition < binaryValue.length()) {
            if(binaryValue.charAt(charPosition) == BitType.ONE.bit.charAt(0)) {
                if(!getOnesAtThisLevel().isPresent()){
                    this.onesAtThisLevel = new BetterBinaryCounts();
                }
                getOnesAtThisLevel().get().addForThisLevel(binaryValue, charPosition+1);
            } else {
                if(!getZerosAtThisLevel().isPresent()){
                    this.zerosAtThisLevel = new BetterBinaryCounts();
                }
                getZerosAtThisLevel().get().addForThisLevel(binaryValue, charPosition+1);
            }
        }
    }

    public List<String> getBinariesAtThisLevel() {
        return binariesAtThisLevel;
    }

    public Optional<BetterBinaryCounts> getZerosAtThisLevel() {
        return Optional.ofNullable(this.zerosAtThisLevel);
    }

    public Optional<BetterBinaryCounts> getOnesAtThisLevel() {
        return Optional.ofNullable(onesAtThisLevel);
    }

    public int binaryCount() {
        return binariesAtThisLevel.size();
    }

    public Optional<BitType> determineMajorValue() {
        Optional<BitType> result = Optional.empty();

        int comparison = Integer.compare(getZerosAtThisLevel().orElse(new BetterBinaryCounts()).binaryCount()
                ,
                getOnesAtThisLevel().orElse(new BetterBinaryCounts()).binaryCount());
        if (comparison > 0  ) {
            result = Optional.of(BitType.ZERO);
        } else if (comparison < 0) {
            result = Optional.of(BitType.ONE);
        }
        return result;
    }

    public Optional<BitType> determineMinorValue() {
        Optional<BitType> result = Optional.empty();
        int comparison = Integer.compare(getZerosAtThisLevel().orElse(new BetterBinaryCounts()).binaryCount(),
                getOnesAtThisLevel().orElse(new BetterBinaryCounts()).binaryCount());

        if (comparison >0) {
                result = Optional.of(BitType.ONE);
        } else if (comparison < 0) {
            result = Optional.of(BitType.ZERO);
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
                 getOnesAtThisLevel().ifPresent(betterBinaryCounts -> result.append(betterBinaryCounts.buildGammaBinary()));
                 break;
             case ZERO:
                 getZerosAtThisLevel().ifPresent(betterBinaryCounts -> result.append(betterBinaryCounts.buildGammaBinary()));
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
                getOnesAtThisLevel().ifPresent(betterBinaryCounts -> result.append(betterBinaryCounts.buildEpsilonBinary()));
                break;
            case ZERO:
                getZerosAtThisLevel().ifPresent(betterBinaryCounts -> result.append(betterBinaryCounts.buildEpsilonBinary()));
                break;
            }
        }
        return result.toString();
    }

    public int getLifeSupportRating() {
        return Integer.parseInt(getOxygenGeneratorRating(),2) *
               Integer.parseInt(getC02ScrubberRating(), 2);
    }

    public String getC02ScrubberRating() {
        StringBuilder returnValue = new StringBuilder("");
        if(this.binaryCount() == 1) {
            returnValue.append(this.binariesAtThisLevel.get(0));
        } else {
            Optional<BitType> bitType = determineMinorValue()   ;
            if(bitType.isPresent()) {
                switch (bitType.get()) {
                case ZERO:
                    getZerosAtThisLevel().ifPresent(zeroCounts -> returnValue.append(zeroCounts.getC02ScrubberRating()));
                    break;
                case ONE:
                    getOnesAtThisLevel().ifPresent(onesCounts -> returnValue.append(onesCounts.getC02ScrubberRating()));
                }
            } else {
                getZerosAtThisLevel().ifPresent(zeroCounts -> returnValue.append(zeroCounts.getC02ScrubberRating()));
            }
        }
        return returnValue.toString();

    }

    public String getOxygenGeneratorRating() {
        StringBuilder returnValue = new StringBuilder("");
        if(this.binaryCount() == 1) {
            returnValue.append(this.binariesAtThisLevel.get(0));
        } else {
            Optional<BitType> bitType = determineMajorValue();
            if(bitType.isPresent()) {
                switch (bitType.get()) {
                case ZERO:
                    getZerosAtThisLevel().ifPresent(zeroCounts -> returnValue.append(zeroCounts.getOxygenGeneratorRating()));
                    break;
                case ONE:
                    getOnesAtThisLevel().ifPresent(onesCounts -> returnValue.append(onesCounts.getOxygenGeneratorRating()));
                }
            } else {
                getOnesAtThisLevel().ifPresent(zeroCounts -> returnValue.append(zeroCounts.getOxygenGeneratorRating()));
            }
        }
        return returnValue.toString();
    }
}
