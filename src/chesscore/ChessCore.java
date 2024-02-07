
package chesscore;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Lenovo
 */
public  class ChessCore {
    public static void moving(Move move,Board board)
    {
        if(board.isValidMove(move))
        {
//            System.out.println("done");
            board.makeMove(move);
        }
    }
    
    public static int Switch(char c)
    {
        int col = -1;
        switch(c) {
                case 'a':
                  col = 0;      
                  break;
                case 'b':
                  col = 1;
                  break;
                case 'c':
                    col=2;
                    break;
                case 'd':
                    col=3;
                    break;
                case 'e':
                    col = 4;
                    break;
                case 'f':
                  col = 5;
                  break;
                case 'g':
                    col = 6;
                    break;
                case 'h':
                    col = 7;
                    
                    
        
    }
        return col;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Start s = new Start();
        String data[];

        File f = new File("Input.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine())
        {
            data = sc.nextLine().split(",");
            int row1 = 8-(data[0].charAt(1)-'0');
            int row2 = 8-(data[1].charAt(1)-'0');
            int col1 = Switch(data[0].charAt(0));
            int col2 = Switch(data[1].charAt(0));
//            System.out.println(col1+","+row1+","+col2+","+row2); 
            if(s.board.getPiece(col1, row1) != null)
            {
                Move move = new Move(s.board,s.board.getPiece(col1, row1),col2,row2);
                moving(move,s.board);
                
            }
               
        }
        
        

        

    }
    
}
