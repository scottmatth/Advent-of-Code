package year2015.day.three;

import year2015.day.three.entities.HouseTracker;
import year2021.day.utilities.DataUtility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalculatingForDayThree {

    public static void main (String[] args) throws Exception {
        String houseLocations = DataUtility.loadTestDataFile("HouseHoppingDirections.txt").get(0);

        char[] directions = houseLocations.toCharArray();

        HouseTracker santaTracker = new HouseTracker(0,0);
        HouseTracker roboSantaTracker = new HouseTracker(0,0);
        for(int directionIdx= 0;directionIdx <directions.length;directionIdx++) {
            if(Math.floorMod(directionIdx, 2) == 0) {
                santaTracker.moveSanta(String.valueOf(directions[directionIdx]));
            } else {
                roboSantaTracker.moveSanta(String.valueOf(directions[directionIdx]));
            }
        }

        Set<HouseTracker.HouseLocation> combinedStops = new HashSet<>();
        combinedStops.addAll(santaTracker.getLocations());
        combinedStops.addAll(roboSantaTracker.getLocations());
        System.out.printf("The number of unique house hits is %s", combinedStops.size());
    }
}
