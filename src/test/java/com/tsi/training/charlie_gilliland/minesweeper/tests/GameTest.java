package com.tsi.training.charlie_gilliland.minesweeper.tests;

import com.tsi.training.charlie_gilliland.minesweeper.*;
import junit.framework.Assert;
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
        Assertions.assertEquals(5, testGame.getGrid().getWholeGrid().length,"The row length is incorrect");

        // Checking that the grid contain the correct number of columns
        Assertions.assertEquals(5, testGame.getGrid().getWholeGrid()[0].length,"The row length is incorrect");
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
        // Here we are creating a board with 25 tiles, 2 mines and 6 flags
        GameOptions testOptions = new GameOptions("Charlie", 5, 5, 1);
        Game testGame = new Game(testOptions);
        testGame.createGame();

        TurnChoices testChoices = new TurnChoices(0,0, true);

        // Assertions should return true as we have flags left so turn is successful
        Assertions.assertEquals(1, testGame.playTurn(testChoices));

        // At this point the player has 5 flags left
        Assertions.assertEquals(5, testGame.getPlayer().getFlagsRemaining(), "The amount of flags remaining is not correct");

        // This uses the remainder of the flags
        TurnChoices testChoices2 = new TurnChoices(0,1, true);
        testGame.playTurn(testChoices2);
        TurnChoices testChoices3 = new TurnChoices(0,2, true);
        testGame.playTurn(testChoices3);
        TurnChoices testChoices4 = new TurnChoices(0,3, true);
        testGame.playTurn(testChoices4);
        TurnChoices testChoices5 = new TurnChoices(0,4, true);
        testGame.playTurn(testChoices5);
        TurnChoices testChoices6 = new TurnChoices(1,0, true);
        testGame.playTurn(testChoices6);
        System.out.println(testGame.getPlayer().getFlagsRemaining());

        // This attempts to use a flag when we have none left
        TurnChoices testChoices7 = new TurnChoices(1,1, true);

        // Method should return 0 if flags are trying to be used without having any left
        Assertions.assertEquals(0, testGame.playTurn(testChoices7), "Play turn is not returning 0 when the player has run out of flags");

        // A new game is created to test the clear functionality
        GameOptions testOptions2 = new GameOptions("Charlie", 2,1,1);
        Game testGame2 = new Game(testOptions2);
        testGame2.createGame();
        int rowOfBomb = 0;
        int columnOfBomb = 0;
        int rowOfClear = 0;
        int columnOfClear = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 1; j++){
                if(!testGame2.getGrid().getWholeGrid()[i][j].getHasBomb()){
                    rowOfBomb = i;
                    columnOfBomb = j;
                } else {
                    rowOfClear = i;
                    columnOfClear = j;
                }
            }
        }
        TurnChoices firstTurn = new TurnChoices(rowOfClear, columnOfClear, true);
        testGame2.playTurn(firstTurn);
        TurnChoices winningTurn = new TurnChoices(rowOfBomb, columnOfBomb, false);
        // A turn that correctly selects an empty space should return -1
        Assertions.assertEquals(1, testGame2.playTurn(winningTurn));
    }

    @Test
    public void testEndOfGameCheck(){
        // Winning Game
        GameOptions testWinningOptions = new GameOptions("Charlie", 2,1,1);
        Game testWinningGame = new Game(testWinningOptions);
        testWinningGame.createGame();
        int rowOfWinningBomb = 0;
        int columnOfWinningBomb = 0;
        int rowOfWinningClear = 0;
        int columnOfWinningClear = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 1; j++){
                if(!testWinningGame.getGrid().getWholeGrid()[i][j].getHasBomb()){
                    rowOfWinningBomb = i;
                    columnOfWinningBomb = j;
                } else {
                    rowOfWinningClear = i;
                    columnOfWinningClear = j;
                }
            }
        }
        TurnChoices firstTurn = new TurnChoices(rowOfWinningClear, columnOfWinningClear, true);
        testWinningGame.playTurn(firstTurn);
        TurnChoices winningTurn = new TurnChoices(rowOfWinningBomb, columnOfWinningBomb, false);
        // A turn that correctly selects an empty space should return -1
        testWinningGame.playTurn(winningTurn);

        Assertions.assertEquals(true, testWinningGame.endOfGameCheck(), "The end of game check is not returning true");


        GameOptions testUnfinishedOptions = new GameOptions("Charlie", 2,1,1);
        Game testUnfinishedGame = new Game(testUnfinishedOptions);
        testUnfinishedGame.createGame();
        int row = 0;
        int column = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 1; j++){
                if(testUnfinishedGame.getGrid().getWholeGrid()[i][j].getHasBomb()){
                    row = i;
                    column = j;
                }
            }
        }
        TurnChoices losingChoices = new TurnChoices(row, column, false);
        testUnfinishedGame.playTurn(losingChoices);

        // End of game should return false if all tiles haven't been accounted for
        Assertions.assertEquals(false, testUnfinishedGame.endOfGameCheck(), "End of game is returning true even though there are tiles left ");
    }

    @Test
    public void testHasWonCheck(){
        // Winning
        GameOptions testWinningOptions = new GameOptions("Charlie", 2,1,1);
        Game testWinningGame = new Game(testWinningOptions);
        testWinningGame.createGame();
        int rowOfWinningBomb = 0;
        int columnOfWinningBomb = 0;
        int rowOfWinningClear = 0;
        int columnOfWinningClear = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 1; j++){
                if(testWinningGame.getGrid().getWholeGrid()[i][j].getHasBomb()){
                    rowOfWinningBomb = i;
                    columnOfWinningBomb = j;
                } else {
                    rowOfWinningClear = i;
                    columnOfWinningClear = j;
                }
            }
        }
        TurnChoices turn1 = new TurnChoices(rowOfWinningClear, columnOfWinningClear, false);
        TurnChoices turn2 = new TurnChoices(rowOfWinningBomb, columnOfWinningBomb, true);
        testWinningGame.playTurn(turn1);
        testWinningGame.playTurn(turn2);
        // hasWonCheck should return true if the player has correctly flagged all bombs
        Assertions.assertEquals(true, testWinningGame.hasWonCheck(), "Game is not showing hasWon correctly");


        // Losing
        GameOptions testLosingOptions = new GameOptions("Charlie", 2,1,1);
        Game testLosingGame = new Game(testLosingOptions);
        testLosingGame.createGame();
        int row = 0;
        int column = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 1; j++){
                if(!testLosingGame.getGrid().getWholeGrid()[i][j].getHasBomb()){
                    row = i;
                    column = j;
                }
            }
        }
        TurnChoices losingChoices = new TurnChoices(row, column, true);
        testLosingGame.playTurn(losingChoices);
        // hasWonCheck should return false if the player has incorrectly placed flags
        Assertions.assertEquals(false, testLosingGame.hasWonCheck(), "End of game is returning true even though there are tiles left ");

    }


}
