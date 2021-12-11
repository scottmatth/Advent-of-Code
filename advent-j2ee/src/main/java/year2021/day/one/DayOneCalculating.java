package year2021.day.one;

import year2021.day.utilities.DataUtility;

public class DayOneCalculating {
    public static void main(String[] args) {

        Integer previous = null;
        int risingCount = 0;
        for (Integer temperature : DataUtility.temperatures) {
            if(previous != null) {
                if(temperature >previous) {
                    risingCount++;
                }
            }
            previous = temperature;
        }
        System.out.println("count of rises for part 1 are " + risingCount);

        Integer lastCapture = null;
        int captureSums = 0;
        for(int index = 2; index < DataUtility.temperatures.size();index++) {
            int currentCapture = DataUtility.temperatures.get(index - 2) + DataUtility.temperatures.get(index - 1)
                                 + DataUtility.temperatures.get(index);
            if(lastCapture != null) {
                if (currentCapture > lastCapture) {
                    captureSums++;
                }
            }
            lastCapture  = currentCapture;
        }
        System.out.printf("The number of rises for part 2 are %s", captureSums);

    }
}
