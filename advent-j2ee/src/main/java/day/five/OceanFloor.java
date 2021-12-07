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

        List<Coordinate> coordinates = Coordinate.determinePointsInLine(coordinateOne, coordinateTwo);

        for (Coordinate coordinate : coordinates) {
            ventPaths.putIfAbsent(coordinate, new VentPathPoint());
            ventPaths.get(coordinate).updatePathPoint();
        }
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
