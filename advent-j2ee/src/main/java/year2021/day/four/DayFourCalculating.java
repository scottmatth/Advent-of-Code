package year2021.day.four;

import year2021.day.utilities.DataUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFourCalculating {

    public static void main(String[] args) throws IOException {

        List<String> dataLines = DataUtility.loadTestDataFile("bingoDataInput.txt");

        String[] dataInput = dataLines.get(0).split(",");

        List<BingoBoard> bingoBoards = new ArrayList<>();

        int beginningBoardIndex = 2;

        while(beginningBoardIndex < dataLines.size()) {
            String beginingBoardDefinition = dataLines.get(beginningBoardIndex);
            String[] boardRow1Data = beginingBoardDefinition.trim().split("(\\s)+");

            BingoBoard board = new BingoBoard(boardRow1Data.length);
            board.addRowOfData(1, Arrays.asList(boardRow1Data));

            List<String> restOfBoard =
                    dataLines.subList(beginningBoardIndex + 1, beginningBoardIndex + boardRow1Data.length );

            int rowCounter = 2;
            for (String boardRow : restOfBoard) {
                String[] rowData = boardRow.trim().split("(\\s)+");
                board.addRowOfData(rowCounter, Arrays.asList(rowData));
                rowCounter++;
            }

            bingoBoards.add(board);
            beginningBoardIndex += (boardRow1Data.length+1);
        }

        List<BingoBoard> winningBoards = new ArrayList<>();

        BingoBoard lastboard = null;
        String firstWinningNumber = "";
        String lastWinningNumber = "";
        for (String callNumber : dataInput) {
            for (BingoBoard bingoBoard : bingoBoards) {
                if(bingoBoard.getBoardState() == BingoBoard.BoardState.WINNER) {
                    continue;
                }
                BingoBoard.BoardState boardState = bingoBoard.addCalledNumber(callNumber);
                if(boardState == BingoBoard.BoardState.WINNER) {
                    winningBoards.add(bingoBoard);
                }
            }
            if(winningBoards.size() == 1) {
                firstWinningNumber = callNumber;
            }
            if(winningBoards.size() == dataInput.length) {
                lastboard = winningBoards.get(winningBoards.size()-1);
                lastWinningNumber = callNumber;
                break;
            }
        }
        assert lastboard != null;
        System.out.printf("The winning number is %s.  The winning board value is %s." , firstWinningNumber,
                winningBoards.get(0).calculateRemainingBoardValue()*Integer.parseInt(firstWinningNumber));

        System.out.println("The winning board layout is\n"+winningBoards.get(0).toString());

        System.out.printf("  The last called number is %s.  The last winning board value is %s.",
                lastWinningNumber, lastboard.calculateRemainingBoardValue()*Integer.parseInt(lastWinningNumber));

        System.out.println("The Last board layout is\n"+winningBoards.get(0).toString());
    }
}
