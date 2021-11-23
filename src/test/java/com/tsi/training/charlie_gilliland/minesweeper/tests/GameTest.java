package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.Game;
import com.tsi.training.charlie_gilliland.minesweeper.GameOptions;
import com.tsi.training.charlie_gilliland.minesweeper.Player;
import com.tsi.training.charlie_gilliland.minesweeper.TurnChoices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void testCreateGame(){
        Game testGame1 = new Game(null);

        int result = testGame1.createGame();

        // Result will be -1 if null is passed instead of gameOptions
        Assertions.assertEquals(-1, result);

        GameOptions testOptions = new GameOptions("Charlie", 5, 5, 1);
        Game testGame2 = new Game(testOptions);
        result = testGame2.createGame();

        // Result will be 1 if gameOptions are passed
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testGetGrid(){
        GameOptions testOptions = new GameOptions("Charlie", 5, 5, 1);
        Game testGame = new Game(testOptions);
        testGame.createGame();

        // Checking that the grid contain the correct number of rows
        Assertions.assertEquals(testGame.getGrid().getWholeGrid().length, 5, "The row length is incorrect");

        // Checking that the grid contain the correct number of columns
        Assertions.assertEquals(testGame.getGrid().getWholeGrid()[0].length, 5, "The row length is incorrect");
    }

    @Test
    public void testGetPlayer(){
        GameOptions testOptions = new GameOptions("Charlie", 10, 10, 1);
        Game testGame = new Game(testOptions);
        testGame.createGame();

        Player testPlayer = new Player("Charlie", 12);

        // Checking the objects are equal
        Assertions.assertEquals(true, testPlayer.equals(testGame.getPlayer()));
    }

    @Test
    public void testPlayTurn(){
        // Here we are creating a board with 100 tiles, 8 mines and 12 flags
        GameOptions testOptions = new GameOptions("Charlie", 10, 10, 1);
        Game testGame = new Game(testOptions);
        testGame.createGame();

        System.out.println(testGame.getPlayer().getFlagsRemaining());

        TurnChoices testChoices = new TurnChoices(0,0, true);

        // Assertions should return true as we have flags left so turn is successful
        Assertions.assertEquals(1, testGame.playTurn(testChoices));

        // At this point the player has 11 flags left
        System.out.println(testGame.getPlayer().getFlagsRemaining());



    }

}
