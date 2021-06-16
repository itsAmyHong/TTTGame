package ultttt.ultimatettt;
/*
    Amy Hong
    Section 0W2
*/
/*  -- Analysis --
    Board class: this class is the basic tic tac toe board with 9 squares that
    stores and returns data of a board within the ultimate board.

    -- Design --
    As the child class to TTTBoard, a board inherits 
    all aspects relating to the Tic-tac-toe game. However, as
    a board of another board it requires a board number and 
    methods isFull and printRow. 
*/
public class Board extends TTTBoard{
    
    private int boardNumber;
    
    /*  Board class constructor: inherits inputs of rows and columns
        and adds input board number 
    */
    public Board(int number, int rows, int cols){
        super(rows, cols);
        boardNumber = number;
    }
    
    // Board number getter
    int getBoardNumber(){
        return boardNumber;
    }
    
    // isFull: returns true if board is full
    boolean isFull() {
        for(int i=0; i<9; i++){
                if(getMark(i).equals("-") || getMark(i).equals("+")){
                    return false;
                }
        }
        return true;
    }
    
    /* setWinner: sets winner like parent method, but
       changes - to + to show board is won
    */
    @Override
    void setWinner(String mark){
        super.setWinner(mark);
        for(int i=0; i<9; i++){
            if(getMark(i).equals("-")){
                setMark(i, "+");
            }
        }
    }

    /* printRow: prints one specified row of the board.
       This method is specifically for printing ultimate board.  
    */
    String printRow(int rowNum){
        String output = "";
        int start = rowNum*3;
        for(int i=start; i<start+3; i++){
            output += getMark(i) + " ";
        }
        return output;
    }
}
