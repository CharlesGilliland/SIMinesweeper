package com.tsi.training.charlie_gilliland.minesweeper;


public class GameOptions {
    // Attributes
    String name;
    int row;
    int column;
    int difficulty;

    // Constructor
    public GameOptions(String n, int r, int c, int d){
        this.name = n;
        this.row = r;
        this.column = c;
        this.difficulty = d;
    }
}
