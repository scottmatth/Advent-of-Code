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

    public void addPath(Coordinate beginningCoordinate, Coordinate endCoordinate) {
        Direction direction = determineDirection(beginningCoordinate, endCoordinate);
        if(EnumSet.of(Direction.HORIZONTAL, Direction.VERTICAL).contains(direction )) {
           switch (direction) {
           case VERTICAL:
               for(int xaxis = beginningCoordinate.getxCoordinate();xaxis<= endCoordinate.getxCoordinate();xaxis++){
                   ventPaths.putIfAbsent(Coordinate.create(xaxis, beginningCoordinate.getyCoordinate()),
                                   new VentPathPoint());
                   ventPaths.get(Coordinate.create(xaxis, beginningCoordinate.getyCoordinate())).updatePathPoint();

               }
               break;
           case HORIZONTAL:
               for(int yaxis = beginningCoordinate.getyCoordinate();yaxis<= endCoordinate.getyCoordinate();yaxis++){
                   ventPaths.putIfAbsent(Coordinate.create(beginningCoordinate.getxCoordinate(),yaxis),
                                   new VentPathPoint());
                   ventPaths.get(Coordinate.create(beginningCoordinate.getxCoordinate(),yaxis)).updatePathPoint();

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
