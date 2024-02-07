/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;

/**
 *
 * @author LENOVO
 */
public class Move {
     int oldcol;
     int oldrow;
     int newcol;
     int newrow;
     Piece piece;
     Piece capture;
    
    public Move(Board board,Piece piece,int newcol,int newrow)
    {
        this.oldcol = piece.cols;
        this.oldrow = piece.rows;
        this.newcol=newcol;
        this.newrow=newrow;
        this.piece = piece;
        this.capture = board.getPiece(newcol,newrow);
    }
    
}
