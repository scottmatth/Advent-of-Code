package day.six;

import day.utilities.DataUtility;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaySixCalculating {
    public static void main(String[] argv) throws IOException {

//        String[] lanternFishData = DataUtility.loadTestDataFile("initialLanternFish.txt").get(0).split(",");
//        String[] lanternFishData = "3,4,3,1,2".split(",");

//        List<String> fishGenerations = new ArrayList<>();
//        for (String lanternFishDatum : lanternFishData) {
//            fishGenerations.add(lanternFishDatum);
//        }

        String testData = "3,4,3,1,2";
        List<String> newGeneration = new ArrayList<>();
        for(int day=1;day <= 256;day++) {
            String[] lanternFishData = testData.split(",");

            System.out.printf("Woring on Day %s\n", day);
//            for (String lanternFishDatum : fishGenerations) {
            for (String lanternFishDatum : lanternFishData) {

                if(StringUtils.equals(lanternFishDatum, "0")) {
                    newGeneration.add("8");
                    newGeneration.add("6");
                } else {
                    int datum = Integer.parseInt(lanternFishDatum);
                    newGeneration.add(String.valueOf(--datum));
                }
            }

            testData = StringUtils.join(newGeneration,",");
            newGeneration = new ArrayList<>();
        }
//        Long count = (long) fishGenerations.size();
        Long count = (long) testData.split(",").length;
        // Day 6 part 1 result is 396210
        System.out.printf("The count of the fish after 80 days is %s", count);
    }

}
