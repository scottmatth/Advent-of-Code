package day.five;

import day.utilities.Coordinate;
import day.utilities.DataUtility;

import java.io.IOException;
import java.util.List;

public class DayFiveCalculations {

    public static void main (String[] args) throws IOException {
        List<String> coordinates = DataUtility.loadTestDataFile("thermalVentPaths.txt");

        OceanFloor subPath = new OceanFloor();
        for (String coordinate : coordinates) {
            String[] fromAndToPoints = coordinate.split("\\s->\\s");
            String[] beginning = fromAndToPoints[0].split(",");
            String[] end = fromAndToPoints[1].split(",");

            subPath.addPath(Coordinate.create(Integer.parseInt(beginning[0]),Integer.parseInt(beginning[1])),
                    Coordinate.create(Integer.parseInt(end[0]),Integer.parseInt(end[1])));
        }

        int i = subPath.countMultipleHits();

        System.out.printf("the number of Multiple hit points is %s", subPath.countMultipleHits());

    }

}
