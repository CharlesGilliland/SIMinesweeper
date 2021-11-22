package com.tsi.training.charlie_gilliland.minesweeper;

public class Tile {
    // Attributes
    private boolean hasBomb;
    private boolean hasFlag;
    private boolean cleared;
    private int bombsNearby;


    // Constructors
    public Tile(){
        this.setHasFlag(false);
        this.setCleared(false);
    }

    // Methods
    @Override
    public String toString(){
        if(this.getHasFlag()){
            return "|  f  |";
        }
        else if(this.getCleared()){
            return "|  " + bombsNearby + "  |";
        }
        /*
        else if(this.getHasBomb()){
            return "|  X  |";
        }
         */

        else {
            return "|  ?  |";
        }
    }

    public boolean getHasBomb(){
        return hasBomb;
    }
    public void setHasBomb(boolean value){
        hasBomb = value;
    }

    public boolean getHasFlag(){
        return hasFlag;
    }
    public void setHasFlag(boolean value){
        hasFlag = value;
    }

    public boolean getCleared(){
        return cleared;
    }
    public void setCleared(boolean value){
        cleared = value;
    }

    public int getBombsNearby(){
        return bombsNearby;
    }
    public void setBombsNearby(int bombsNearby){
        this.bombsNearby = bombsNearby;
    }

}
