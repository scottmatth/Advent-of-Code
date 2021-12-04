package two;

import javafx.geometry.Pos;
import utilities.DataUtility;

import java.util.HashMap;
import java.util.Map;

public class DayTwoCalculating {

    static Map<DataUtility.Positions, Integer> divePositions = new HashMap<DataUtility.Positions, Integer>(){{
        put(DataUtility.Positions.HORIZONTAL, 0);
        put(DataUtility.Positions.VERTICAL, 0);
    }};

    public enum PositionTypes {

        Forward("forward"),
        Down("down"),
        Up("up");

        private final String referencedDirection;

        PositionTypes(String referencedDirection) {
            this.referencedDirection = referencedDirection;
        }

        public static void moveForward(int distance) {
            divePositions.merge(DataUtility.Positions.HORIZONTAL,distance, Integer::sum);
        }

        public static void moveUp(int distance) {
            divePositions.merge(DataUtility.Positions.VERTICAL, distance, Integer::sum);
        }

        public static void moveDown(int distance) {
            divePositions.merge(DataUtility.Positions.VERTICAL, -distance, Integer::sum);
        }

        public String getReferencedDirection() {
            return referencedDirection;
        }

        public static PositionTypes fromReference(String currentDirection) {
            for (PositionTypes value : values()) {
                if(StringUtils value.referencedDirection)
            }

        }
    }
    public static void main(String[] args) {

        for (String divePosition : DataUtility.divePositions) {
            String[] splitValues = divePosition.split(" ");
            switch (splitValues[0]) {

            }
        }

    }
}
