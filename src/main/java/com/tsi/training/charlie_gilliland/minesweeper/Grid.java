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
                int veryHard = ((int)(totalTiles/2.5));
                this.totalBombs = veryHard == 0 ? 1 : veryHard;
                break;
            case 4:
                int hard = ((int)(totalTiles/3.125));
                this.totalBombs = hard == 0 ? 1 : hard;
                break;
            case 3:
                int normal = ((int)(totalTiles/4.16));
                this.totalBombs =  normal == 0 ? 1 : normal;
                break;
            case 2:
                int easy = ((int)(totalTiles/6.25));
                this.totalBombs =  easy == 0 ? 1 : easy;
                break;
            default:
                int veryEasy = ((int)(totalTiles/12.5));
                this.totalBombs =  veryEasy == 0 ? 1 : veryEasy;
                break;
        }
    }

    public int getTotalBombs(){
        return totalBombs;
    }

    public void showAdjacentCleared(int r, int c){
        Tile selectedTile = wholeGrid[r][c];
        if(!selectedTile.getCleared() && !selectedTile.getHasBomb()){
            selectedTile.setCleared(true);
            for(int i = r-1; i <= r+1; i++){
                for(int j = c-1; j <= c+1; j++){
                    if (i < 0 || j < 0 || i >= height || j >= width) {
                        continue;
                    }
                    if(selectedTile.getBombsNearby() == 0){
                        showAdjacentCleared(i, j);
                    }
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

    public void assignBombsNearby(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.getWholeGrid()[i][j].setBombsNearby(this.showBombsNearby(i, j));
            }
        }
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
                if(wholeGrid[i][j].getHasBomb() && !(wholeGrid[i][j].getHasFlag()) ||
                        !wholeGrid[i][j].getHasBomb() && (wholeGrid[i][j].getHasFlag())){
                    flagsCorrect = false;
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
