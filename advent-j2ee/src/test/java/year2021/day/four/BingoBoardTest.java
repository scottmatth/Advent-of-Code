package year2021.day.four;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BingoBoardTest {

    private BingoBoard testBoard;

    @BeforeMethod
    public void setUp() {

        testBoard = new BingoBoard(4);

    }

//    @Test
//    public void testAddCalledNumber() {
//
//    }

    @Test
    public void testCheckForWinner() {
    }

    @Test
    public void testCheckDiagonals() {

        testBoard.defineBoardValue(1,1,"1");
        testBoard.defineBoardValue(1,2,"2");
        testBoard.defineBoardValue(1,3,"3");
        testBoard.defineBoardValue(1,4,"4");

        testBoard.defineBoardValue(2,1,"5");
        testBoard.defineBoardValue(2,2,"6");
        testBoard.defineBoardValue(2,3,"7");
        testBoard.defineBoardValue(2,4,"8");

        testBoard.defineBoardValue(3,1,"9");
        testBoard.defineBoardValue(3,2,"10");
        testBoard.defineBoardValue(3,3,"11");
        testBoard.defineBoardValue(3,4,"12");

        testBoard.defineBoardValue(4,1,"13");
        testBoard.defineBoardValue(4,2,"14");
        testBoard.defineBoardValue(4,3,"15");
        testBoard.defineBoardValue(4,4,"16");

        Assert.assertNotEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);
        testBoard.addCalledNumber("1");
        testBoard.addCalledNumber("6");
        testBoard.addCalledNumber("11");
        testBoard.addCalledNumber("16");
        Assert.assertEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);
    }

    @Test
    public void testCheckUpSlopeDiagonal() {
        testBoard.defineBoardValue(1,1,"1");
        testBoard.defineBoardValue(1,2,"2");
        testBoard.defineBoardValue(1,3,"3");
        testBoard.defineBoardValue(1,4,"4");

        testBoard.defineBoardValue(2,1,"5");
        testBoard.defineBoardValue(2,2,"6");
        testBoard.defineBoardValue(2,3,"7");
        testBoard.defineBoardValue(2,4,"8");

        testBoard.defineBoardValue(3,1,"9");
        testBoard.defineBoardValue(3,2,"10");
        testBoard.defineBoardValue(3,3,"11");
        testBoard.defineBoardValue(3,4,"12");

        testBoard.defineBoardValue(4,1,"13");
        testBoard.defineBoardValue(4,2,"14");
        testBoard.defineBoardValue(4,3,"15");
        testBoard.defineBoardValue(4,4,"16");

        Assert.assertNotEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);
        testBoard.addCalledNumber("4");
        testBoard.addCalledNumber("7");
        testBoard.addCalledNumber("10");
        testBoard.addCalledNumber("13");
        Assert.assertEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);

    }

    @Test
    public void testCheckDownSlopeDiagonal() {
        testBoard.defineBoardValue(1,1,"1");
        testBoard.defineBoardValue(1,2,"2");
        testBoard.defineBoardValue(1,3,"3");
        testBoard.defineBoardValue(1,4,"4");

        testBoard.defineBoardValue(2,1,"5");
        testBoard.defineBoardValue(2,2,"6");
        testBoard.defineBoardValue(2,3,"7");
        testBoard.defineBoardValue(2,4,"8");

        testBoard.defineBoardValue(3,1,"9");
        testBoard.defineBoardValue(3,2,"10");
        testBoard.defineBoardValue(3,3,"11");
        testBoard.defineBoardValue(3,4,"12");

        testBoard.defineBoardValue(4,1,"13");
        testBoard.defineBoardValue(4,2,"14");
        testBoard.defineBoardValue(4,3,"15");
        testBoard.defineBoardValue(4,4,"16");

        Assert.assertNotEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);
        testBoard.addCalledNumber("1");
        testBoard.addCalledNumber("6");
        testBoard.addCalledNumber("11");
        testBoard.addCalledNumber("16");
        Assert.assertEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);

    }

    @Test
    public void testCheckColumn() {
        testBoard.defineBoardValue(1,1,"1");
        testBoard.defineBoardValue(1,2,"2");
        testBoard.defineBoardValue(1,3,"3");
        testBoard.defineBoardValue(1,4,"4");

        testBoard.defineBoardValue(2,1,"5");
        testBoard.defineBoardValue(2,2,"6");
        testBoard.defineBoardValue(2,3,"7");
        testBoard.defineBoardValue(2,4,"8");

        testBoard.defineBoardValue(3,1,"9");
        testBoard.defineBoardValue(3,2,"10");
        testBoard.defineBoardValue(3,3,"11");
        testBoard.defineBoardValue(3,4,"12");

        testBoard.defineBoardValue(4,1,"13");
        testBoard.defineBoardValue(4,2,"14");
        testBoard.defineBoardValue(4,3,"15");
        testBoard.defineBoardValue(4,4,"16");

        Assert.assertNotEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);
        testBoard.addCalledNumber("1");
        testBoard.addCalledNumber("5");
        testBoard.addCalledNumber("9");
        testBoard.addCalledNumber("13");
        Assert.assertEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);

    }

    @Test
    public void testCheckRow() {
        testBoard.defineBoardValue(1,1,"1");
        testBoard.defineBoardValue(1,2,"2");
        testBoard.defineBoardValue(1,3,"3");
        testBoard.defineBoardValue(1,4,"4");

        testBoard.defineBoardValue(2,1,"5");
        testBoard.defineBoardValue(2,2,"6");
        testBoard.defineBoardValue(2,3,"7");
        testBoard.defineBoardValue(2,4,"8");

        testBoard.defineBoardValue(3,1,"9");
        testBoard.defineBoardValue(3,2,"10");
        testBoard.defineBoardValue(3,3,"11");
        testBoard.defineBoardValue(3,4,"12");

        testBoard.defineBoardValue(4,1,"13");
        testBoard.defineBoardValue(4,2,"14");
        testBoard.defineBoardValue(4,3,"15");
        testBoard.defineBoardValue(4,4,"16");

        Assert.assertNotEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);
        testBoard.addCalledNumber("1");
        testBoard.addCalledNumber("2");
        testBoard.addCalledNumber("3");
        testBoard.addCalledNumber("4");
        Assert.assertEquals(testBoard.getBoardState(), BingoBoard.BoardState.WINNER);

    }

    @Test
    public void testDefineBoardValue() {
    }

    @Test
    public void testTestEquals() {
        BingoBoard board1 = new BingoBoard(5);
        BingoBoard board2 = new BingoBoard(6);
        BingoBoard board3 = new BingoBoard(5);
        Assert.assertFalse(board1.equals(board2));
        Assert.assertTrue(board1.equals(board3));


    }

    @Test
    public void testTestHashCode() {
    }
}