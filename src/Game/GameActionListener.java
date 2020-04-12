package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int column;
    private GameButton btn;
    public GameActionListener(int row, int column, GameButton gameButton) {
        this.row = row;
        this.column = column;
        this. btn = gameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = btn.getBoard();

        if(board.isTurnable(row, column)){
            updateByPlayersData(board);

            if(board.isFull()){
                board.getGame().showMessage("Draw!");
                board.clearField();
            } else {
                updateByAIData(board);
                if(board.isFull()){
                    board.getGame().showMessage("Draw!");
                    board.clearField();
                }
            }
        } else {
            board.getGame().showMessage("Incorrect turn!");
        }
    }

    private void updateByAIData(GameBoard board) {
        int x, y;
        Random rand = new Random();

        do{
            x = rand.nextInt(GameBoard.dimension);
            y = rand.nextInt(GameBoard.dimension);
        }while(!board.isTurnable(x, y));

        board.updateGameField(x, y);

        int cellIndex = GameBoard.dimension * x + y;
        board.getBtn(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if(board.checkWin()){
            btn.getBoard().getGame().showMessage("Computer Win!");
            board.clearField();
        } else {
            board.getGame().passTurn();
        }
    }

    private void updateByPlayersData(GameBoard board) {
        board.updateGameField(row, column);
        btn.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if(board.checkWin()){
            board.getGame().showMessage("YOU WIN!!!");
            board.clearField();
        } else {
            board.getGame().passTurn();
        }
    }
}
