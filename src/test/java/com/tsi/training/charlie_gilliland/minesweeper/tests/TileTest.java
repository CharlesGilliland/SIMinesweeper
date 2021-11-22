package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {


    @Test
    public void testToStringFlagged(){
        Tile testTile = new Tile();
        testTile.setHasFlag(true);
        Assertions.assertEquals("|  f  |", testTile.toString());
    }

    @Test
    public void testToStringCleared(){
        Tile testTile = new Tile();
        testTile.setBombsNearby(1);
        testTile.setCleared(true);
        Assertions.assertEquals("|  1  |", testTile.toString());
    }

    @Test
    public void testToStringUnknown(){
        Tile testTile = new Tile();
        Assertions.assertEquals("|  ?  |", testTile.toString());
    }

}


