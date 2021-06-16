package ultttt.ultimatettt;
/*
    Amy Hong
    Section 0W2
*/
/* -- Analysis --
   TTTBoard class contains all functions shared by Ultimate Board and 
   Board. This prevents repeat functions such as checking for win, which
   both the ultimate board and smaller boards do the same way.
   
   -- Design -- 
   This class is the parent class to Board and UltimateBoard.
   Both boards have similarities regarding the tic tac toe game that can be 
   inherited such as what is considered a win, the rows and columns, and 
   the marks and squares. 
*/
public class TTTBoard {
    private int numRows;
    private int numCols;
    
    private String winnerMark = "";
    private boolean hasWinner = false;
    
    // Every board and it's marks are stored and printed as a 2D array.
    private String squares[][];
    
    /* TTTBoard constructor: every TTTBoard takes in number of rows and columns,
       initializes the squares array, and resets squares upon creation.
    */
    public TTTBoard(int numRows, int numCols){
        this.numRows = numRows;
        this.numCols = numCols;  
        squares = new String[numCols][numRows];
        resetSquares();
    }
    
    // resetSquares: initializes every element in squares array to "-"
    void resetSquares() {
        for(int row=0; row<getNumRows(); row++){
            for(int col=0; col<getNumCols(); col++){
                squares[row][col] = "-";
            }
        }
    }
    
    // setMark: takes in square number and mark and updates mark accordingly
    void setMark(int squareNum, String mark){
        int row = getSquareRow(squareNum);
        int col = getSquareCol(squareNum);
        squares[col][row] = mark;
    }
    
    // hasWinner: returns boolean hasWinner, showing if there's a winner.
    boolean hasWinner(){
        return hasWinner;
    }
    
    // setWinner: sets hasWinner to true and sets winnerMark to input
    void setWinner(String mark){
        winnerMark = mark;
        hasWinner = true;
    }
    
    // getNumRows: returns number of rows
    int getNumRows(){
        return numRows;
    }
    
    // getNumCols: returns number of columns
    int getNumCols(){
        return numCols;
    }
    
    // getSquareRow: given a it's number, this method returns square's row
    int getSquareRow(int x){
        if(x < 3)
            return 0;
        else if (x < 6)
            return 1;
        else {
            return 2;
        }            
    }
    
    // getSquareRow: given it's number, this method returns square's column
    int getSquareCol(int x){
        switch (x) {
            case 1:
            case 4:
            case 7:
                return 1;
            case 2:
            case 5: 
            case 8:
                return 2;
            default:
                return 0;
        }
    }
    
    // getMark: takes in square number and returns the mark at that given square
    String getMark(int squareNum){
        int row = getSquareRow(squareNum);
        int col = getSquareCol(squareNum);
        return squares[col][row];
    } 
    
    // getSquares: returns squares array
    String[][] getSquares(){
        return squares;
    }
    
    // isEmpty: returns if any given square is empty
    boolean isEmpty(int square){
        return (getMark(square).equals("-") || getMark(square).equals("+"));
    }
    
    // getBoard: converts squares array into a string
    public String getBoard(){
        String printString = "";
        for(int row=0; row<numRows; row++){
            for(int column=0; column<numCols; column++){
                printString += squares[column][row] +" ";
            }
            printString += "\n";
        }
        return printString;
    }
    
    /* getWinStatus: takes in player, row, and column of a given move,
       and determines if the board of that move has a win or not. The 
       method returns 1 if the player has won, 0 for a tie, and -1 if
       no win can be found on the board. 
    */
    public int getWinStatus(Player player, int row, int col){
        String mark = player.getMark();
        String board = getBoard();
        
        // check if game is a tie
        if(board.indexOf('-') == -1){
            return 0;
        } 
        
        // check columns for win
        for(int i=0; i<3; i++){
            if(!squares[col][i].equals(mark)){
                break;
            }
            if (i==2){
                return 1;
            }
        }
        
        // check rows for win
        for(int i=0; i<3; i++){
            if(!squares[i][row].equals(mark)){
                break;
            }
            if (i==2){
                return 1;
            }
        }
        
        // check left diagnals for win
        if(row == col){
            for(int i=0; i<3; i++){
                if(!squares[i][i].equals(mark)){
                    break;
                }
                if(i == 2){
                    return 1;
                }
            }  
        }
        
        // check right diagnol for win
        if(row + col == 2){
            for(int i=0; i<3; i++){
                if(!squares[i][2-i].equals(mark)){
                    break;
                }
                if(i == 2){
                    return 1;
                }
            }  
        }
        return -1;
    }
    
}
