package day.four;

import day.utilities.DataUtility;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFourCalculating {

    public static void main(String[] args) throws IOException {

        List<String> dataLines = DataUtility.loadTestDataFile("bingoDataInput.txt");

        String[] dataInput = dataLines.get(0).split(" ");

        List<BingoBoard> bingoBoards = new ArrayList<>();

        int beginningBoardIndex = 2;

        while(beginningBoardIndex < dataLines.size()) {
            String beginingBoardDefinition = dataLines.get(beginningBoardIndex);
            String[] boardRow1Data = beginingBoardDefinition.split(" ");

            BingoBoard board = new BingoBoard(boardRow1Data.length);
            board.addRowOfData(1, Arrays.asList(boardRow1Data));

            List<String> restOfBoard =
                    dataLines.subList(beginningBoardIndex + 1, beginningBoardIndex + boardRow1Data.length - 1);

            int rowCounter = 2;
            for (String boardRow : restOfBoard) {
                String[] rowData = boardRow.split(" ");
                board.addRowOfData(rowCounter, Arrays.asList(rowData));
                rowCounter++;
            }

            bingoBoards.add(board);
            beginningBoardIndex += (boardRow1Data.length+1);
        }

        List<BingoBoard> winningBoards = new ArrayList<>();
        String winningNumber = "";
        for (String callNumber : dataInput) {
            for (BingoBoard bingoBoard : bingoBoards) {
                BingoBoard.BoardState boardState = bingoBoard.addCalledNumber(callNumber);
                if(boardState == BingoBoard.BoardState.WINNER) {
                    winningBoards.add(bingoBoard);
                }
            }
            if(CollectionUtils.isNotEmpty(winningBoards)) {
                winningNumber = callNumber;
            }
        }
        System.out.printf("The winning number is %s", winningNumber);
    }
}
