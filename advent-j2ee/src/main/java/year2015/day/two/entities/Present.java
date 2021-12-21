package year2015.day.two.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Present {
    private int length;
    private int width;
    private int height;


    public Present(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int WrappingSquareFootage() {
        int baseDimensions = 2 * length * width + 2 * width * height + 2 * length * height;

        List<Integer> orderedDimensions = new ArrayList<>();
        orderedDimensions.add(length);
        orderedDimensions.add(width);
        orderedDimensions.add(height);

        Collections.sort(orderedDimensions);

        Integer lowestDimention = orderedDimensions.get(0);
        Integer middleDimention = orderedDimensions.get(1);

        baseDimensions += lowestDimention * middleDimention;
        return baseDimensions;
    }
}
