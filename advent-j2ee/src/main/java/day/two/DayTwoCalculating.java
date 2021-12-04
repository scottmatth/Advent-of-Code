package two;

import javafx.geometry.Pos;
import utilities.DataUtility;

import java.util.HashMap;
import java.util.Map;

public class DayTwoCalculating {

    static Map<DataUtility.Positions, Integer> divePositions = new HashMap<DataUtility.Positions, Integer>(){{
        put(DataUtility.Positions.HORIZONTAL, 0);
        put(DataUtility.Positions.VERTICAL, 0);
        put(DataUtility.Positions.AIM, 0);
    }};

    public enum PositionTypes {

        Forward("forward"),
        Down("down"),
        Up("up");

        private final String referencedDirection;

        PositionTypes(String referencedDirection) {
            this.referencedDirection = referencedDirection;
        }

        public void moveForward(int distance) {
//            System.out.printf("Moving Forward: Old Horizontal is %s, increasing by %s\n",
//                    divePositions.get(DataUtility.Positions.HORIZONTAL), distance);
            moveForward(distance, false);
        }

        private void moveForward(int distance, boolean withAim) {
            divePositions.merge(DataUtility.Positions.HORIZONTAL,distance, Integer::sum);
            if(withAim && divePositions.get(DataUtility.Positions.AIM) != 0) {
                divePositions.merge(DataUtility.Positions.VERTICAL,
                        distance*divePositions.get(DataUtility.Positions.AIM), Integer::sum);
            }
        }

        public void moveUp(int distance) {
//            System.out.printf("Moving Up: Old Vertical is %s, decreasing by %s\n",
//                    divePositions.get(DataUtility.Positions.VERTICAL), distance);
            divePositions.merge(DataUtility.Positions.VERTICAL, -distance, Integer::sum);
        }

        private void moveUpWithAim(int distance) {
            divePositions.merge(DataUtility.Positions.AIM, -distance, Integer::sum);
        }

        public void moveDown(int distance) {
//            System.out.printf("Moving Up: Old Vertical is %s, increasing by %s\n",
//                    divePositions.get(DataUtility.Positions.VERTICAL), distance);
            divePositions.merge(DataUtility.Positions.VERTICAL, distance, Integer::sum);
        }
        public void moveDownWithAim(int distance) {
            divePositions.merge(DataUtility.Positions.AIM, distance, Integer::sum);
        }

        public String getReferencedDirection() {
            return referencedDirection;
        }

        public static PositionTypes fromReference(String currentDirection) {
            PositionTypes foundType= null;
            for (PositionTypes value : values()) {
                if(value.referencedDirection.equals(currentDirection)) {
                    foundType = value;
                    break;
                }
            }
            return foundType;
        }
    }
    public static void main(String[] args) {

        // Part one solution is 1604850
        for (String divePosition : DataUtility.divePositions) {
            String[] splitValues = divePosition.split(" ");

            PositionTypes positionType = PositionTypes.fromReference(splitValues[0]);
            switch (positionType) {
            case Up:
                positionType.moveUp(Integer.parseInt(splitValues[1]));
                break;
            case Down:
                positionType.moveDown(Integer.parseInt(splitValues[1]));
                break;
            case Forward:
                positionType.moveForward(Integer.parseInt(splitValues[1]));
                break;
            }
        }

        System.out.printf("The final horizontal is %s.  The final vertical is %s.  The multiplied values are %s.\n",
                divePositions.get(DataUtility.Positions.HORIZONTAL), divePositions.get(DataUtility.Positions.VERTICAL),
                divePositions.get(DataUtility.Positions.HORIZONTAL) * divePositions.get(DataUtility.Positions.VERTICAL));

        divePositions.put(DataUtility.Positions.AIM, 0);
        divePositions.put(DataUtility.Positions.HORIZONTAL, 0);
        divePositions.put(DataUtility.Positions.VERTICAL, 0);
        for (String divePosition : DataUtility.divePositions) {
            String[] splitValues = divePosition.split(" ");

            PositionTypes positionType = PositionTypes.fromReference(splitValues[0]);
            switch (positionType) {
            case Up:
                positionType.moveUpWithAim(Integer.parseInt(splitValues[1]));
                break;
            case Down:
                positionType.moveDownWithAim(Integer.parseInt(splitValues[1]));
                break;
            case Forward:
                positionType.moveForward(Integer.parseInt(splitValues[1]),true);
                break;
            }
        }

        System.out.printf("Part 2: The final horizontal is %s.  The final vertical is %s.  The multiplied values are %s.\n",
                divePositions.get(DataUtility.Positions.HORIZONTAL), divePositions.get(DataUtility.Positions.VERTICAL),
                divePositions.get(DataUtility.Positions.HORIZONTAL) * divePositions.get(DataUtility.Positions.VERTICAL));

    }
}
