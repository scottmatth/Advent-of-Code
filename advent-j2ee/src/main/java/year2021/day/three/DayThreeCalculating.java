package year2021.day.three;

import year2021.day.utilities.DataUtility;

import java.util.Optional;

public class DayThreeCalculating {

    public static void main(String[] args) {
        BinaryCounts[] gammaEpsilonData = new BinaryCounts[DataUtility.binaryDataInput.get(0).length()];

        BetterBinaryCounts partTwoImpl = new BetterBinaryCounts();

        for (String input : DataUtility.binaryDataInput) {
            partTwoImpl.addForThisLevel(input, 0);
            for(int inputIndex=0;inputIndex<input.length();inputIndex++) {
                Optional<BinaryCounts> gammaEpsilonDatum = Optional.ofNullable(gammaEpsilonData[inputIndex]);
                if(!gammaEpsilonDatum.isPresent()) {
                    gammaEpsilonData[inputIndex] = new BinaryCounts();
                }

                gammaEpsilonData[inputIndex].incrementValue(String.valueOf(input.charAt(inputIndex)));
            }
        }

        StringBuilder gammaValue = new StringBuilder();
        StringBuilder epsilonValue = new StringBuilder();

        for (BinaryCounts gammaEpsilonDatum : gammaEpsilonData) {
            gammaValue.append(gammaEpsilonDatum.getGamma());
            epsilonValue.append(gammaEpsilonDatum.getEpsilon());
        }
        String betterGamma = partTwoImpl.buildGammaBinary();
        String betterEpsilon = partTwoImpl.buildEpsilonBinary();
        Integer gamma = Integer.parseInt(gammaValue.toString(),2);
        Integer epsilon = Integer.parseInt(epsilonValue.toString(),2);

        //Part 1 value is 1997414
        System.out.printf("Part 1: The gamma value is %s->Int: %s, the epsilon value is %s->Int: %s.  The power consumption is %s.\n",
                gammaValue,gamma, epsilonValue,epsilon, gamma*epsilon);

        System.out.printf("Part 2: The Oxygen generator rating is %s, the CO2 Scubber rating is %s, the life support "
                          + "rating is %s.", partTwoImpl.getOxygenGeneratorRating(), partTwoImpl.getC02ScrubberRating(),
                partTwoImpl.getLifeSupportRating());
    }
}
