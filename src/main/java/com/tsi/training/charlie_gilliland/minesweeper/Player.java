package com.tsi.training.charlie_gilliland.minesweeper;

public class Player {
    // Attributes
    int flagsRemaining;
    int score;

    // Constructor
    public Player(int flags){
        this.flagsRemaining = flags;
        this.score = 0;
    }

    // Methods
    public boolean selectTile(Tile tile){
        if(tile.getHasBomb() == true){
            return false;
        }
        tile.setCleared(true);
        this.score++;
        return true;
    }
    public void setFlag(Tile tile){
        if(tile.getHasFlag() == true){
            tile.setHasFlag(false);
        } else {
            tile.setHasFlag(true);
        }
    }

}
