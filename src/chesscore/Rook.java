/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

import java.awt.image.BufferedImage;

/**
 *
 * @author Lenovo
 */
public class Rook extends Piece{
    
    public Rook(Board board,int cols,int rows,boolean isWhite) {
        
        super(board);
        this.cols=cols;
        this.rows=rows;
        this.isWhite=isWhite;
        this.xPos=cols*board.titlesize;
        this.yPos=rows*board.titlesize;
        this.name="Rook";
        this.sprite=pic.getSubimage(4*picScale, isWhite ? 0 : picScale, picScale, picScale).getScaledInstance(board.titlesize, board.titlesize, BufferedImage.SCALE_SMOOTH);
        
}
    
    @Override
    public boolean isValidMovement(int col, int row)
    {
        return this.cols == col || this.rows == row;
    }
    
    @Override
    public boolean moveCollideWithPiece(int col,int row)
    {
        //left
        if(this.cols > col)
            for (int c = this.cols -1 ; c > col ; c--)
                if(board.getPiece(c, this.rows) != null)
                {
                    return true;
                }
        //right
        if(this.cols < col)
            for(int c = this.cols +1; c < col ;c++)
            {
                if(board.getPiece(c, this.rows) != null)
                    return true;
                    
            }
        
        //up
        if(this.rows > row)
            for(int r = this.rows-1 ; r > row ; r--)
            {
                if(board.getPiece(this.cols, r) != null)
                    return true;
            }
        
        //down
        if(this.rows < row)
            for(int r = this.rows + 1 ; r < row ; r++)
            {
                if(board.getPiece(this.cols, r) != null)
                    return true;
            }
        
        
        return false;
    }
}