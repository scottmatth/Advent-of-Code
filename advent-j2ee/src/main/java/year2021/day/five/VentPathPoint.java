package year2021.day.five;

import year2021.day.utilities.Cell;

public class VentPathPoint extends Cell {

    public VentPathPoint() {
        super("0");
        this.markCell();
    }

    public void updatePathPoint() {
        setCellValue(String.valueOf(getIntCellValue()+1));
    }
}
