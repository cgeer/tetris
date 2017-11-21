/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.paint.Color;

/**
 * This should be implemented to include your game control.
 * @author Connor Geer
 */
public class TetrisGame {
    private final Tetris tetrisApp;
    private int rand;
    public String shape;
    TetrisSquare square1;
    TetrisSquare square2;
    TetrisSquare square3;
    TetrisSquare square4;
    TetrisBoard board;
    private int move;
    private boolean gameOver = false;
    
    /**
     * Initialize the game. Remove the example code and replace with code
     * that creates a random piece.
     * @param tetrisApp A reference to the application (use to set messages).
     * @param board A reference to the board on which Squares are drawn
     */
    public TetrisGame(Tetris tetrisApp, TetrisBoard board) {
        // Some sample code that places two squares on the board.
        // Take this out and construct your random piece here.
        square1 = new TetrisSquare(board);
        square2 = new TetrisSquare(board);
        square3 = new TetrisSquare(board);
        square4 = new TetrisSquare(board);
        this.board = board;
        square1.moveToTetrisLocation(board.getX_DIM()/2, 3);
        rand = (int) (Math.random() * 7);
        switch(rand) {
            case 0:
                shape = "square";
                createSquare(square1, square2, square3, square4);
                break;
            case 1:
                shape = "leftL";
                createLeftL(square1, square2, square3, square4);
                break;
            case 2:
                shape = "rightL";
                createRightL(square1, square2, square3, square4);
                break;
            case 3:
                shape = "leftZ";
                createLeftZ(square1, square2, square3, square4);
                break;
            case 4:
                shape = "rightZ";
                createRightZ(square1, square2, square3, square4);
                break;
            case 5:
                shape = "rectangle";
                createRectangle(square1, square2, square3, square4);
                break;
            case 6:
                shape = "T";
                createT(square1, square2, square3, square4);
                break;
        }
        
        this.tetrisApp = tetrisApp;
        // You can use this to show the score, etc.
        tetrisApp.setMessage("Game has started!");
    }

    /**
     * Animate the game, by moving the current tetris piece down.
     */
    void update() {
        
        boolean check1 = checkSquares(square1.getX(), square1.getY() + 1, board);
        boolean check2 = checkSquares(square2.getX(), square2.getY() + 1, board);
        boolean check3 = checkSquares(square3.getX(), square3.getY() + 1, board);
        boolean check4 = checkSquares(square4.getX(), square4.getY() + 1, board);
        
        if(check1 && check2 && check3 && check4) {
            commitNewLocation(square1.getX(), square1.getY() + 1, square2.getX(), 
                    square2.getY() + 1, square3.getX(), square3.getY() + 1, 
                    square4.getX(), square4.getY() + 1);
            move++;
        }
        else if (move == 0){
            setGameOver(true);
            tetrisApp.setMessage("Game Over");
        }
        else {
            
            board.addSquare(square1);
            board.addSquare(square2);
            board.addSquare(square3);
            board.addSquare(square4);
            
            square1 = new TetrisSquare(board);
            square2 = new TetrisSquare(board);
            square3 = new TetrisSquare(board);
            square4 = new TetrisSquare(board);
            this.board = board;
            square1.moveToTetrisLocation(board.getX_DIM()/2, 3);
            rand = (int) (Math.random() * 7);
            switch(rand) {
                case 0:
                    shape = "square";
                    createSquare(square1, square2, square3, square4);
                    break;
                case 1:
                    shape = "leftL";
                    createLeftL(square1, square2, square3, square4);
                    break;
                case 2:
                    shape = "rightL";
                    createRightL(square1, square2, square3, square4);
                    break;
                case 3:
                    shape = "leftZ";
                    createLeftZ(square1, square2, square3, square4);
                    break;
                case 4:
                    shape = "rightZ";
                    createRightZ(square1, square2, square3, square4);
                    break;
                case 5:
                    shape = "rectangle";
                    createRectangle(square1, square2, square3, square4);
                    break;
                case 6:
                    shape = "T";
                    createT(square1, square2, square3, square4);
                    break;
                                   
            }
            move = 0;
            board.checkRows();
            tetrisApp.setMessage("Score: " + board.getScore());
        }
    }
    
    /**
     * Move the current tetris piece left.
     */
    void left() {
        System.out.println("left key was pressed!");
        
        boolean check1 = checkSquares(square1.getX() - 1, square1.getY(), board);
        boolean check2 = checkSquares(square2.getX() - 1, square2.getY(), board);
        boolean check3 = checkSquares(square3.getX() - 1, square3.getY(), board);
        boolean check4 = checkSquares(square4.getX() - 1, square4.getY(), board);
        
        if(check2 && check3 && check4 && !getGameOver())
            commitNewLocation(square1.getX() - 1, square1.getY(), square2.getX() - 1, 
                    square2.getY(), square3.getX() - 1, square3.getY(), 
                    square4.getX() - 1, square4.getY());
    }

