package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Grid;
import com.tsi.training.charlie_gilliland.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    @Test
    public void testPopulateGrid(){
        Grid testGrid = new Grid(5, 5);
        Tile[][] exampleGrid = new Tile[5][5];
        // Creating Tiles in example grid
        boolean classMatch = true;
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5; j++){
                exampleGrid[i][j] = new Tile();
                if(exampleGrid[i][j].getClass() != testGrid.getWholeGrid()[i][j].getClass()){
                    classMatch = false;
                }
            }
        }
        // Testing if the classes of all elements match
        Assertions.assertTrue(classMatch, "The elements are not the same class");
        // Testing the amount of rows
        Assertions.assertEquals(testGrid.getWholeGrid().length, exampleGrid.length, "The arrays have a different number of rows");
        // Testing the amount of columns
        Assertions.assertEquals(testGrid.getWholeGrid()[0].length, exampleGrid[0].length, "The arrays have a different number of columns");
    }

    @Test
    public void testSetDifficulty(){
        Grid testGrid = new Grid(10,10);
        int result = testGrid.setDifficulty(5);
        Assertions.assertEquals(40, result, "The number of bombs set by the difficulty is wrong");
    }

    @Test
    public void testGetAndSetTotalBombs(){
        Grid testGrid = new Grid(10,10);
        testGrid.setDifficulty(5);
        testGrid.setTotalBombs();
        int result = testGrid.getTotalBombs();

        // Set difficulty should set the total number of bombs
        Assertions.assertEquals(40, result, "The number of bombs set by the difficulty is wrong");
    }


    @Test
    public void testShowAdjacentCleared(){
        Grid testGrid = new Grid(3, 3);
        // A new grid is created without any bombs
        testGrid.showAdjacentCleared(1, 1);
        boolean clearedCheck = true;
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++){
                if(!testGrid.getWholeGrid()[i][j].getCleared()){
                    clearedCheck = false;
                }
            }
        }
        Assertions.assertTrue(clearedCheck, "The tiles surrounding were not cleared");
    }

    @Test
    public void testShowsBombsNearby(){
        Grid testGrid = new Grid(3,3);
        testGrid.getWholeGrid()[0][0].setHasBomb(true);
        testGrid.getWholeGrid()[0][1].setHasBomb(true);
        Assertions.assertEquals(2, testGrid.showBombsNearby(1,1), "The number of bombs detected nearby is wrong");
    }

    @Test
    public void testCheckBoardCleared(){
        Grid testGrid = new Grid(2,2);
        // CheckBoardCleared should return false
        Assertions.assertFalse(testGrid.checkBoardCleared());

        testGrid.getWholeGrid()[0][0].setCleared(true);
        testGrid.getWholeGrid()[0][1].setHasFlag(true);

        // CheckBoardCleared should return false
        Assertions.assertFalse(testGrid.checkBoardCleared());

        testGrid.getWholeGrid()[1][0].setCleared(true);
        testGrid.getWholeGrid()[1][1].setHasFlag(true);


        // CheckBoardCleared should return true
        Assertions.assertTrue(testGrid.checkBoardCleared());
    }

    @Test
    public void testCheckFlaggedCorrect(){
        Grid testGrid = new Grid(3,3);

        // If no bombs are present this should be true
        // Assertions.assertTrue(testGrid.checkFlaggedCorrect());

        testGrid.getWholeGrid()[0][0].setHasBomb(true);
        testGrid.getWholeGrid()[0][1].setHasBomb(true);

        // If bombs are present and not flagged this should be false
        // Assertions.assertFalse(testGrid.checkFlaggedCorrect());

        testGrid.getWholeGrid()[0][0].setHasFlag(true);

        // If bombs are present and not all flagged this should be false
        // Assertions.assertFalse(testGrid.checkFlaggedCorrect());

        testGrid.getWholeGrid()[0][1].setHasFlag(true);

        // If bombs are present and all flagged this should be true
        Assertions.assertTrue(testGrid.checkFlaggedCorrect());
    }

    @Test
    public void testToString(){
        Grid testGrid = new Grid(2,2);
        String result = "\n|  ?  ||  ?  |\n|  ?  ||  ?  |\n";
        Assertions.assertEquals(testGrid.toString(), result, "The grid is not displaying properly with toString");

        testGrid.getWholeGrid()[0][0].setHasFlag(true);
        String resultWithFlag = "\n|  f  ||  ?  |\n|  ?  ||  ?  |\n";
        Assertions.assertEquals(testGrid.toString(), resultWithFlag, "The grid is not displaying properly with toString");

    }

    @Test
    public void testGetWholeGrid(){
        Grid testGrid = new Grid(3,3);
        // Asserting that the grid is the correct class
        Assertions.assertEquals("class [[Lcom.tsi.training.charlie_gilliland.minesweeper.Tile;", testGrid.getWholeGrid().getClass().toString());

        // Checking that the grid contain the correct number of rows
        Assertions.assertEquals(testGrid.getWholeGrid().length, 3, "The row length is incorrect");

        // Checking that the grid contain the correct number of columns
        Assertions.assertEquals(testGrid.getWholeGrid()[0].length, 3, "The row length is incorrect");
    }

    @Test
    public void testAssignBombs(){
        Grid testGrid = new Grid(5,5);
        testGrid.setDifficulty(1);
        int bombsBeforeAssignment = testGrid.getTotalBombs();
        testGrid.assignBombs();
        int noOfBombs = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(testGrid.getWholeGrid()[i][j].getHasBomb()){
                    noOfBombs++;
                }
            }
        }
        Assertions.assertEquals(0, testGrid.getTotalBombs(), "Not all the bombs were assigned");
        Assertions.assertEquals(bombsBeforeAssignment, noOfBombs);
    }
}
