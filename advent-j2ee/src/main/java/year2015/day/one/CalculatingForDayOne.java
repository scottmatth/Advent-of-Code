package year2015.day.one;

import year2015.day.one.entities.SantaDirectionalCalculator;
import year2021.day.utilities.DataUtility;

import java.io.IOException;
import java.util.List;

public class CalculatingForDayOne {

    public static void main(String[] args) throws IOException {
        List<String> santaDirections = DataUtility.loadTestDataFile("SantaFloorDirections.txt");

        SantaDirectionalCalculator calculator = new SantaDirectionalCalculator(0);
        char[] directionArray = santaDirections.get(0).toCharArray();
        boolean firstInBasement = false;
        for(int directionIdx= 0;directionIdx < directionArray.length;directionIdx++) {
            String santaDirection = String.valueOf(directionArray[directionIdx]);
            calculator.addDirection(santaDirection);
            if(calculator.getFloor() == -1 && !firstInBasement) {
                System.out.printf("Santa went to the basement at direction %s\n", directionIdx+1);
                firstInBasement = true;
            }
        }

        System.out.printf("The final floor is %s", calculator.getFloor());

    }

}
