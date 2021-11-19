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
        this.wholeGrid = populateGrid(h, w);
    }

    // Methods
    public Tile[][] populateGrid(int width, int height){
        Tile[][] wholeGrid = new Tile[width][height];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                wholeGrid[i][j] = new Tile();
            }
        }
        return wholeGrid;
    }
}
