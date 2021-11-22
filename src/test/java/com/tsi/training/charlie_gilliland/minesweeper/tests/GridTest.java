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
    public void testSetDifficulty(){
        Grid testGrid = new Grid(10,10);
        // testGrid.setDifficulty();


    }


}
