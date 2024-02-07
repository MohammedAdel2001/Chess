
package chesscore;
 
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    public Board board;
    public int cols,rows;
    public int xPos,yPos;
    
    public boolean isWhite;
    public String name;
    public int value;
    public boolean isFirstMove = true;
    
    
    BufferedImage pic;
    {
        try{
            pic=ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
            
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    protected int picScale=pic.getWidth()/6;
    Image sprite;
    public Piece(Board board){
        this.board=board;
    }
    
    

    void paint(Graphics2D g2d) {
    g2d.drawImage(sprite, xPos, yPos,null );
       }
    
    
    public abstract boolean isValidMovement(int col,int row);
    
    public boolean moveCollideWithPiece(int col,int row)
    {
        return false;
    }

   
    
}
