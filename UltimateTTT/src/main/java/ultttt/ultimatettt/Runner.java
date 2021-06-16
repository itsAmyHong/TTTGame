package ultttt.ultimatettt;
import java.util.Scanner;
/*
    Amy Hong
    Section 0W2
*/
/* -- Analysis --
   This class initializes the game class and continues 
   running games until the user requests to stop playing again.

   -- Design --
   Since boards and squares aren't numbered, a numbered
   illustration is given at the start to aid a new user. Then 
   a do-while loop keeps playing games until boolean playing is false.
*/
public class Runner {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        boolean playing = true;
        
        boolean computer = true;
        
        System.out.println("Welcome to Ultimate Tic Tac Toe!");
        System.out.println("\t  0 1 2 \n\t  3 4 5 \n\t  6 7 8");
        System.out.println("================================");
        
        do{
            Game game = new Game(computer);
            game.start();
                    
            System.out.println("Do you want to play again? Y/N");
            String answer = scan.next();
            if(answer.equals("Y") || answer.equals("y")){
                System.out.println("Great! Let's play another game!");
            } else{
                System.out.println("Thanks for playing!");
                playing = false;
            }
        } while(playing);
        
    }
    
}
