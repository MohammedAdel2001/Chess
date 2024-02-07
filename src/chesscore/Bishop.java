
package chesscore;

import java.awt.image.BufferedImage;


public class Bishop extends Piece{
    
    public Bishop(Board board,int cols,int rows,boolean isWhite) {
        
        super(board);
        this.cols=cols;
        this.rows=rows;
        this.isWhite=isWhite;
        this.xPos=cols*board.titlesize;
        this.yPos=rows*board.titlesize;
        this.name="Bishop";
        this.sprite=pic.getSubimage(2*picScale, isWhite ? 0 : picScale, picScale, picScale).getScaledInstance(board.titlesize, board.titlesize, BufferedImage.SCALE_SMOOTH);
        
    }
    
    @Override
    public boolean isValidMovement(int col ,int row)
    {
        return Math.abs(col - this.cols)==Math.abs(row - this.rows);
    }
    
    
        @Override
    public boolean moveCollideWithPiece(int col,int row)
    {
        //up left 
        if(this.cols > col && this.rows > row)
            for(int i =1 ; i< Math.abs(this.cols-col);i++)
                if(board.getPiece(this.cols-i, this.rows-i) != null)
                    return true;
        
        //up right
        if(this.cols < col && this.rows > row)
            for(int i =1 ; i< Math.abs(this.cols-col);i++)
                if(board.getPiece(this.cols+i, this.rows-i) != null)
                    return true;
        //down left 
        if(this.cols > col && this.rows < row)
            for(int i =1 ; i< Math.abs(this.cols-col);i++)
                if(board.getPiece(this.cols-i, this.rows+i) != null)
                    return true;
        
        //down right
        if(this.cols < col && this.rows < row)
            for(int i =1 ; i< Math.abs(this.cols-col);i++)
                if(board.getPiece(this.cols+i, this.rows+i) != null)
                    return true;
        return false;
    }
    
}
