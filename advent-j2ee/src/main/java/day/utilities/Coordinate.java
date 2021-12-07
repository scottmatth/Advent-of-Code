package day.utilities;

import day.four.BingoBoard;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coordinate {
    private final int xCoordinate;
    private final int yCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public static Coordinate create(int xCoordinate, int yCoordinate) {
        return new Coordinate(xCoordinate, yCoordinate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) o;

        return new EqualsBuilder().append(xCoordinate, that.xCoordinate)
                .append(yCoordinate, that.yCoordinate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(xCoordinate).append(yCoordinate).toHashCode();
    }

    @Override
    public String toString() {
        return "Coordinate{" +
               "xCoordinate=" + xCoordinate +
               ", yCoordinate=" + yCoordinate +
               '}';
    }
}
