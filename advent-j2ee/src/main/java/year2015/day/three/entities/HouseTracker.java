package year2015.day.three.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HouseTracker {

    private HouseLocation currentLocation;

    private Set<HouseLocation> locations = new HashSet<>();

    public HouseTracker(int initialXAxis, int initialYAxis) {
        this.currentLocation = new HouseLocation(initialXAxis, initialYAxis);
        this.locations.add(this.currentLocation);
    }

    public HouseTracker() {
        this(0,0);
    }

    public Set<HouseLocation> getLocations() {
        return locations;
    }

    public HouseLocation getCurrentLocation() {
        return currentLocation;
    }

    public void moveSanta(String direction) {
        final HouseLocation newLocation;
        switch (direction) {
        case "^":
            newLocation = new HouseLocation(this.currentLocation.xAxis, this.currentLocation.yAxis+1);
            break;
        case "v":
            newLocation = new HouseLocation(this.currentLocation.xAxis, this.currentLocation.yAxis-1);
            break;
        case "<":
            newLocation = new HouseLocation(this.currentLocation.xAxis-1, this.currentLocation.yAxis);
            break;
        case ">":
            newLocation = new HouseLocation(this.currentLocation.xAxis+1, this.currentLocation.yAxis);
            break;
        default:
            throw new RuntimeException("No valid direction set");
        }
        this.currentLocation = newLocation;
        this.locations.add(this.currentLocation);
    }

    public static class HouseLocation {
        private final int xAxis;
        private final int yAxis;

        public HouseLocation(int xAxis, int yAxis) {
            this.xAxis = xAxis;
            this.yAxis = yAxis;
        }

        public int getxAxis() {
            return xAxis;
        }

        public int getyAxis() {
            return yAxis;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            HouseLocation that = (HouseLocation) o;

            return new EqualsBuilder().append(xAxis, that.xAxis).append(yAxis, that.yAxis)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(xAxis).append(yAxis).toHashCode();
        }
    }
}
