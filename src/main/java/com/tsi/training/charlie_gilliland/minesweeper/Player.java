package com.tsi.training.charlie_gilliland.minesweeper;

public class Player {
    // Attributes
    private String name;
    private int flagsRemaining;
    private int score;
    private boolean hasWon = false;

    // Constructor
    public Player(String name, int flags){
        this.name = name;
        this.flagsRemaining = flags;
        this.score = 0;
    }

    // Methods
    public boolean selectTile(Tile tile){
        if(tile.getHasBomb()){
            return false;
        }
        tile.setCleared(true);
        this.score++;
        return true;
    }

    public void setFlag(Tile tile){
        if(tile.getCleared()){
            return;
        }
        if(tile.getHasFlag()){
            tile.setHasFlag(false);
            this.flagsRemaining++;
        } else {
            tile.setHasFlag(true);
            this.flagsRemaining--;
        }

    }

    public int getFlagsRemaining(){
        return this.flagsRemaining;
    }


    public int getScore(){
        return this.score;
    }

    public void increaseScore(){
        this.score++;
    }

    public boolean getHasWon(){
        return hasWon;
    }

    public void setHasWon(boolean value){
        hasWon = value;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Player player = (Player)obj;
        return this.name == player.name && this.flagsRemaining == player.flagsRemaining && this.score == player.score;
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

}
