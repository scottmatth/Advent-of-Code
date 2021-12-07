package day.four;

import day.utilities.Cell;
import day.utilities.Coordinate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BingoBoard {

    private final int boardXYSize;
    private final Map<Coordinate, Cell> boardCoordinates;

    private BoardState boardState;

    public BoardState getBoardState() {
        return boardState;
    }

    public void addRowOfData(int i, List<String> asList) {
        for(int column=1;column <= boardXYSize;column++) {
            defineBoardValue(i,column, asList.get(column-1));
        }
    }

    public enum BoardState {
        IN_PLAY("Not yet a Winner."),
        WINNER ("This board has won!!!"),
        FULL("All cells on this board have been taken.");

        private final String stateDispaly;

        BoardState(String s) {
            this.stateDispaly = s;
        }
    }

    public BingoBoard(int xySize) {
        this.boardXYSize = xySize;
        this.boardState = BoardState.IN_PLAY;
        this.boardCoordinates = new HashMap<>();
        for(int xidx=1;xidx<= this.boardXYSize;xidx++) {
            for(int yidx=1;yidx<= this.boardXYSize;yidx++) {
                this.boardCoordinates.put(new Coordinate(xidx, yidx), new Cell());
            }
        }
    }

    public BoardState addCalledNumber(String number) {
        if(this.boardState == BoardState.IN_PLAY) {
            for (Map.Entry<Coordinate, Cell> coordinateBingoCellEntry : boardCoordinates.entrySet()) {
                Cell value = coordinateBingoCellEntry.getValue();
                if (value.hasValue(number)) {
                    value.markCell();
                    break;
                }
            }
        } else{
            throw new RuntimeException("Should not add another called number because this Board is ");
        }
        checkForWinner();
        return this.boardState;
    }

    public void checkForWinner() {
        for(int index = 1;index<= this.boardXYSize;index++) {
            checkRow(index);
            if(this.boardState != BoardState.IN_PLAY) {
                break;
            }
            checkColumn(index);
            if(this.boardState != BoardState.IN_PLAY) {
                break;
            }
        }
        checkDiagonals();
    }

    protected void checkDiagonals() {
        checkDownSlopeDiagonal();
        if(this.boardState != BoardState.IN_PLAY) {
            return;
        }
        checkUpSlopeDiagonal();
    }

    protected void checkUpSlopeDiagonal() {

        for(int xIndex=1, yIndex=this.boardXYSize;xIndex<= this.boardXYSize && yIndex >= 1;xIndex++,yIndex--) {
            if(!this.boardCoordinates.get(Coordinate.create(xIndex, yIndex)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;
    }

    protected void checkDownSlopeDiagonal() {
        for(int index1=1;index1<= this.boardXYSize;index1++) {
            if(!this.boardCoordinates.get(Coordinate.create(index1, index1)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;
    }

    protected void checkColumn(int index) {
        for(int rowIndex = 1;rowIndex<= this.boardXYSize;rowIndex++) {
            if(!this.boardCoordinates.get(Coordinate.create(rowIndex, index)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;

    }

    protected void checkRow(int index) {
        for(int columnIndex = 1;columnIndex<= this.boardXYSize;columnIndex++) {
            if(!this.boardCoordinates.get(Coordinate.create(index, columnIndex)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;
    }

    public void defineBoardValue(int xPosition, int yPosition, String positionValue) {
        Coordinate currentCoordinate = Coordinate.create(xPosition, yPosition);
        if(this.boardCoordinates.containsKey(currentCoordinate)) {
            this.boardCoordinates.put(currentCoordinate,new Cell(positionValue));
        } else {
            throw new RuntimeException("The cordinates "+xPosition+"::"+yPosition+" is not defined on the board");
        }
    }

    public Integer calculateRemainingBoardValue() {
        return boardCoordinates.entrySet().stream().map(Map.Entry::getValue).filter(cell -> !cell.isCellMarked())
                .reduce(0, (integer, cell) -> integer + (cell.getIntCellValue()),Integer::sum);
    }

    @Override
    public String toString() {
        StringBuffer results = new StringBuffer("BingoBoard{" +
                   "boardXYSize=" + boardXYSize +
                   ", boardCoordinates=" + boardCoordinates +
                   ", boardState=" + boardState +
                   "}\n\n");
        for(int xAxis =1;xAxis<= boardXYSize;xAxis++) {
            for(int yAxis=1;yAxis<= boardXYSize;yAxis++) {
                results.append("| ");
                Cell currentCell = this.boardCoordinates.get(Coordinate.create(xAxis, yAxis));
                if(currentCell.isCellMarked()) {
                    results.append("+");
                }
                results.append(currentCell.getCellValue());
                if(currentCell.isCellMarked()) {
                    results.append("+");
                }
                results.append(" |");
            }
            results.append("\n");
        }
        return results.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BingoBoard board = (BingoBoard) o;

        return new EqualsBuilder().append(boardXYSize, board.boardXYSize)
                .append(boardCoordinates, board.boardCoordinates).append(boardState, board.boardState).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(boardXYSize).append(boardCoordinates).append(boardState).toHashCode();
    }

}
