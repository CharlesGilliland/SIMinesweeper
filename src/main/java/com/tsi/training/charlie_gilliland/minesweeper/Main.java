package com.tsi.training.charlie_gilliland.minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;

        // Generating Game Board
        System.out.println("Enter height and width for the board:");
        System.out.print("Height: ");
        int height = Integer.parseInt(input.next());
        System.out.print("Width: ");
        int width = Integer.parseInt(input.next());
        Grid grid = new Grid(height,width);

        // Creating Player
        System.out.print("Enter your player name:");
        String name = input.next();
        Player player = new Player(name, (grid.getTotalBombs() + 4));


        do{
            System.out.println(grid);

            System.out.println("Enter the row of the tile you want to select");
            int rowChoice = Integer.parseInt(input.next());
            System.out.println("Enter the column of the tile you want to select");
            int columnChoice = Integer.parseInt(input.next());

            Tile selectedTile = grid.wholeGrid[rowChoice][columnChoice];

            System.out.println("Do you want to flag or select? f / s");
            String flagOrSelect = input.next();
            boolean inputOk = false;

            do{
                if(flagOrSelect.equals("f")){
                    if(player.getFlagsRemaining() > 0){
                        player.setFlag(selectedTile);
                        inputOk = true;
                    } else {
                        System.out.println("You have no remaining flags");
                    }
                }
                else if (flagOrSelect.equals("s")){
                    boolean success = player.selectTile(selectedTile);
                    if(!success) {
                        System.out.println("You've lost the game!");
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
                    System.out.println("Enter either f / s");
                    flagOrSelect = input.next();
                }
            } while(!inputOk);
        } while(!gameOver);




    }
}

