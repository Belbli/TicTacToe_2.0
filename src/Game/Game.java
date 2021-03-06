package Game;

import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playersTurn = 0;

    public Game(){
        this.board = new GameBoard(this);
    }

    public void initGame(){
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, 'O');
    }

    public void setPlayersTurn(int playersTurn) {
        this.playersTurn = playersTurn;
    }

    void passTurn(){
        if(playersTurn == 0){
            playersTurn = 1;
        } else {
            playersTurn = 0;
        }
    }

    public GamePlayer getCurrentPlayer(){
        return gamePlayers[playersTurn];
    }

    void showMessage(String msg){
        JOptionPane.showMessageDialog(board, msg);
    }
}
