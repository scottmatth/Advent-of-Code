package day.utilities;

import day.five.OceanFloor;
import day.five.VentPathPoint;
import day.four.BingoBoard;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate {
    private final int xCoordinate;
    private final int yCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public static OceanFloor.Direction determineDirection(Coordinate beginningCoordinate, Coordinate endCoordinate) {
        OceanFloor.Direction discoveredDirection = OceanFloor.Direction.OTHER;
        if (beginningCoordinate.getxCoordinate() == endCoordinate.getxCoordinate()) {
            discoveredDirection = OceanFloor.Direction.VERTICAL;
        }
        if (beginningCoordinate.getyCoordinate() == endCoordinate.getyCoordinate()) {
            discoveredDirection = OceanFloor.Direction.HORIZONTAL;
        }

        if (Math.abs(beginningCoordinate.getxCoordinate() - endCoordinate.getxCoordinate()) ==
            (Math.abs(beginningCoordinate.getyCoordinate() - endCoordinate.getyCoordinate()))) {
            discoveredDirection = OceanFloor.Direction.DIAGONAL;
        }

        return discoveredDirection;
    }

    public static List<Coordinate> determinePointsInLine(Coordinate coordinateOne, Coordinate coordinateTwo) {
        List<Coordinate> allPoints = new ArrayList<>();

        OceanFloor.Direction direction = determineDirection(coordinateOne, coordinateTwo);

        int beginning;
        int end;
        switch (direction) {
        case HORIZONTAL:
            if (coordinateOne.getxCoordinate() > coordinateTwo.getxCoordinate()) {
                beginning = coordinateTwo.getxCoordinate();
                end = coordinateOne.getxCoordinate();
            } else {
                beginning = coordinateOne.getxCoordinate();
                end = coordinateTwo.getxCoordinate();
            }
            for (int xaxis = beginning; xaxis <= end; xaxis++) {
                allPoints.add(Coordinate.create(xaxis, coordinateOne.getyCoordinate()));
            }
            break;
        case VERTICAL:
            if (coordinateOne.getyCoordinate() > coordinateTwo.getyCoordinate()) {
                beginning = coordinateTwo.getyCoordinate();
                end = coordinateOne.getyCoordinate();
            } else {
                beginning = coordinateOne.getyCoordinate();
                end = coordinateTwo.getyCoordinate();
            }

            for (int yaxis = beginning; yaxis <= end; yaxis++) {
                allPoints.add(Coordinate.create(coordinateOne.getxCoordinate(), yaxis));
            }
            break;
        case DIAGONAL:

            List<Integer> xRange = new ArrayList<>();
            List<Integer> yRange = new ArrayList<>();

            if(coordinateOne.getxCoordinate() < coordinateTwo.getxCoordinate()) {
                xRange = IntStream.rangeClosed(coordinateOne.getxCoordinate(), coordinateTwo.getxCoordinate()).boxed()
                        .collect(Collectors.toList());
            } else {
                for(int xidx = coordinateOne.getxCoordinate();xidx >= coordinateTwo.getxCoordinate();xidx--) {
                    xRange.add(xidx);
                }
            }

            if(coordinateOne.getyCoordinate() < coordinateTwo.getyCoordinate()) {
                yRange = IntStream.rangeClosed(coordinateOne.getyCoordinate(), coordinateTwo.getyCoordinate()).boxed()
                        .collect(Collectors.toList());
            } else {
                for(int yidx = coordinateOne.getyCoordinate();yidx >= coordinateTwo.getyCoordinate();yidx--) {
                    yRange.add(yidx);
                }
            }

            if (xRange.size() != yRange.size()) {
                throw new RuntimeException("The ranges for x and y axis' of the diagnal are not in sync");
            }

            for (int idx = 0; idx < xRange.size(); idx++) {
                allPoints.add(Coordinate.create(xRange.get(idx), yRange.get(idx)));
            }

            break;
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
