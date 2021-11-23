package com.tsi.training.charlie_gilliland.minesweeper;

import java.util.Random;

public class Grid {
    // Attributes
    int height;
    int width;
    int totalTiles;
    int difficulty;
    int totalBombs;
    Random randomGen = new Random();

    Tile[][] wholeGrid;


    // Constructor
    public Grid(int h, int w){
        this.height = h;
        this.width = w;
        this.totalTiles = h * w;
        this.wholeGrid = populateGrid();
    }

    // Methods
    public Tile[][] populateGrid(){
        wholeGrid = new Tile[this.height][this.width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                wholeGrid[i][j] = new Tile();
            }
        }
        return wholeGrid;
    }

    public void setDifficulty(int d){
        this.difficulty = d;
        switch(d){
            case 5:
                this.totalBombs = ((int)(totalTiles/2.5));
                break;
            case 4:
                this.totalBombs = ((int)(totalTiles/3.125));
                break;
            case 3:
                this.totalBombs =  ((int)(totalTiles/4.16));
                break;
            case 2:
                this.totalBombs =  ((int)(totalTiles/6.25));
                break;
            default:
                this.totalBombs =  ((int)(totalTiles/12.5));
                break;
        }
    }

    public int getTotalBombs(){
        return totalBombs;
    }



    public void showAdjacentCleared(int r, int c){
        for(int i = r-1; i <= r+1; i++){
            for(int j = c-1; j <= c+1; j ++){
                if(i < 0 || j < 0 || i >= height || j >= width){
                    continue;
                }
                Tile selectedTile = wholeGrid[i][j];
                selectedTile.setBombsNearby(showBombsNearby(i, j));
                if(!selectedTile.getHasBomb()){
                    selectedTile.setCleared(true);
                }
            }
        }
    }

    public int showBombsNearby(int r, int c){
        int totalMines = 0;
        for(int i = r-1; i <= r+1; i++){
            for(int j = c-1; j <= c+1; j ++){
                if(i < 0 || j < 0 || i >= height || j >= width){
                    continue;
                }
                if(wholeGrid[i][j].getHasBomb()){
                    totalMines++;
                }
            }
        }
        return totalMines;
    }

    public boolean checkBoardCleared(){
        boolean boardCleared = true;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(!wholeGrid[i][j].getCleared() && !wholeGrid[i][j].getHasFlag()){
                    boardCleared = false;
                }
            }
        }
        return boardCleared;
    }

    public boolean checkFlaggedCorrect(){
        boolean flagsCorrect = true;

        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(wholeGrid[i][j].getHasBomb()){
                    if(!(wholeGrid[i][j].getHasFlag())){
                        flagsCorrect = false;
                    }
                } else {
                    if(wholeGrid[i][j].getHasFlag()){
                        if(!(wholeGrid[i][j].getHasBomb())){
                            flagsCorrect = false;
                        }
                    }
                }
            }
        }
        return flagsCorrect;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("\n");
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                result.append(wholeGrid[i][j].toString());
            }
            result.append("\n");
        }
        return result.toString();
    }

    public Tile[][] getWholeGrid(){
        return this.wholeGrid;
    }

    public void assignBombs(){
        int bombsRemaining = totalBombs;
        while(bombsRemaining > 0){
            int randomHeight = randomGen.nextInt(height);
            int randomWidth = randomGen.nextInt(width);
            Tile selectedTile = this.wholeGrid[randomHeight][randomWidth];
            if(!selectedTile.getHasBomb()){
                selectedTile.setHasBomb(true);
                bombsRemaining--;
            }
        }
    }
}
