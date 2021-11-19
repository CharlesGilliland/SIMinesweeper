package com.tsi.training.charlie_gilliland.minesweeper;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(4,4);
        for(int i = 0; i < grid.height; i++){
            for(int j = 0; j < grid.width; j++){
                System.out.println(grid.wholeGrid[i][j].checkForBomb());
            }
        }
    }
}
