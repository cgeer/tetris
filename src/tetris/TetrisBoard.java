/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
/**
 * A Pane in which tetris squares can be displayed.
 * 
 * @author Connor Geer
 */
public class TetrisBoard extends Pane{
    // The size of the side of a tetris square
    public static final int SQUARE_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int X_DIM_SQUARES = 20;
    public static final int Y_DIM_SQUARES = 35;
    private TetrisSquare[][] squares = new TetrisSquare[Y_DIM_SQUARES] [X_DIM_SQUARES];
    private int score;

    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    public TetrisBoard() {
        this.setPrefHeight(Y_DIM_SQUARES*SQUARE_SIZE);
        this.setPrefWidth(X_DIM_SQUARES*SQUARE_SIZE);
    }
    
    public int getX_DIM() {
        return X_DIM_SQUARES;
    }
    
     public int getY_DIM() {
        return Y_DIM_SQUARES;
    }
    /**
     * Add tetris square to array
     * @param square 
     */
    public void addSquare(TetrisSquare square) {
        squares[square.getY()][square.getX()] = square;
    }
    /**
     * Check to see if cell in array is already occupied
     * @param sqY
     * @param sqX
     * @return 
     */
    public boolean checkLocation(int sqY, int sqX) {
        return squares[sqY][sqX] == null;
    }
    /**
     * Check if row has been filled or not
     */
    public void checkRows() {
        int count = 0;
        for(int r = 0; r < squares.length; r++) {
            count = 0;
            for(int c = 0; c < squares[r].length; c++) {
                if(squares[r][c] == null)
                    count++;
            }
            if(count == 0) {
                removeRow(r, squares);
                score += 100;
            }
        }    
    }
    /**
     * Remove row if full
     * @param r
     * @param squares 
     */
    public void removeRow(int r, TetrisSquare[][] squares) {
        for(int i = 0; i < X_DIM_SQUARES; i++) {
            squares[r][i].removeFromDrawing();
            squares[r][i] = null;
        }
        for(int j = r; j > 0; j--) {
            for(int k = 0; k < squares[j - 1].length; k++) {
                if(squares[j][k] == null && squares[j - 1][k] != null) {
                    squares[j-1][k].moveToTetrisLocation(k, j);
                    squares[j][k] = squares[j-1][k];
                    squares[j-1][k] = null;
                }
            }
        }
            
    }
    /**
     * Get score 
     * @return 
     */
    public int getScore() {
        return score;
    }
}
