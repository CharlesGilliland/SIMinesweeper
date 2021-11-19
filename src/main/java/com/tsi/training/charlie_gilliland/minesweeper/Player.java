package com.tsi.training.charlie_gilliland.minesweeper;

public class Player {
    // Attributes
    String name;
    private int flagsRemaining;
    private int score;

    // Constructor
    public Player(String name, int flags){
        this.name = name;
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

    public int getFlagsRemaining(){
        return this.flagsRemaining;
    }

    public void increaseScore(){
        this.score++;
    }

}
