import java.util.Scanner;
/**
 * Amy Hong
 * CS2336.S21
 */
public class TTTBoard
{
    public static void main(String args[]){
        boolean running = true;
        int rowNumberX; int colNumberX;
        int rowNumberY; int colNumberY ;
        char playerX = 'X'; char playerY = 'Y';
        
        char [][] board = new char[3][3];
        
        // Program will continue running until user decides not to play again
        while(running){ 
            System.out.println("Printing board info... ");
            emptyBoard(board);
            System.out.println(printBoard(board));
            
            // Rounds will repeat until winCondition is met
            while(true) {
                // Game asks Player X for board space until an empty one is chosen
                do {    
                    System.out.println("Player "+ playerX +", Please select a row number (0-2): ");
                    rowNumberX = getValidInput();
                    System.out.println("Player " + playerX + ", Please select a column number (0-2): ");
                    colNumberX = getValidInput();
                } while (!isEmptySpace(board, rowNumberX, colNumberX));
                System.out.println("Printing board info... ");
                move(board, playerX , rowNumberX, colNumberX);
                System.out.println(printBoard(board));
                // if winCondition is met then round ends and the infinite while-loop is terminated
                if(checkWinStatus(board, playerX, rowNumberX, colNumberX)){
                    break;
                }
                
                // Game asks Player Y for board space until an empty one is chosen
                do {
                    System.out.println("Player "+ playerY +", Please select a row number (0-2): ");
                    rowNumberY = getValidInput();
                    System.out.println("Player "+ playerY +", Please select a column number (0-2): ");
                    colNumberY = getValidInput();
                } while(!isEmptySpace(board, rowNumberY, colNumberY));
                System.out.println("Printing board info... ");
                move(board, playerY, rowNumberY, colNumberY);
                System.out.println(printBoard(board));
                // if winCondition is met then round ends and the infinite while-loop is terminated
                if (checkWinStatus(board, playerY, rowNumberY, colNumberY)){
                    break;
                }
            }
            System.out.println("Would you like to play again? (Y/N): ");
            running = runningStatus();
        }
        System.out.println("Thanks for playing!");
    }
    
    /*  -- Method runningStatus --
    This method gets a valid user input and returns
    if the user wants to play again or not 
    Input: none
    Output: true/false
    */
    public static boolean runningStatus(){
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        boolean validAnswer = false;
        while(!validAnswer){
            if(answer.equals("N")|| answer.equals("n")){
                validAnswer = true;
                return false;
            } else if (answer.equals("Y") || answer.equals("y")){
                validAnswer = true;
                return true;
            } else{
                System.out.println("Would you like to play again? (Y/N): ");
                return runningStatus();
            }
        }
        return false;
    }
        
    /*  -- Method emptyBoard --
    This method changes all board elements to "-" 
    Input: board
    Output: none
    */
    public static void emptyBoard(char [][] board){
        for(int row=0; row<board.length; row++){
            for(int column=0; column<board.length; column++){
                board[column][row] = '-';
            }
        }
    }
    
    /*  -- Method getValidInput --
    This method does not return a value to main
    until the user enters a valid input value
    Input: none
    Output: int input (integers from 0-2)
    */
    public static int getValidInput(){
        Scanner scan = new Scanner(System.in);
        int input = 0;
        try {
            input = scan.nextInt();
            if(input <= 2 && input >= 0){
                return input; 
            } 
            else {
                System.out.println("Invalid input (row = 1, column = 1) \nPlease try again!");
                return getValidInput();
            }
        }
        catch (Exception e){
            System.out.println("Invalid input (row = 1, column = 1) \nPlease try again!"); 
            return getValidInput();
        }
    }
    
    /*  -- Method move --
    This method updates the Board with player's current move
    Input: board, player, row, column 
    Output: none
    */
    public static void move(char [][] board, char player, int row, int column){
        char changeTo;
        if(player == 'X'){
            changeTo = 'X';
        }
        else {
            changeTo = 'O';
        }
        
        board[column][row] = changeTo;
    }
    
    /*  -- Method isEmptySpace --
    This method returns true if space entered is empty
    Input: board, row, col
    Output: true/false
    */
    public static boolean isEmptySpace(char [][] board, int row, int col){
        if(board[col][row] != '-'){
            System.out.println("This space has been taken \nPlease select another one!");
            return false;
        } else{
            return true;
        }
    }
    
    /*  -- Method printBoard --
    This method returns a string version of
    the board 
    Input: board
    Output: printString (string version of board array)
    */
    public static String printBoard(char[][] board){
        String printString = "";
        for(int row=0; row<board.length; row++){
            for(int column=0; column<board.length; column++){
                printString += board[column][row] +" ";
            }
            printString += "\n";
        }
        return printString;
    }
    
    /*  -- Method checkWinStatus --
    This method checks for wins by inputted player.
    If the player has won, it returns true and a win message.
    Input: board, player, moveRow, moveCol  
    (moveRow/moveCol indicate player's current move position)
    Output: true/false
    */
    public static boolean checkWinStatus(char [][] board, char player, int moveRow, int moveCol){
        // Sets boardPiece to player's corresponding piece X or O
        char boardPiece = 'X';
        if(player == 'Y'){
            boardPiece = 'O';
        }
        
        // Checks for if board is full/ game is a tie
        String printedBoard = printBoard(board);
        if(!printedBoard.contains("-")){
            System.out.println("This game is a tie!");
            return true;
        } 
        
        // Checks for three in a row or three in a column
        for(int i=0; i<3; i++){
            if(board[moveCol][i] != boardPiece){
                break;
            }
            if (i==2){
                System.out.println("Player "+ player+ " won the game!");
                return true;
            }
        }
        for(int i=0; i<3; i++){
            if(board[i][moveRow] != boardPiece){
                break;
            }
            if (i==2){
                System.out.println("Player "+ player+ " won the game!");
                return true;
            }
        }
        
        // Checks both diagnals for a win
        if(moveRow == moveCol){
            for(int i=0; i<3; i++){
                if(board[i][i] != boardPiece){
                    break;
                }
                if(i == 2){
                    System.out.println("Player "+ player+ " won the game!");
                    return true;
                }
            }  
        }
        if(moveRow + moveCol == 2){
            for(int i=0; i<3; i++){
                if(board[i][2-i] != boardPiece){
                    break;
                }
                if(i == 2){
                    System.out.println("Player "+ player+ " won the game!");
                    return true;
                }
            }  
        }
        return false;
    }
}
