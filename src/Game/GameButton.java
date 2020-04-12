package Game;

import javax.swing.*;

public class GameButton extends JButton {
    private int btnIndex;
    private GameBoard board;


    public GameButton(int gameBtnIndex, GameBoard currentGameBoard){
        this.btnIndex = gameBtnIndex;
        this.board = currentGameBoard;

        int row = btnIndex / GameBoard.dimension;
        int column = btnIndex % GameBoard.dimension;

        setSize(GameBoard.cellSize -5, GameBoard.cellSize -5);
        addActionListener(new GameActionListener(row, column, this));
    }

    public GameBoard getBoard() {
        return board;
    }
}
