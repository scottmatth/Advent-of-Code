package three;

import utilities.DataUtility;

import java.util.Optional;
import java.util.stream.Stream;

public class DayThreeCalculating {

    public static void main(String[] args) {
        BinaryCounts[] gammaEpsilonData = new BinaryCounts[DataUtility.binaryDataInput.get(0).length()];

        for (String input : DataUtility.binaryDataInput) {
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
        Integer gamma = Integer.parseInt(gammaValue.toString(),2);
        Integer epsilon = Integer.parseInt(epsilonValue.toString(),2);

        System.out.printf("Part 1: The gamma value is %s, the epsilon value is %s.  The power consumption is %s.",
                gamma, epsilon, gamma*epsilon);
    }
}
