/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Input extends MouseAdapter{

    Board board;
    
    public Input(Board board)
    {
        this.board=board;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int col = me.getX() / board.titlesize;
        int row = me.getY() / board.titlesize;
        Piece pieceXY = board.getPiece(col, row);
        if(pieceXY != null)
            board.selectedPiece = pieceXY;
        
    }

    
    @Override
    public void mouseDragged(MouseEvent me)
    {
        if(board.selectedPiece != null)
        {
            board.selectedPiece.xPos = me.getX() - board.titlesize / 2;
            board.selectedPiece.yPos = me.getY() - board.titlesize / 2;
            board.repaint();
        }
    }
    
    
    @Override
    public void mouseReleased(MouseEvent me) {
        int col = me.getX() / board.titlesize;
        int row = me.getY() / board.titlesize;
        if(board.selectedPiece != null)
        {
            Move move = new Move(board,board.selectedPiece,col,row);
            if(board.isValidMove(move)){
                board.makeMove(move);
            }else
            {
                board.selectedPiece.xPos = board.selectedPiece.cols *board.titlesize;
                board.selectedPiece.yPos = board.selectedPiece.rows *board.titlesize;
            }
        }
        board.selectedPiece=null;
        board.repaint();
    }
}

