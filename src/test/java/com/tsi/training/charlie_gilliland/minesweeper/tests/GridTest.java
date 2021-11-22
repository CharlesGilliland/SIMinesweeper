package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Grid;
import com.tsi.training.charlie_gilliland.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    @Test
    void testPopulateGrid(){
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
    void testSetDifficulty(){
        Grid testGrid = new Grid(10,10);
        int result = testGrid.setDifficulty(5);
        Assertions.assertEquals(40, result, "The number of bombs set by the difficulty is wrong");
    }

    @Test
    void testShowAdjacentCleared(){
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
    void testShowsBombsNearby(){
        Grid testGrid = new Grid(3,3);
        testGrid.getWholeGrid()[0][0].setHasBomb(true);
        testGrid.getWholeGrid()[0][1].setHasBomb(true);
        Assertions.assertEquals(testGrid.showBombsNearby(1,1), 2, "The number of bombs detected nearby is wrong");
    }

    @Test
    void testCheckBoardCleared(){
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
    void testCheckFlaggedCorrect(){
        Grid testGrid = new Grid(3,3);

        // If no bombs are present this should be true
        Assertions.assertTrue(testGrid.checkFlaggedCorrect());

        testGrid.getWholeGrid()[0][0].setHasBomb(true);
        testGrid.getWholeGrid()[0][1].setHasBomb(true);

        // If bombs are present and not flagged this should be false
        Assertions.assertFalse(testGrid.checkFlaggedCorrect());

        testGrid.getWholeGrid()[0][0].setHasFlag(true);
        testGrid.getWholeGrid()[0][1].setHasBomb(true);

        // If bombs are present and not all flagged this should be false
        Assertions.assertFalse(testGrid.checkFlaggedCorrect());

        testGrid.getWholeGrid()[0][1].setHasFlag(true);

        // If bombs are present and all flagged this should be true
        Assertions.assertTrue(testGrid.checkFlaggedCorrect());
    }

    @Test
    void testToString(){
        Grid testGrid = new Grid(2,2);
        String result = "|  ?  ||  ?  |\n|  ?  ||  ?  |\n";
        Assertions.assertEquals(testGrid.toString(), result, "The grid is not displaying properly with toString");

        testGrid.getWholeGrid()[0][0].setHasFlag(true);
        String resultWithFlag = "|  f  ||  ?  |\n|  ?  ||  ?  |\n";
        Assertions.assertEquals(testGrid.toString(), resultWithFlag, "The grid is not displaying properly with toString");

    }

    @Test
    void testAssignBombs(){
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
        Assertions.assertEquals(testGrid.getTotalBombs(), 0, "Not all the bombs were assigned");
        Assertions.assertEquals(bombsBeforeAssignment, noOfBombs);
    }
}
