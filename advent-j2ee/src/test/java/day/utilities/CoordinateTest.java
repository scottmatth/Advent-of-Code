package day.utilities;

import day.five.OceanFloor;
import org.apache.commons.collections4.CollectionUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class CoordinateTest {

    @Test
    public void testDetermineDirection() {

        Coordinate coordinate1a = Coordinate.create(1,5);
        Coordinate coordinate1b = Coordinate.create(5,5);

        OceanFloor.Direction direction = Coordinate.determineDirection(coordinate1a, coordinate1b);

        assertThat(direction, is(OceanFloor.Direction.HORIZONTAL));

        Coordinate coordinate2a = Coordinate.create(5,1);
        Coordinate coordinate2b = Coordinate.create(5,5);

        OceanFloor.Direction direction2 = Coordinate.determineDirection(coordinate2a, coordinate2b);

        assertThat(direction2, is(OceanFloor.Direction.VERTICAL));

        Coordinate coordinate3a = Coordinate.create(5,5);
        Coordinate coordinate3b = Coordinate.create(8,8);
        Coordinate coordinate3c = Coordinate.create(2,2);
        Coordinate coordinate3d = Coordinate.create(8,2);
        Coordinate coordinate3e = Coordinate.create(2,8);

        OceanFloor.Direction direction3a = Coordinate.determineDirection(coordinate3a, coordinate3b);
        assertThat(direction3a, is(OceanFloor.Direction.DIAGONAL));
        OceanFloor.Direction direction3b = Coordinate.determineDirection(coordinate3a, coordinate3c);
        assertThat(direction3b, is(OceanFloor.Direction.DIAGONAL));
        OceanFloor.Direction direction3c = Coordinate.determineDirection(coordinate3b, coordinate3c);
        assertThat(direction3c, is(OceanFloor.Direction.DIAGONAL));
        OceanFloor.Direction direction3d = Coordinate.determineDirection(coordinate3d, coordinate3a);
        assertThat(direction3d, is(OceanFloor.Direction.DIAGONAL));

        Coordinate coordinate4a = Coordinate.create(1,5);
        Coordinate coordinate4b = Coordinate.create(2,3);
        OceanFloor.Direction direction4 = Coordinate.determineDirection(coordinate4a, coordinate4b);
        assertThat(direction4, is(OceanFloor.Direction.OTHER));
    }

    @Test
    public void testDeterminePointsInLine() {
        Coordinate coordinate1 = Coordinate.create(1,5);
        Coordinate coordinate2 = Coordinate.create(5,5);

        List<Coordinate> coordinateList = Coordinate.determinePointsInLine(coordinate1, coordinate2);
        Assert.assertTrue(coordinateList.contains(Coordinate.create(1,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(2,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(3,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(4,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(5,5)));

        Coordinate coordinate3 = Coordinate.create(7,1);
        Coordinate coordinate4 = Coordinate.create(7,10);

        coordinateList = Coordinate.determinePointsInLine(coordinate4, coordinate3);

        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,1)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,2)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,3)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,4)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,6)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,8)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,9)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,10)));

        coordinateList = Coordinate.determinePointsInLine(coordinate3, coordinate4);

        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,1)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,2)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,3)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,4)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,6)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,8)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,9)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,10)));

        Coordinate coordinate5 = Coordinate.create(9,7);
        Coordinate coordinate6 = Coordinate.create(6,4);

        coordinateList = Coordinate.determinePointsInLine(coordinate6, coordinate5);
        assertThat(coordinateList, hasSize(4));

        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(8,6)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(6,4)));

        coordinateList = Coordinate.determinePointsInLine(coordinate5, coordinate6);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(8,6)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(6,4)));

        Coordinate coordinate7 = Coordinate.create(6,10);

        coordinateList = Coordinate.determinePointsInLine(coordinate5, coordinate7);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(8,8)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,9)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(6,10)));

        coordinateList = Coordinate.determinePointsInLine(coordinate7, coordinate5);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(8,8)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(7,9)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(6,10)));

        Coordinate coordinate8 = Coordinate.create(12, 10);

        coordinateList = Coordinate.determinePointsInLine(coordinate8, coordinate5);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(10,8)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(11,9)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(12,10)));

        coordinateList = Coordinate.determinePointsInLine(coordinate5, coordinate8);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(10,8)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(11,9)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(12,10)));

        Coordinate coordinate9 = Coordinate.create(12, 4);

        coordinateList = Coordinate.determinePointsInLine(coordinate9, coordinate5);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(10,6)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(11,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(12,4)));

        coordinateList = Coordinate.determinePointsInLine(coordinate5, coordinate9);
        assertThat(coordinateList, hasSize(4));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(9,7)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(10,6)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(11,5)));
        Assert.assertTrue(coordinateList.contains(Coordinate.create(12,4)));

    }

    @Test
    public void testGetxCoordinate() {
    }

    @Test
    public void testGetyCoordinate() {
    }

    @Test
    public void testCreate() {
    }
}