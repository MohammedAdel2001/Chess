
package chesscore;

import java.awt.image.BufferedImage;


public class Pawn extends Piece{
    
    public Pawn(Board board,int cols,int rows,boolean isWhite) {
        super(board);
        this.cols=cols;
        this.rows=rows;
        this.xPos=cols*board.titlesize;
        this.yPos=rows*board.titlesize;
        this.isWhite=isWhite;
        this.name="Pawn";
        this.sprite=pic.getSubimage(5*picScale, isWhite ? 0 : picScale, picScale, picScale).getScaledInstance(board.titlesize, board.titlesize, BufferedImage.SCALE_SMOOTH);
        
        
    }

    @Override
    public boolean isValidMovement(int col, int row)
    {
        int colorIndex = this.isWhite ? 1 : -1;
        //push 1
        if(this.cols == col && row == this.rows - colorIndex && board.getPiece(col, row) == null )
        {
            return true;
        }
        //push 2
        else if(isFirstMove && this.cols == col && row == this.rows-(colorIndex*2) && board.getPiece(col, row) == null && board.getPiece(this.cols, this.rows-colorIndex) == null)
        {
            return true;
        }
        //capture left
        else if(col == this.cols - 1 && row == this.rows - colorIndex && board.getPiece(col, row) != null)
        {
            return true;
        }
        //capture right
        else if(col == this.cols + 1 && row == this.rows - colorIndex  && board.getPiece(col, row) != null)
        {
            return true;
        }
        
        //en passant left
        else if(board.getTileNum(col, row) == board.enpassantTile && col == this.cols - 1 && row == this.rows - colorIndex && board.getPiece(col, row + colorIndex) != null)
        {
            return true;
        }
        //en passant right
        else if(board.getTileNum(col, row) == board.enpassantTile && col == this.cols + 1 && row == this.rows - colorIndex && board.getPiece(col, row + colorIndex) != null)
        {
            return true;
        }
        else
            return false;
    }
    
    
}
