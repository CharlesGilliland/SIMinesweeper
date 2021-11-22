package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Grid;
import com.tsi.training.charlie_gilliland.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    @Test
    public void populateGridTest(){
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
    public void setDifficultyTest(){
        Grid testGrid = new Grid(10,10);
        int result = testGrid.setDifficulty(5);
        Assertions.assertEquals(40, result, "The number of bombs set by the difficulty is wrong");
    }

    @Test
    public void showAdjacentClearedTest(){
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


}
