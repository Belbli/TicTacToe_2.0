package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    static int dimension = 3;
    static int cellSize = 150;
    static char nullSmb = '\u0000';
    private char[][] gameField;
    private GameButton[] gameButtons;

    private Game game;

    public GameBoard(Game game) {
        this.game = game;
        initField();
    }

    private void initField() {
        setBounds(cellSize * dimension, cellSize * dimension, 400, 300);
        setTitle("TicTacToe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameBtn = new JButton("New Game");
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameBtn);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(dimension, dimension));
        gamePanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension*dimension];

        for(int i = 0; i < (dimension*dimension); i++){
            gameButtons[i] = new GameButton(i, this);
            gamePanel.add(gameButtons[i]);
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void clearField() {
        for(int i = 0; i < (dimension*dimension); i++){
            gameButtons[i].setText("");
            int row = i / dimension;
            int col = i % dimension;
            gameField[row][col] = nullSmb;
        }
    }

    Game getGame(){
        return game;
    }

    boolean isTurnable(int x, int y){
        return gameField[y][x] == nullSmb;
    }

    void updateGameField(int x, int y){
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
    }

    boolean checkWin(){
        char playerSmb = getGame().getCurrentPlayer().getPlayerSign();

        return checkWinDiagonals(playerSmb) || checkWinLines(playerSmb);
    }

    private boolean checkWinDiagonals(char playerSymbol){
        boolean leftRight, rightLeft, result;

        leftRight = true;
        rightLeft = true;
        result = false;

        for(int i = 0; i < dimension; i++){
            leftRight &= (gameField[i][i] == playerSymbol); //оптимизация кода, чтобы не было через if/else
            rightLeft &= (gameField[dimension - i - 1][i] == playerSymbol);
        }

        return leftRight || rightLeft;
    }

    private boolean checkWinLines(char playerSymbol){
        boolean cols, rows;


        for(int col = 0; col < dimension; col++){
            cols = true;
            rows = true;

            for(int row = 0; row < dimension; row++){
                cols &= (gameField[col][row] == playerSymbol);
                rows &= (gameField[row][col] == playerSymbol);
            }
            if(cols || rows){
                return true;
            }
        }
        return false;
    }
    boolean isFull(){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                if(gameField[i][j] == nullSmb){
                    return false;
                }
            }
        }
        return true;
    }

    public GameButton getBtn(int index){
        return gameButtons[index];
    }
}