    /**
     * Move the current tetris piece right.
     */
    void right() {
        System.out.println("right key was pressed!");
        
        boolean check1 = checkSquares(square1.getX() + 1, square1.getY(), board);
        boolean check2 = checkSquares(square2.getX() + 1, square2.getY(), board);
        boolean check3 = checkSquares(square3.getX() + 1, square3.getY(), board);
        boolean check4 = checkSquares(square4.getX() + 1, square4.getY(), board);
        
        if(check2 && check3 && check4 && !getGameOver())
            commitNewLocation(square1.getX() + 1, square1.getY(), square2.getX() + 1, 
                    square2.getY(), square3.getX() + 1, square3.getY(), 
                    square4.getX() + 1, square4.getY());
    }

    /**
     * Drop the current tetris piece.
     */
    void drop() {
        System.out.println("drop key was pressed!");
        
        boolean check1 = checkSquares(square1.getX(), square1.getY() + 1, board);
        boolean check2 = checkSquares(square2.getX(), square2.getY() + 1, board);
        boolean check3 = checkSquares(square3.getX(), square3.getY() + 1, board);
        boolean check4 = checkSquares(square4.getX(), square4.getY() + 1, board);
        
        while(check1 && check2 && check3 && check4 && !getGameOver() == true) {
            
            commitNewLocation(square1.getX(), square1.getY() + 1, square2.getX(), 
                    square2.getY() + 1, square3.getX(), square3.getY() + 1, 
                    square4.getX(), square4.getY() + 1);
            
            check1 = checkSquares(square1.getX(), square1.getY() + 1, board);
            check2 = checkSquares(square2.getX(), square2.getY() + 1, board);
            check3 = checkSquares(square3.getX(), square3.getY() + 1, board);
            check4 = checkSquares(square4.getX(), square4.getY() + 1, board); 
        }
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
     void rotateLeft() {
        System.out.println("rotate left key was pressed!");
        
        int square2NewRelX = square2.getY() - square1.getY();
        int square2NewRelY = -(square2.getX() - square1.getX());
        int square3NewRelX = square3.getY() - square1.getY();
        int square3NewRelY = -(square3.getX() - square1.getX());
        int square4NewRelX = square4.getY() - square1.getY();
        int square4NewRelY = -(square4.getX() - square1.getX());
       
        boolean check2 = checkSquares(square2NewRelX + square1.getX(), 
                square2NewRelY + square1.getY(), board);
        boolean check3 = checkSquares(square3NewRelX + square1.getX(), 
                square3NewRelY + square1.getY(), board);
        boolean check4 = checkSquares(square4NewRelX + square1.getX(), 
                square4NewRelY + square1.getY(), board);
        
        if(check2 && check3 && check4 && !getGameOver())
            commitNewLocation(square1.getX(), square1.getY(), square2NewRelX + square1.getX(), 
                    square2NewRelY + square1.getY(), square3NewRelX + square1.getX(), 
                    square3NewRelY + square1.getY(), square4NewRelX + square1.getX(), 
                    square4NewRelY + square1.getY());     
    }
    
    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight() {
        System.out.println("rotate right key was pressed!");
        
        int square2NewRelX = -(square2.getY() - square1.getY());
        int square2NewRelY = square2.getX() - square1.getX();
        int square3NewRelX = -(square3.getY() - square1.getY());
        int square3NewRelY = square3.getX() - square1.getX();
        int square4NewRelX = -(square4.getY() - square1.getY());
        int square4NewRelY = square4.getX() - square1.getX();
        
        boolean check2 = checkSquares(square2NewRelX + square1.getX(), 
                square2NewRelY + square1.getY(), board);
        boolean check3 = checkSquares(square3NewRelX + square1.getX(), 
                square3NewRelY + square1.getY(), board);
        boolean check4 = checkSquares(square4NewRelX + square1.getX(), 
                square4NewRelY + square1.getY(), board);
        
        if(check2 && check3 && check4 && !getGameOver())
            commitNewLocation(square1.getX(), square1.getY(), square2NewRelX + square1.getX(), 
                    square2NewRelY + square1.getY(), square3NewRelX + square1.getX(), 
                    square3NewRelY + square1.getY(), square4NewRelX + square1.getX(), 
                    square4NewRelY + square1.getY());           
    }
    /**
     * Check if squares can move to desired location
     * @param sqX
     * @param sqY
     * @param board
     * @return 
     */
    boolean checkSquares(int sqX, int sqY, TetrisBoard board) {
        if(sqX < 0 || sqX >= board.getX_DIM())
            return false;
        else if(sqY < 0 || sqY >= board.getY_DIM())
            return false;
        else if((board.checkLocation(sqY, sqX)) == false)
            return false;
        return true;
    }
    /**
     * Move squares to desired location
     * @param sq1X
     * @param sq1Y
     * @param sq2X
     * @param sq2Y
     * @param sq3X
     * @param sq3Y
     * @param sq4X
     * @param sq4Y 
     */
    void commitNewLocation(int sq1X, int sq1Y, int sq2X, int sq2Y, int sq3X,
            int sq3Y, int sq4X, int sq4Y) {
        square1.moveToTetrisLocation(sq1X, sq1Y);
        square2.moveToTetrisLocation(sq2X, sq2Y);
        square3.moveToTetrisLocation(sq3X, sq3Y);
        square4.moveToTetrisLocation(sq4X, sq4Y);
    }
      
    /**
     * Create a square tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createSquare(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
        
        square2.moveToTetrisLocation(square1.getX(), square1.getY() - 1);
        square3.moveToTetrisLocation(square1.getX() - 1, square1.getY() - 1);
        square4.moveToTetrisLocation(square1.getX() - 1, square1.getY());
        square1.setColor(Color.BLUE);
        square2.setColor(Color.BLUE);
        square3.setColor(Color.BLUE);
        square4.setColor(Color.BLUE);
    }
    
    /**
     * Create a Left L tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createLeftL(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
      
        square2.moveToTetrisLocation(square1.getX(), square1.getY() + 1);
        square3.moveToTetrisLocation(square1.getX(), square1.getY() - 1);
        square4.moveToTetrisLocation(square1.getX() - 1, square1.getY() - 1);
        square1.setColor(Color.RED);
        square2.setColor(Color.RED);
        square3.setColor(Color.RED);
        square4.setColor(Color.RED);
    }
    
    /**
     * Create a Right L tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createRightL(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
        
        square2.moveToTetrisLocation(square1.getX(), square1.getY() + 1);
        square3.moveToTetrisLocation(square1.getX(), square1.getY() - 1);
        square4.moveToTetrisLocation(square1.getX() + 1, square1.getY() - 1);
        square1.setColor(Color.VIOLET);
        square2.setColor(Color.VIOLET);
        square3.setColor(Color.VIOLET);
        square4.setColor(Color.VIOLET);
    }
    
    /**
     * Create a Left Z tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createLeftZ(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
        
        square2.moveToTetrisLocation(square1.getX() + 1, square1.getY());
        square3.moveToTetrisLocation(square1.getX() - 1, square1.getY() - 1);
        square4.moveToTetrisLocation(square1.getX(), square1.getY() - 1);
        square1.setColor(Color.ORANGE);
        square2.setColor(Color.ORANGE);
        square3.setColor(Color.ORANGE);
        square4.setColor(Color.ORANGE);
    }
    
    /**
     * Create a Right Z tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createRightZ(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
        
        square2.moveToTetrisLocation(square1.getX() - 1, square1.getY());
        square3.moveToTetrisLocation(square1.getX(), square1.getY() - 1);
        square4.moveToTetrisLocation(square1.getX() + 1, square1.getY() - 1);
        square1.setColor(Color.GREEN);
        square2.setColor(Color.GREEN);
        square3.setColor(Color.GREEN);
        square4.setColor(Color.GREEN);
    }
    
    /**
     * Create a Rectangle tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createRectangle(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
        
        square2.moveToTetrisLocation(square1.getX(), square1.getY() - 1);
        square3.moveToTetrisLocation(square1.getX(), square1.getY() + 1);
        square4.moveToTetrisLocation(square1.getX(), square1.getY() + 2);
        square1.setColor(Color.YELLOW);
        square2.setColor(Color.YELLOW);
        square3.setColor(Color.YELLOW);
        square4.setColor(Color.YELLOW);
    }
    
    /**
     * Create a T tetris piece
     * @param square1
     * @param square2
     * @param square3
     * @param square4 
     */
    void createT(TetrisSquare square1, TetrisSquare square2, 
            TetrisSquare square3, TetrisSquare square4) {
        
        square2.moveToTetrisLocation(square1.getX() - 1, square1.getY());
        square3.moveToTetrisLocation(square1.getX() + 1, square1.getY());
        square4.moveToTetrisLocation(square1.getX(), square1.getY() + 1);
        square1.setColor(Color.AQUA);
        square2.setColor(Color.AQUA);
        square3.setColor(Color.AQUA);
        square4.setColor(Color.AQUA);
    }
    /**
     * Set boolean value gameOver
     * @param game 
     */
    void setGameOver(boolean game) {
        gameOver = game;
    }
    /**
     * Return boolean value gameOver
     * @return 
     */
    boolean getGameOver() {
        return gameOver;
    }
}
