/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

import java.awt.image.BufferedImage;


public class Queen extends Piece{
     public Queen(Board board,int cols,int rows,boolean isWhite) {
        
        super(board);
        this.cols=cols;
        this.rows=rows;
        this.isWhite=isWhite;
        this.xPos=cols*board.titlesize;
        this.yPos=rows*board.titlesize;
        this.name="Queen";
        this.sprite=pic.getSubimage(1*picScale, isWhite ? 0 : picScale, picScale, picScale).getScaledInstance(board.titlesize, board.titlesize, BufferedImage.SCALE_SMOOTH);
        
    
}
    @Override
    public boolean isValidMovement(int col, int row)
    {
        return this.cols == col || this.rows == row || Math.abs(this.cols - col)==Math.abs(this.rows - row);
    }

         @Override
    public boolean moveCollideWithPiece(int col,int row)
    {
        if(this.cols == col || this.rows == row)
        {
            //left
            if(this.cols > col)
                for(int c = this.cols-1 ; c > col ; c--)
                    if(board.getPiece(c, row) != null)
                        return true;

            //right
            if(this.cols < col)
                for(int c = this.cols+1; c < col ; c++)
                    if(this.board.getPiece(c, row) != null)
                        return true;

            //up
            if(this.rows > row)
                for(int r = this.rows-1; r > row ; r--)
                    if(this.board.getPiece(col, r) != null)
                        return true;

            //down
            if(this.rows < row)
                for(int r = this.rows+1; r < row ; r++)
                    if(board.getPiece(col, r) != null)
                        return true;
            return false;
            }
        else
        {
            //up left 
            if(this.cols > col && this.rows > row)
                for(int i = 1 ; i < Math.abs(this.cols - col) ; i++)
                    if(board.getPiece(this.cols-i, this.rows-i) != null)
                        return true;
            //up right
            if(this.cols < col && this.rows > row)
                for(int i = 1; i < Math.abs(this.cols-col);i++)
                    if(board.getPiece(this.cols+i, this.rows-i) != null)
                        return true;
            //down left
            if(this.cols > col && this.rows < row)
                for(int i = 1 ; i < Math.abs(this.cols-col);i++)
                    if(board.getPiece(this.cols-i, this.rows+i) != null)
                        return true;
            
            //down right
            if(this.cols < col && this.rows < row)
                for(int i = 1; i < Math.abs(this.cols-col);i++)
                    if(board.getPiece(this.cols+i, this.rows+i) != null)
                        return true;
            return false;
        }
        
        
        
    }
}

