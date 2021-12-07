package day.utilities;

import day.five.OceanFloor;
import day.five.VentPathPoint;
import day.four.BingoBoard;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Coordinate {
    private final int xCoordinate;
    private final int yCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public static OceanFloor.Direction determineDirection(Coordinate beginningCoordinate, Coordinate endCoordinate) {
        OceanFloor.Direction discoveredDirection = OceanFloor.Direction.OTHER;
        if(beginningCoordinate.getxCoordinate() == endCoordinate.getxCoordinate()) {
            discoveredDirection = OceanFloor.Direction.HORIZONTAL;
        }
        if(beginningCoordinate.getyCoordinate() == endCoordinate.getyCoordinate()) {
            discoveredDirection = OceanFloor.Direction.VERTICAL;
        }

        if(Math.abs(beginningCoordinate.getxCoordinate() - endCoordinate.getxCoordinate()) ==
           (Math.abs(beginningCoordinate.getyCoordinate() - endCoordinate.getyCoordinate()))) {
            discoveredDirection = OceanFloor.Direction.DIAGONAL;
        }

        return discoveredDirection;
    }

    public static List<Coordinate> determinePointsInLine(Coordinate coordinateOne, Coordinate coordinateTwo) {
        List<Coordinate> allPoints = new ArrayList<>();

        OceanFloor.Direction direction = determineDirection(coordinateOne, coordinateTwo);

        if (EnumSet.of(OceanFloor.Direction.HORIZONTAL, OceanFloor.Direction.VERTICAL).contains(direction)) {
            int beginning;
            int end;
            switch (direction) {
            case HORIZONTAL:
                if(coordinateOne.getyCoordinate() > coordinateTwo.getyCoordinate()) {
                    beginning = coordinateTwo.getyCoordinate();
                    end = coordinateOne.getyCoordinate();
                } else {
                    beginning = coordinateOne.getyCoordinate();
                    end = coordinateTwo.getyCoordinate();
                }
                for(int xaxis = beginning;xaxis<= end;xaxis++){
                    allPoints.add(Coordinate.create(xaxis, coordinateOne.getxCoordinate()));
                }
                break;
            case VERTICAL:
                if(coordinateOne.getxCoordinate() > coordinateTwo.getxCoordinate()) {
                    beginning = coordinateTwo.getxCoordinate();
                    end = coordinateOne.getxCoordinate();
                } else {
                    beginning = coordinateOne.getxCoordinate();
                    end = coordinateTwo.getxCoordinate();
                }

                for(int yaxis = beginning;yaxis<= end;yaxis++){
                    allPoints.add(Coordinate.create(coordinateOne.getyCoordinate(),yaxis));
                }
                break;
            case DIAGONAL:

                break;
            }
        }

        return allPoints;
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
