/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chesscore;


public class CheckScanner {
    Board board;
    public CheckScanner(Board board)
    {
        this.board = board;
    }
    
    public boolean isKingChecked(Move move){
        
        Piece King=board.findKing(move.piece.isWhite);
        assert King!=null;
        int KingCols=King.cols;
        int KingRows=King.rows;
        if(board.selectedPiece !=null && board.selectedPiece.name.equals("King")){
            KingCols=move.newcol;
            KingRows=move.newrow;
        }
        return hitByRook(move.newcol, move.newrow, King, KingCols, KingRows, 0, 1)|| 
               hitByRook(move.newcol, move.newrow, King, KingCols, KingRows, 1, 0)||
               hitByRook(move.newcol, move.newrow, King, KingCols, KingRows, 0, -1)||
               hitByRook(move.newcol, move.newrow, King, KingCols, KingRows, -1, 0)||
               hitByBishop(move.newcol, move.newrow, King, KingCols, KingRows, -1, -1)||
               hitByBishop(move.newcol, move.newrow, King, KingCols, KingRows, 1, -1)||
               hitByBishop(move.newcol, move.newrow, King, KingCols, KingRows, 1, 1)||
               hitByBishop(move.newcol, move.newrow, King, KingCols, KingRows, -1, 1)||
               
               hitByKnight(move.newcol, move.newrow, King, KingCols, KingRows)|| 
               hitByPawn(move.newcol, move.newrow, King, KingCols, KingRows)||
               hitByKing(King, KingCols, KingRows);
    }
    
    private boolean hitByRook(int col,int row ,Piece King , int KingCols,int KingRows,int ColVal,int RowVal){
        
        for(int i=1;i<8;i++){
            if(KingCols +(i*ColVal)==col && KingRows+(i*RowVal)==row){
              break;  
            }
            
            Piece piece=board.getPiece(KingCols +(i*ColVal) , KingRows+(i*RowVal));
            
            if(piece !=null && piece!=board.selectedPiece){
                if(!board.sameTeam(piece, King)&&(piece.name.equals("Rook")||piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
            
        
        }
        return false;
    }
    
    private boolean hitByBishop(int col,int row ,Piece King , int KingCols,int KingRows,int ColVal,int RowVal){
        
        for(int i=1;i<8;i++){
            if(KingCols -(i*ColVal)==col && KingRows-(i*RowVal)==row){
              break;  
            }
            
            Piece piece=board.getPiece(KingCols -(i*ColVal) , KingRows-(i*RowVal));
            
            if(piece !=null && piece!=board.selectedPiece){
                if(!board.sameTeam(piece, King)&&(piece.name.equals("Bishop")||piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
            
        
        }
        return false;
        
         }
    
         private boolean hitByKnight(int col,int row ,Piece King , int KingCols,int KingRows){
         
             return CheckKnight(board.getPiece(KingCols-1, KingRows-2), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols+1, KingRows-2), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols+2, KingRows-1), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols+2, KingRows+1), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols+1, KingRows+2), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols-1, KingRows+2), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols-2, KingRows+1), King,col, row) ||
                    CheckKnight(board.getPiece(KingCols-2, KingRows-1), King,col, row);
           
                     
             
         }
        private boolean CheckKnight(Piece p,Piece k,int col,int row){
            
            return p!=null && !board.sameTeam(p, k) &&p.name.equals("Knight") && !(p.cols==col && p.rows==row);
            
        }
        
          private boolean hitByKing(Piece King , int KingCols,int KingRows){
              
              return CheckKing(board.getPiece(KingCols-1, KingRows-1), King)||  
                     CheckKing(board.getPiece(KingCols+1, KingRows-1), King)||
                     CheckKing(board.getPiece(KingCols, KingRows-1), King)||
                     CheckKing(board.getPiece(KingCols-1, KingRows), King)||
                     CheckKing(board.getPiece(KingCols+1, KingRows), King)||
                     CheckKing(board.getPiece(KingCols-1, KingRows+1), King)||
                     CheckKing(board.getPiece(KingCols+1, KingRows+1), King)||
                     CheckKing(board.getPiece(KingCols, KingRows+1), King);
          }
        
    
         private boolean CheckKing(Piece p,Piece k){              
             return p!=null && !board.sameTeam(p, k) &&p.name.equals("King");
         }
    
    
         private boolean hitByPawn(int col,int row ,Piece King , int KingCols,int KingRows){  
             int ColorVal=King.isWhite ? -1 : 1;
             return CheckPawn(board.getPiece(KingCols+1, KingRows+ColorVal), King, col, row)||
                    CheckPawn(board.getPiece(KingCols-1, KingRows+ColorVal), King, col, row);
         }
         
            private boolean CheckPawn(Piece p,Piece k,int col,int row){                    
                 return p!=null && !board.sameTeam(p, k) &&p.name.equals("Pawn")&& !(p.cols==col && p.rows==row);
            }
}

