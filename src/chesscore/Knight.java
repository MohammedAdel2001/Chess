
package chesscore;

import java.awt.image.BufferedImage;


public class Knight extends Piece{
    
    public Knight(Board board,int col,int rows,boolean isWhite) {
        super(board);
        this.cols=col;
        this.rows=rows;
        this.xPos=col*board.titlesize;
        this.yPos=rows*board.titlesize;
        this.isWhite=isWhite;
        this.name="Knight";
        this.sprite=pic.getSubimage(3*picScale, isWhite ? 0  : picScale,picScale, picScale).getScaledInstance(board.titlesize,board.titlesize,BufferedImage.SCALE_SMOOTH);
        
        
    }
    @Override
        
    public boolean isValidMovement(int col,int rows){
        
        return Math.abs(col-this.cols)*Math.abs(rows-this.rows)==2; //two steps up and one step left or right
    }
    
    }
    
    
    

