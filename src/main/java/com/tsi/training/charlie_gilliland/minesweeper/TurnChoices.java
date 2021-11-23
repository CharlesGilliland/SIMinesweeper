package com.tsi.training.charlie_gilliland.minesweeper;

public class TurnChoices {
    // Attributes
    int row;
    int column;
    boolean flagNotSelect;

    // Constructor
    public TurnChoices(int r, int c, boolean fS){
        this.row = r;
        this.column = c;
        this. flagNotSelect = fS;
    }

}
