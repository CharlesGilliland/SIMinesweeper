package com.tsi.training.charlie_gilliland.minesweeper;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Getting player name
        logger.info("Enter your player name:");
        String name = input.next();

        // Getting board size
        logger.info("Enter rows and columns for the board:");
        logger.info("Rows: ");
        int rows = Integer.parseInt(input.next());
        logger.info("Columns: ");
        int columns = Integer.parseInt(input.next());

        // Getting difficulty
        logger.info("Enter difficulty (1 - 5): ");
        int difficulty = Integer.parseInt(input.next());

        // Creating game options
        GameOptions options = new GameOptions(name, rows, columns, difficulty);

        // Creating game
        Game game = new Game(options);
        game.createGame();

        // Setting condition to keep playing the game
        boolean gameOver = false;
        boolean hasWon = false;

        // Main game loop
        do{
            logger.info(game.grid.toString());
            // Getting choices for tile
            logger.info("Enter the row of the tile you want to select");
            int rowChoice = Integer.parseInt(input.next());
            logger.info("Enter the column of the tile you want to select");
            int columnChoice = Integer.parseInt(input.next());

            // Setting either flag or select
            logger.info("Do you want to flag or select? f / s");
            String flagOrSelect = input.next();
            boolean flagNotSelect = false;
            boolean inputOk = false;
            do {
                if (flagOrSelect.equals("f")) {
                    flagNotSelect = true;
                    inputOk = true;
                } else if (flagOrSelect.equals("s")) {
                    flagNotSelect = false;
                    inputOk = true;
                } else {
                    logger.info("Enter either f / s");
                    flagOrSelect = input.next();
                }
            } while(!inputOk);

            // Creating the turn choices
            TurnChoices choices = new TurnChoices(rowChoice, columnChoice, flagNotSelect);

            // Running the turn with the choices
            game.playTurn(choices);

            gameOver = game.endOfGameCheck();
            game.player.setHasWon(game.hasWonCheck());
        } while(!gameOver);


        if(game.player.getHasWon()){
            logger.info("You have won the game!\nYour final score was " + game.player.getScore());
        } else {
            logger.info("You have lost the game!\nYour final score was " + game.player.getScore());
        }
    }
}

