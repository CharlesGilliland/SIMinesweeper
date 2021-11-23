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
        // Player should have one less flag than they started with
        Assertions.assertEquals(4, testPlayer.getFlagsRemaining(), "Player has a different amount of flag as they should ");

        testPlayer.setFlag(testTile);
        // Player should receive a flag when they take one off of the board
        Assertions.assertEquals(5, testPlayer.getFlagsRemaining(), "Player has a different amount of flag as they should ");

        testTile.setCleared(true);

        testPlayer.setFlag(testTile);

        // SetFlag should not be set if the tile is also cleared
        Assertions.assertFalse(testTile.getHasFlag());
    }

    @Test
    public void testGetFlagsRemaining(){
        Player testPlayer = new Player("Charlie", 5);
        Assertions.assertEquals(5, testPlayer.getFlagsRemaining(), "The flags remaining value returned is wrong");
    }

    @Test
    public void testGetScore(){
        Player testPlayer = new Player("Charlie", 5);

        // Score should be 0 when player is initialized
        Assertions.assertEquals(0, testPlayer.getScore());
    }

    @Test
    public void testIncreaseScore(){
        Player testPlayer = new Player("Charlie", 5);
        testPlayer.increaseScore();
        Assertions.assertEquals(1, testPlayer.getScore());
    }

    @Test
    public void testGetHasWon(){
        Player testPlayer = new Player("Charlie", 5);

        // hasWon should be false when a player is initialized
        Assertions.assertEquals(false, testPlayer.getHasWon());
    }

    @Test
    public void testSetHasWon(){
        Player testPlayer = new Player("Charlie", 5);
        testPlayer.setHasWon(true);

        // hasWon should be false when a player is initialized
        Assertions.assertEquals(true, testPlayer.getHasWon());
    }

    @Test
    public void testGetName(){
        Player testPlayer = new Player("Charlie", 5);

        // getName should return the string of the player name supplied in constructor
        Assertions.assertEquals("Charlie", testPlayer.getName());
    }
}
