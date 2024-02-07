
package chesscore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel{
//    public boolean turn = true;
    int flag = 1;
    private choosePromote window;
    public int titlesize=85;
    int cols=8;
    int rows=8;
    public Piece selectedPiece;
    Input input = new Input(this);
    public int enpassantTile = -1;
    public CheckScanner checkScanner = new CheckScanner(this);
    
    ArrayList<Piece> listOfPieces=new ArrayList<>();
    public Board(){
        this.setPreferredSize(new Dimension(cols*titlesize,rows*titlesize));
        AddPieace();
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }
    
    public int getTileNum(int col, int row)
    {
        return row*rows+col;
    }
    
    public Piece getPiece(int col , int row)
    {
        for(Piece piece:listOfPieces)
        {
            if(piece.cols == col && piece.rows == row)
            {
                return piece;
            }
        }
        return null;
    }
    
   
    
    public void AddPieace(){
        
        // For black pieces:
        listOfPieces.add(new Knight(this,1,0,false)); // second one is the cols where the piece in
        listOfPieces.add(new Knight(this,6,0,false));
        listOfPieces.add(new Pawn(this,0,1,false));
        listOfPieces.add(new Pawn(this,1,1,false));
        listOfPieces.add(new Pawn(this,2,1,false));
        listOfPieces.add(new Pawn(this,3,1,false));
        listOfPieces.add(new Pawn(this,4,1,false));
        listOfPieces.add(new Pawn(this,5,1,false));
        listOfPieces.add(new Pawn(this,6,1,false));
        listOfPieces.add(new Pawn(this,7,1,false));
        listOfPieces.add(new Bishop(this, 2, 0, false));
        listOfPieces.add(new Bishop(this, 5, 0, false));
        listOfPieces.add(new Rook(this, 0, 0, false));
        listOfPieces.add(new Rook(this, 7, 0, false));
        listOfPieces.add(new King(this, 4, 0, false));
        listOfPieces.add(new Queen(this, 3, 0, false));

        
        
       //For white Pieces
        listOfPieces.add(new Knight(this,1,7,true));
        listOfPieces.add(new Knight(this,6,7,true));
        listOfPieces.add(new Pawn(this,0,6,true));
        listOfPieces.add(new Pawn(this,1,6,true));
        listOfPieces.add(new Pawn(this,2,6,true));
        listOfPieces.add(new Pawn(this,3,6,true));
        listOfPieces.add(new Pawn(this,4,6,true));
        listOfPieces.add(new Pawn(this,5,6,true));
        listOfPieces.add(new Pawn(this,6,6,true));
        listOfPieces.add(new Pawn(this,7,6,true));
        listOfPieces.add(new Bishop(this, 2, 7, true));
        listOfPieces.add(new Bishop(this, 5, 7, true));
        listOfPieces.add(new Rook(this, 0, 7, true));
        listOfPieces.add(new Rook(this, 7, 7, true));
         listOfPieces.add(new King(this, 4, 7, true));
        listOfPieces.add(new Queen(this, 3, 7, true));
        
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){


            g2d.setColor((j+i)%2==0 ? new Color(227,227,227) : new Color(157,105,57));
            g2d.fillRect(j*titlesize, i*titlesize, titlesize, titlesize);
            }
        }
        if(selectedPiece != null)
        {
            for(int i = 0 ; i < rows ; i++)
                for(int j = 0 ; j < cols ; j++)
                    if(isValidMove(new Move(this,selectedPiece,j,i)))
                    {
                        g2d.setColor(new Color(68,180,57,190));
                        g2d.fillRect(j*titlesize, i*titlesize, titlesize, titlesize);
                    }

        }        
        for(Piece piece : listOfPieces){
            piece.paint(g2d);
        }
        
    }
    
    

    
    public void makeMove(Move move)
    {

        //        turn = !turn;
                if(move.piece.name.equals("Pawn"))
                {
                    movePawn(move);
                }
                else if(move.piece.name.equals("King"))
                {
                    MoveKing(move);
                }
                    move.piece.cols = move.newcol;
                    move.piece.rows = move.newrow;
                    move.piece.xPos = move.newcol * titlesize; 
                    move.piece.yPos = move.newrow * titlesize;

                    move.piece.isFirstMove = false;

                    capture(move.capture);
                    if(move.capture != null)
                    {
                        if(move.capture.name.equals("King"))
                        {
                            if(move.capture.isWhite)
                            {
                                System.out.println("Black won");
                                flag = 0;
                            }else
                            {
                                System.out.println("White won");
                                flag = 0;
                            }
                        }else
                        {
                            System.out.println("captured "+move.capture.name);
                        }
                    }

                
                
            }

    
    
    private void movePawn(Move move) 
    {
        // en passant
        int colorIndex = move.piece.isWhite ? 1 : -1;
        if(getTileNum(move.newcol, move.newrow) == enpassantTile)
        {
            move.capture = getPiece(move.newcol, move.newrow+colorIndex);
        }
        if(Math.abs(move.piece.rows - move.newrow) == 2 )
        {
            enpassantTile = getTileNum(move.newcol, move.newrow+colorIndex);
        }
        else
        {
            enpassantTile = -1;
        }
        //promotions
        colorIndex = move.piece.isWhite ? 0: 7;
        if(move.newrow == colorIndex)
        {
            promotePawn(move);
        }
       
        if(move.capture != null)
            System.out.println("captured "+move.capture.name);
    }
    
    private void promotePawn(Move move) 
    {
        window = new choosePromote(this,move); 
        window.setVisible(true);

    }

        
    public void capture(Piece piece)
    {
        
        listOfPieces.remove(piece);
    }
    
    public boolean isValidMove(Move move)
    {
        if(flag == 1)
        {
            if(checkScanner.isKingChecked(move))
            {
                //System.out.println("king check");
                return false;
            }
            if(sameTeam(move.piece,move.capture))
            {
                System.out.println("same team");
                return false;
            }
            if(!move.piece.isValidMovement(move.newcol, move.newrow))
            {
                return false;
            }
            if(move.piece.moveCollideWithPiece(move.newcol, move.newrow))
            {
                System.out.println("collide with another piece");
                return false;
            }
            
            return true;
        }else
        {
            System.out.println("Game finished");
            return false;
        }
    }
    
    
    public boolean sameTeam(Piece p1 , Piece p2)
    {
        if(p1 == null || p2 == null)
        {
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }
    private void MoveKing(Move move){
       if(Math.abs(move.piece.cols-move.newcol)==2){
           System.out.println("castlemove");
           Piece rook;
           if(move.piece.cols<move.newcol){
               rook=getPiece(7, move.piece.rows);
               rook.cols=5;
           } else{
               rook=getPiece(0, move.piece.rows);
               rook.cols=3;
           }
           rook.xPos=rook.cols*titlesize;
       } 
    }
    public Piece findKing(boolean isWhite)
    {
        for(Piece piece : listOfPieces)
            if(isWhite == piece.isWhite && piece.name.equals("King"))
            {
                return piece;
            }
        return null;
    }



    
}
