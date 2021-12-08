package day.six;

import day.utilities.DataUtility;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaySixCalculating {
    public static void main(String[] argv) throws IOException {

        String[] lanternFishData = DataUtility.loadTestDataFile("initialLanternFish.txt").get(0).split(",");
        List<LanternFish> fishGenerations = new ArrayList<>();

        for (String lanternFishDatum : lanternFishData) {
            fishGenerations.add(new LanternFish(Integer.parseInt(lanternFishDatum)));
        }

        for(int day=1;day <= 80;day++) {
            fishGenerations.forEach(LanternFish::incrementDay);
        }

        Integer reducedCount = fishGenerations.stream()
                .reduce(0, (integer, lanternFish) -> integer + lanternFish.countFish(), Integer::sum);

        // Day 6 part 1 result is 396210
        System.out.printf("The count of the fish after 80 days is %s", reducedCount);
    }

}
