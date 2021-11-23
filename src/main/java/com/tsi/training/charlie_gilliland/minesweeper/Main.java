package com.tsi.training.charlie_gilliland.minesweeper;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        boolean gameOver = false;

        // Generating Game Board
        logger.info("Enter height and width for the board:");
        logger.info("Height: ");
        int height = Integer.parseInt(input.next());
        logger.info("Width: ");
        int width = Integer.parseInt(input.next());
        // Setting difficulty
        logger.info("Enter difficulty (1 - 5): ");
        int difficulty = Integer.parseInt(input.next());
        Grid grid = new Grid(height,width);
        grid.setDifficulty(difficulty);
        grid.setTotalBombs();
        grid.assignBombs();




        // Creating Player
        logger.info("Enter your player name:");
        String name = input.next();
        Player player = new Player(name, (grid.getTotalBombs() + 4));


        do{
            logger.info(grid.toString());
            logger.info(player.getName() + " - Current Score: " + player.getScore());

            logger.info("Enter the row of the tile you want to select");
            int rowChoice = Integer.parseInt(input.next());
            logger.info("Enter the column of the tile you want to select");
            int columnChoice = Integer.parseInt(input.next());

            Tile selectedTile = grid.wholeGrid[rowChoice][columnChoice];

            logger.info("Do you want to flag or select? f / s");
            String flagOrSelect = input.next();
            boolean inputOk = false;

            do{
                if(flagOrSelect.equals("f")){
                    if(player.getFlagsRemaining() > 0){
                        player.setFlag(selectedTile);
                        inputOk = true;
                    } else {
                        logger.info("You have no remaining flags");
                    }
                }
                else if (flagOrSelect.equals("s")){
                    boolean success = player.selectTile(selectedTile);
                    if(!success) {
                        player.setHasWon(false);
                        gameOver = true;
                    }
                    player.increaseScore();
                    selectedTile.setBombsNearby(grid.showBombsNearby(rowChoice, columnChoice));
                    if(selectedTile.getBombsNearby() < 1){
                        grid.showAdjacentCleared(rowChoice, columnChoice);
                    }
                    inputOk = true;
                }
                else {
                    logger.info("Enter either f / s");
                    flagOrSelect = input.next();
                }
            } while(!inputOk);
            if(grid.checkBoardCleared()){
                gameOver = true;
                if(grid.checkFlaggedCorrect()){
                    player.setHasWon(true);
                }
            }
        } while(!gameOver);
        if(player.getHasWon()){
            logger.info("Congratulations  " + player.getName() + "!");
            logger.info("You finished with a score of " + player.getScore());
        } else {
            logger.info("You've lost the game, with a final score of " + player.getScore());
            logger.info("Better luck next time!");
        }




    }
}

