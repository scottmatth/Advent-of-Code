package year2015.day.two;

import org.apache.commons.lang3.StringUtils;
import year2015.day.two.entities.Present;
import year2021.day.utilities.DataUtility;

import java.util.List;

public class CalculatingFor2015DayTwo {

    public static void main(String[] args) throws Exception {
        List<String> wrappingPaperDimensions = DataUtility.loadTestDataFile("WrappingPaperDimensions.txt");

        int totalDimensions = 0;
        int totalRibbonDimensions = 0;
        int counter = 0;
        for (String wrappingPaperDimension : wrappingPaperDimensions) {
            if(StringUtils.isNotBlank(wrappingPaperDimension)){
                String[] currentDimensions = wrappingPaperDimension.trim().split("x");
                Present currentPresent = new Present(Integer.parseInt(currentDimensions[0]),
                        Integer.parseInt(currentDimensions[1]), Integer.parseInt(currentDimensions[2]));
                totalDimensions += currentPresent.wrappingSquareFootage();
                totalRibbonDimensions += currentPresent.ribbonNeeded();
            }
        }

        System.out.printf("The total square footage needed is %s and the total ribbon needed is %s\n", totalDimensions,
                totalRibbonDimensions);
    }
}
