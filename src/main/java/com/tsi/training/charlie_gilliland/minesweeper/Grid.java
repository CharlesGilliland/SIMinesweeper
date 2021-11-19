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
        Tile[][] wholeGrid = new Tile[this.height][this.width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                wholeGrid[i][j] = new Tile();
            }
        }
        return wholeGrid;
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
