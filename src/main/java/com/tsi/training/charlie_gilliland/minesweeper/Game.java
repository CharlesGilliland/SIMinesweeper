package com.tsi.training.charlie_gilliland.minesweeper;


public class Game {
    // Attributes
    GameOptions options;
    Grid grid;
    Player player;

    // Constructor
    public Game(GameOptions gO){
        this.options = gO;
    }

    // Methods
    public void createGame(){
        // Creating the board
        this.grid = new Grid(options.row, options.column);
        this.grid.setDifficulty(options.difficulty);
        this.grid.setTotalBombs();
        this.grid.assignBombs();

        // Creating the player
        this.player = new Player(options.name, this.grid.getTotalBombs() + 4);
    }

    public int playTurn(TurnChoices choices){
        // Turn will return -1 if loss, 0 if flags are unavailable or 1 if turn in successful
        if(this.grid == null || this.player == null){
            return -1;
        }
        Tile selectedTile = grid.getWholeGrid()[choices.row][choices.column];
        if(choices.flagNotSelect){
            if(player.getFlagsRemaining() > 0){
                player.setFlag(selectedTile);
                return 1;
            } else {
                return 0;
            }
        } else {
            boolean result = player.selectTile(selectedTile);
            if(result){
                selectedTile.setBombsNearby(grid.showBombsNearby(choices.row, choices.column));
                if(selectedTile.getBombsNearby() < 1){
                    grid.showAdjacentCleared(choices.row, choices.column);
                }
                return 1;
            } else {
                return -1;
            }
        }
    }

    public boolean endOfGameCheck(){
        if(grid.checkBoardCleared()){
            return true;
        }
        return false;
    }

    public boolean hasWonCheck(){
        if(grid.checkFlaggedCorrect()){
            return true;
        }
        return false;
    }
}