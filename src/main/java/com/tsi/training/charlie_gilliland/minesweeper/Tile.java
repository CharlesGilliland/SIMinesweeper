package com.tsi.training.charlie_gilliland.minesweeper;

public class Tile {
    // Attributes
    private boolean hasBomb;
    private boolean hasFlag;
    private boolean cleared;


    // Constructors
    public Tile(){
        if(Math.random() < 0.2){
            setHasBomb(true);
        }
    }

    // Methods
    public boolean checkForBomb(){
        if(hasBomb){
            return true;
        }
        return false;
    }

    public boolean getHasBomb(){
        return hasBomb;
    }
    private void setHasBomb(boolean value){
        hasBomb = value;
    }

    public boolean getHasFlag(){
        return hasBomb;
    }
    private void setHasFlag(boolean value){
        hasFlag = value;
    }

    public boolean getCleared(){
        return hasBomb;
    }
    private void setCleared(boolean value){
        cleared = value;
    }




}
