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
public class King extends Piece{
     public King(Board board,int cols,int rows,boolean isWhite) {
        
        super(board);
        this.cols=cols;
        this.rows=rows;
        this.isWhite=isWhite;
        this.xPos=cols*board.titlesize;
        this.yPos=rows*board.titlesize;
        this.name="King";
        this.sprite=pic.getSubimage(0*picScale, isWhite ? 0 : picScale, picScale, picScale).getScaledInstance(board.titlesize, board.titlesize, BufferedImage.SCALE_SMOOTH);
        
    
}
    @Override
    public boolean isValidMovement(int col, int row)
    {
         return Math.abs(this.cols - col) * Math.abs(this.rows - row) == 1 || Math.abs(this.cols - col) + Math.abs(this.rows - row) == 1 || cancastle(col, row);
    }
    
    private boolean cancastle(int col,int row){
        
        if(this.rows==row){
            
            if(col==6){
                Piece rook=board.getPiece(7, row);
                if(rook !=null &&rook.isFirstMove&& isFirstMove){
                    return board.getPiece(5, row)==null && board.getPiece(6, row)==null && !board.checkScanner.isKingChecked(new Move(board,this,5,row));
                }
            }
            
            else if(col==2){
                Piece rook=board.getPiece(0, row);
                if(rook !=null &&rook.isFirstMove&& isFirstMove){
                    return board.getPiece(3, row)==null && board.getPiece(2, row)==null &&  board.getPiece(1, row)==null &&  !board.checkScanner.isKingChecked(new Move(board,this,3,row));
                }
            }
        }
        
        return false;
    }
}
