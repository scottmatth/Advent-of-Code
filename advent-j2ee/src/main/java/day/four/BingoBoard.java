package day.four;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BingoBoard {

    private final int boardXYSize;
    private final Map<Coordinate, BingoCell> boardCoordinates;

    private BoardState boardState;

//    private final Map<String, Coordinate> coordinateByValue;

    public String getCellValue(int i, int i1) {
        return this.boardCoordinates.get(Coordinate.quickMake(i, i1)).cellValue;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public void addRowOfData(int i, List<String> asList) {
        for(int column=1;column <= boardXYSize;column++) {
            defineBoardValue(i,column, asList.get(column));
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
//        this.coordinateByValue = new HashMap<>();
        for(int xidx=1;xidx<= this.boardXYSize;xidx++) {
            for(int yidx=1;yidx<= this.boardXYSize;yidx++) {
                this.boardCoordinates.put(new Coordinate(xidx, yidx), new BingoCell());
            }
        }
    }

    public BoardState addCalledNumber(String number) {
        if(this.boardState == BoardState.IN_PLAY) {
            for (Map.Entry<Coordinate, BingoCell> coordinateBingoCellEntry : boardCoordinates.entrySet()) {
                BingoCell value = coordinateBingoCellEntry.getValue();
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
            if(!this.boardCoordinates.get(Coordinate.quickMake(xIndex, yIndex)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;
    }

    protected void checkDownSlopeDiagonal() {
        for(int index1=1;index1<= this.boardXYSize;index1++) {
            if(!this.boardCoordinates.get(Coordinate.quickMake(index1, index1)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;
    }

    protected void checkColumn(int index) {
        for(int rowIndex = 1;rowIndex<= this.boardXYSize;rowIndex++) {
            if(!this.boardCoordinates.get(Coordinate.quickMake(rowIndex, index)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;

    }

    protected void checkRow(int index) {
        for(int columnIndex = 1;columnIndex<= this.boardXYSize;columnIndex++) {
            if(!this.boardCoordinates.get(Coordinate.quickMake(index, columnIndex)).isCellMarked()) {
                return;
            }
        }
        this.boardState = BoardState.WINNER;
    }

    public void defineBoardValue(int xPosition, int yPosition, String positionValue) {
        Coordinate currentCoordinate = Coordinate.quickMake(xPosition, yPosition);
        if(this.boardCoordinates.containsKey(currentCoordinate)) {
            this.boardCoordinates.put(currentCoordinate,new BingoCell(positionValue));
//            this.coordinateByValue.put(positionValue,currentCoordinate);
        } else {
            throw new RuntimeException("The cordinates "+xPosition+"::"+yPosition+" is not defined on the board");
        }
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
        return boardXYSize == board.boardXYSize && Objects.equals(boardCoordinates, board.boardCoordinates)
               && boardState == board.boardState
//               && Objects.equals(coordinateByValue, board.coordinateByValue)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardXYSize, boardCoordinates);
    }

    public static class Coordinate {
        private final int xCoordinate;
        private final int yCoordinate;

        public Coordinate(int xCoordinate, int yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        public int getxCoordinate() {
            return xCoordinate;
        }

        public int getyCoordinate() {
            return yCoordinate;
        }

        public static Coordinate quickMake(int xCoordinate, int yCoordinate) {
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
            return xCoordinate == that.xCoordinate && yCoordinate == that.yCoordinate;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xCoordinate, yCoordinate);
        }
    }

    public static class BingoCell {
        private String cellValue;
        private boolean cellMarked = false;

        public BingoCell() {
        }

        public BingoCell(String cellValue) {
            setCellValue(cellValue);
        }

        public String getCellValue() {
            return cellValue;
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
            return cellValue.equals(testValue);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            BingoCell bingoCell = (BingoCell) o;
            return cellMarked == bingoCell.cellMarked && Objects.equals(cellValue, bingoCell.cellValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cellValue, cellMarked);
        }
    }
}