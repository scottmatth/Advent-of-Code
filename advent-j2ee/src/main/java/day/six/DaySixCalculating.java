package day.six;

import day.utilities.DataUtility;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DaySixCalculating {
    public static void main(String[] argv) throws IOException {

        String[] lanternFishData = DataUtility.loadTestDataFile("initialLanternFish.txt").get(0).split(",");
//        List<LanternFish> fishGenerations = new ArrayList<>();

        LanternFish currentFish;
        BigInteger finalCountDown = new BigInteger("0");
//        for (String lanternFishDatum : lanternFishData) {
        String lanternFishDatum = null;
        for(int dataIdx = 0; dataIdx < lanternFishData.length; dataIdx++) {
            lanternFishDatum = lanternFishData[dataIdx];
            System.out.printf("Working on fish number %s\n", dataIdx + 1);
            currentFish = new LanternFish(Integer.parseInt(lanternFishDatum));
            for (int day = 1; day <= 256; day++) {
                System.out.printf("\t\tWorking on Day %s\n",day);
                currentFish.incrementDay();
            }
            finalCountDown = finalCountDown.add(currentFish.countFish());
        }

//        BigInteger reducedCount = fishGenerations.stream()
//                .reduce(BigInteger.ZERO, (integer, lanternFish) -> integer.add(lanternFish.countFish()), BigInteger::add);

        // Day 6 part 1 result is 396210
//        System.out.printf("The count of the fish after 80 days is %s", reducedCount);
    }

}
