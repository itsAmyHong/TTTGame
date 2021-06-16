package ultttt.ultimatettt;
import java.util.*;
/*
    Amy Hong
    Section 0W2
*/
/*  -- Analysis --
    The game class oversees all game functions and where objects of the
    game and the user interact to form the tic tac toe game. 

    -- Design --
    When a game begins, all parts tic tac toe come together here.
    The players, the ultimate board, and the computer are all initialized
    in the beginning here. The game itself checks for wins, updates the board,
    and prints instructions for the user. 
*/
public class Game {
    
    // Settings: not made to be adjusted
    private int numRows = 3;
    private int numCols = 3;
    private int numPlayers = 2;
    private String[] marks = {"x", "o"};
    
    private Player[] players = new Player[numPlayers];
    private UltimateBoard ultBoard = new UltimateBoard(numRows, numCols);
    
    // startboard is the board number which player is restricted to
    private int startBoard;
    private int currentPlayerIndex = -1;
    
    
    /* Game constructor: takes in true or false if user is playing 
       against a computer. This allows for easy switching between
       2 player games and 1 player games. It also makes testing easier.
    */
    public Game(boolean computer){
        if(computer){
            Player p = new Player("Player", marks[0]);
            players[0] = p;
            Player c = new Player("Computer", marks[1]);
            players[1] = c;
        }else{
            setPlayers();
        }
    }
    
    /* setPlayers: is not used when computer is true. This
       method initializes players of 2 or more player games. 
    */
    void setPlayers() {
        for(int i=0; i<players.length; i++){
            Player p = new Player("Player "+(i+1), marks[i]);
            players[i] = p;
        }      
    }
    
    /* getValidBoardNumber: retreives user input and validates 
       the input, making sure inputs that are invalid don't crash
       the program.
    */
    int getValidBoardNumber(){
        Scanner scan = new Scanner(System.in);
        try{
            int b = scan.nextInt();
            if(b >= 0 && b < 9){
                return b;
            } else{
                System.out.println("Boards only range from 0 to 8. Try again!");
                return getValidBoardNumber();
            }
        } catch (Exception e){
            System.out.println("Invalid input. Try again!");
            return getValidBoardNumber();
        }
    }
    
    /* start: responsible for overseeing the game by both instructing the 
       user and calling methods needed at specific times of the game.
    */
    public void start(){
        System.out.println("New game started...");
        
        System.out.println("Please select starting board: ");
        Scanner scan = new Scanner(System.in);
        startBoard = getValidBoardNumber();
        
        while(true){
            for(int i=0; i<numPlayers; i++){
                System.out.println(ultBoard.print());
                switchCurrentPlayer();
                String playerName =  players[currentPlayerIndex].getName();
                
                if(playerName.equals("Computer")){
                    while (ultBoard.findBoard(startBoard).isFull()){
                        Random rand = new Random();
                        startBoard = rand.nextInt(9);
                        System.out.println("Computer chose to move on board "+startBoard);
                    } 
                    move(players[currentPlayerIndex]);  
                } else {
                    if(ultBoard.findBoard(startBoard).isFull()){
                        System.out.println(playerName +", choose a new board");
                        startBoard = getValidBoardNumber();
                    } 
                    System.out.println(playerName + ", it is your turn! You must move on board "+ startBoard);
                    System.out.println(playerName +", where would you like to move? ");
                    move(players[currentPlayerIndex]);
                }
                // infinite loop until ultBoard has winner breaks it
                if(ultBoard.hasWinner()){
                    System.out.println(ultBoard.print());
                    break;
                } 
            }
            if(ultBoard.hasWinner()){
                break;
            }
        } 
    }
    
    /* move: takes in Player and user input and updates baords
       accordingly. If player is a computer, findMove is called.
    */
    void move(Player player) {  
        Scanner scan = new Scanner(System.in);
        Board board = ultBoard.findBoard(startBoard);
        int boxNum;      
        if(player.getName().equals("Computer")){
            boxNum = player.findMove(board);
            System.out.println("Computer chose square "+ boxNum);
        }
        else {
            // do-while checks move is valid and updates squares
            do{
                boxNum = getValidBoardNumber();
                if(!board.getMark(boxNum).equals("x") && !board.getMark(boxNum).equals("o")){
                    break;
                } else{
                    System.out.println("Move taken. Please choose another!");
                }
            } while(true);
        }
        
        // Per move, the game checks if any player has won
        board.setMark(boxNum, player.getMark()); 
        checkForWin(player, board, boxNum);
        startBoard = boxNum;
    }
    
    /* checkForWin: takes in player, board, and square which last'
       move was made. This method looks for wins on the board and 
       the ultimate board and sets winners accordingly.
    */
    void checkForWin(Player player, Board board, int square){
        int row = board.getSquareRow(square);
        int col = board.getSquareCol(square);
        int ultRow = ultBoard.getSquareRow(board.getBoardNumber());
        int ultCol = ultBoard.getSquareCol(board.getBoardNumber());
        
        if(!board.hasWinner()){
            int winStatus = board.getWinStatus(player, row, col);
            if(winStatus == 1){
                board.setWinner(player.getMark());
                ultBoard.setMark(board.getBoardNumber(), player.getMark());
                System.out.println(player.getName() +" has won Board "+ startBoard);
                
                // If a player wins on a board, the game checks for ultBoard win
                int ultWinStatus = ultBoard.getWinStatus(player, ultRow, ultCol);
                if(ultWinStatus == 1){
                    System.out.println(player.getName() +" has won the Game! ");
                    ultBoard.setWinner(player.getMark());
                } else if (ultWinStatus == 0 || ultBoard.checkForTie()){
                    System.out.println("Game is a tie!");
                    ultBoard.setWinner("");
                }
            } else if(winStatus == 0){
                System.out.println("Board "+ startBoard +" is a tie!");
            }    
        }
    }
    
    // switchCurrentPlayer: toggles player turns
    void switchCurrentPlayer() {
        if(currentPlayerIndex == -1){
            currentPlayerIndex = 0;
        } else if(currentPlayerIndex == 0){
            currentPlayerIndex = 1;
        } else{
            currentPlayerIndex = 0;
        }
    }
}
