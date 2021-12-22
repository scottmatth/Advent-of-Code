package year2015.day.three;

import year2015.day.three.entities.HouseTracker;
import year2021.day.utilities.DataUtility;

import java.util.List;

public class CalculatingForDayThree {

    public static void main (String[] args) throws Exception {
        String houseLocations = DataUtility.loadTestDataFile("HouseHoppingDirections.txt").get(0);

        char[] directions = houseLocations.toCharArray();

        HouseTracker tracker = new HouseTracker(0,0);
        for (char direction : directions) {
            tracker.moveSanta(String.valueOf(direction));
        }

        System.out.printf("The number of unique house hits is %s", tracker.getLocations().size());
    }
}
