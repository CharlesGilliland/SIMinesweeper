package com.tsi.training.charlie_gilliland.minesweeper;

public class Grid {
    // Attributes
    int height;
    int width;

    Tile[][] wholeGrid;


    // Constructor
    public Grid(int h, int w){
        this.height = h;
        this.width = w;
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

    public int getTotalBombs(){
        int total = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(this.wholeGrid[i][j].getHasBomb()){
                    total++;
                }
            }
        }
        return total;
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
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(wholeGrid[i][j].getHasFlag() && !wholeGrid[i][j].getHasBomb()){
                    flagsCorrect = false;
                }
            }
        }
        return flagsCorrect;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                result.append(wholeGrid[i][j].toString());
            }
            result.append("\n");
        }
        return result.toString();
    }
}
