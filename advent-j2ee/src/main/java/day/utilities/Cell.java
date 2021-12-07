package day.utilities;

import day.four.BingoBoard;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Cell {
    private String cellValue;
    private boolean cellMarked = false;

    public Cell() {
    }

    public Cell(String cellValue) {
        setCellValue(cellValue);
    }

    public String getCellValue() {
        return cellValue;
    }

    public int getIntCellValue() {
        return Integer.parseInt(cellValue);
    }

    public void setCellValue(String cellValue) {

        this.cellValue = cellValue;
    }

    public boolean isCellMarked() {
        return cellMarked;
    }

    public void markCell() {
        this.cellMarked = true;
    }

    public boolean hasValue(String testValue) {
        return StringUtils.equals(cellValue, testValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return new EqualsBuilder().append(cellMarked, cell.cellMarked)
                .append(cellValue, cell.cellValue).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(cellValue).append(cellMarked).toHashCode();
    }
}
