package year2015.day.one.entities;

import org.apache.commons.lang3.StringUtils;

public class SantaDirectionalCalculator {

    public static final String DOWN_DIRECTION = ")";
    public static final String UP_DIRECTION = "(";
    private int floor = 0;

    public SantaDirectionalCalculator(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void addDirection (String direction) {
        switch (direction) {
        case DOWN_DIRECTION:
            floor--;
            break;
        case UP_DIRECTION:
            floor++;
            break;
        }
    }
}
