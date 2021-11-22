package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {


    @Test
    void testToStringFlagged(){
        Tile testTile = new Tile();
        testTile.setHasFlag(true);
        Assertions.assertEquals("|  f  |", testTile.toString());
    }

    @Test
    void testToStringCleared(){
        Tile testTile = new Tile();
        testTile.setBombsNearby(1);
        testTile.setCleared(true);
        Assertions.assertEquals("|  1  |", testTile.toString());
    }

    @Test
    void testToStringUnknown(){
        Tile testTile = new Tile();
        Assertions.assertEquals("|  ?  |", testTile.toString());
    }

}


