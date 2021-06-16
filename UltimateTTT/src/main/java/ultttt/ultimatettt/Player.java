package ultttt.ultimatettt;
import java.util.Random;
/*
    Amy Hong
    Section 0W2
*/
/* -- Analysis --
   Player class defines the Player object, which stores and returns
   data about the player playing the game.

   -- Design --
   Every player has a name and a mark which the game 
   uses to address which player is moving and update the board with 
   the correct marks. This class also has a findMove function used 
   by any computer player.
*/

public class Player {
    
    private String name;
    private String mark;
    
    // Constructor
    public Player(String name, String mark){
        this.name = name;
        this.mark = mark;
    }
    
    // Getters
    String getName(){
        return name;
    }
    
    String getMark(){
        return mark;
    }
    
    // Computer's method to find a move to make. Returns square number.
    int findMove(Board board){
        Random rand = new Random();
        String[][] squares = board.getSquares();
        
        if(board.isEmpty(4)){
            return 4;
        }
        int [] random = {0, 2, 6, 8};
        if(board.isEmpty(0) && board.isEmpty(2) && board.isEmpty(6) && board.isEmpty(8)){
            return random[rand.nextInt(4)];
        } 
        while(true){
            int num = rand.nextInt(9);
            if(board.isEmpty(num)){
                return num;
            }
        }
    } 
}
