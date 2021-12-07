package day.five;

import day.utilities.Coordinate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OceanFloor {
    public enum Direction{
        HORIZONTAL,VERTICAL, DIAGONAL, OTHER;
    }

    Map<Coordinate, VentPathPoint> ventPaths = new HashMap<>();

    public void addPath(Coordinate coordinateOne, Coordinate coordinateTwo) {
        Direction direction = determineDirection(coordinateOne, coordinateTwo);
        if(EnumSet.of(Direction.HORIZONTAL, Direction.VERTICAL).contains(direction )) {
            int beginning;
            int end;
            switch (direction) {
           case VERTICAL:
               if(coordinateOne.getyCoordinate() > coordinateTwo.getyCoordinate()) {
                   beginning = coordinateTwo.getyCoordinate();
                   end = coordinateOne.getyCoordinate();
               } else {
                   beginning = coordinateOne.getyCoordinate();
                   end = coordinateTwo.getyCoordinate();
               }
               for(int xaxis = beginning;xaxis<= end;xaxis++){
                   ventPaths.putIfAbsent(Coordinate.create(xaxis, coordinateOne.getxCoordinate()),
                                   new VentPathPoint());
                   ventPaths.get(Coordinate.create(xaxis, coordinateOne.getxCoordinate())).updatePathPoint();

               }
               break;
           case HORIZONTAL:
               if(coordinateOne.getxCoordinate() > coordinateTwo.getxCoordinate()) {
                   beginning = coordinateTwo.getxCoordinate();
                   end = coordinateOne.getxCoordinate();
               } else {
                   beginning = coordinateOne.getxCoordinate();
                   end = coordinateTwo.getxCoordinate();
               }

               for(int yaxis = beginning;yaxis<= end;yaxis++){
                   ventPaths.putIfAbsent(Coordinate.create(coordinateOne.getyCoordinate(),yaxis),
                                   new VentPathPoint());
                   ventPaths.get(Coordinate.create(coordinateOne.getyCoordinate(),yaxis)).updatePathPoint();

               }
               break;
           }
        }
    }

    public Direction determineDirection(Coordinate beginningCoordinate, Coordinate endCoordinate) {
        Direction discoveredDirection = Direction.OTHER;
        if(beginningCoordinate.getxCoordinate() == endCoordinate.getxCoordinate()) {
            return Direction.VERTICAL;
        }
        if(beginningCoordinate.getyCoordinate() == endCoordinate.getyCoordinate()) {
            return Direction.HORIZONTAL;
        }

        return discoveredDirection;
    }

    public int countMultipleHits() {
        System.out.printf("The number of path points are: %s\n", ventPaths.size());
        List<VentPathPoint> multipleHits =
                ventPaths.values().stream().filter(ventPathPoint -> ventPathPoint.getIntCellValue() > 1).collect(
                        Collectors.toList());
        return multipleHits.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OceanFloor that = (OceanFloor) o;

        return new EqualsBuilder().append(ventPaths, that.ventPaths).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(ventPaths).toHashCode();
    }
}
