package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Player;
import com.tsi.training.charlie_gilliland.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testSelectTile(){
        Player testPlayer = new Player("Charlie", 5);
        Tile testTile = new Tile();
        testTile.setHasBomb(true);
        // SelectTile should return false if there is a bomb present
        Assertions.assertFalse(testPlayer.selectTile(testTile));

        testTile.setHasBomb(false);
        // SelectTile should return true if there is not a bomb present
        Assertions.assertTrue(testPlayer.selectTile(testTile));

        // The players score should also be incremented by 1
        Assertions.assertEquals(1, testPlayer.getScore());
    }

    @Test
    public void testSetFlag(){
        Tile testTile = new Tile();
        Player testPlayer = new Player("Charlie", 5);
        testPlayer.setFlag(testTile);

        // SetFlag should get HasFlag to be true on the Tile object
        Assertions.assertTrue(testTile.getHasFlag());

        testTile.setHasFlag(false);
        testTile.setCleared(true);

        testPlayer.setFlag(testTile);

        // SetFlag should not be set if the tile is also cleared
        Assertions.assertFalse(testTile.getHasFlag());
    }

    @Test
    public void testIncreaseScore(){
        Player testPlayer = new Player("Charlie", 5);
        testPlayer.increaseScore();
        Assertions.assertEquals(1, testPlayer.getScore());
    }
}
