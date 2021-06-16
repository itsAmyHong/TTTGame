package ultttt.ultimatettt;
/*
    Amy Hong
    Section 0W2
*/
/* -- Analysis --
   Ultimate Board is a type of TTTBoard. This class is used to store 
   and return data about the bigger board of the game.

   -- Design --
   While inheriting qualities of a TTTBoard, Ultimate Board also 
   has a 2D array of Boards. Each time a player wins on a board, that
   win is stored in the Ultimate Board's 2D array of squares inherited 
   from TTTBoard. 
*/
public class UltimateBoard extends TTTBoard{
    private Board[][] boards;
    
    /* UltimateBoard constructor: takes in rows and columns like parent class, 
       initializes board array, and calls setBoards. 
    */
    public UltimateBoard(int rows, int cols){
        super(rows, cols);
        boards = new Board[cols][rows];   
        setBoards();
    }
    
    // setBoards: initializes all boards in Ultimate board
    void setBoards() {
        int number = 0;
        
        for(int i=0; i<getNumRows(); i++){
            for(int j=0; j<getNumCols(); j++){
                Board b = new Board(number, 3, 3);
                boards[i][j] = b;
                number++;
            }
        }
    }
    
    // findboard: takes a board number and returns the board with that number 
    public Board findBoard(int boardNumber){
        int count = 0;
        for(int i=0; i<getNumRows(); i++){
            for(int j=0; j<getNumCols(); j++){
                if(count == boardNumber){
                    return boards[i][j];
                } else {
                    count++;   
                }
            }
        }
        return boards[0][0];   
    }
    
    // checkForTie: checks if there are no more boards to win 
    public boolean checkForTie(){
        for(int i=0; i<9; i++){
            if(!findBoard(i).hasWinner()){
                return false;
            }
        }
        return true;
    }
    
    // print: prints the ultimate board row by row 
    public String print(){
        int start = 0;
        String output = "\n";
        
        for(int i=0; i<getNumRows(); i++){
            for(int j=0; j<getNumRows(); j++){
                for(int k=0; k<getNumRows(); k++){ 
                    output += findBoard(start+k).printRow(j) + "   ";
                }
                output += "\n";
            }
            output += "\n";
            start+=3;
        }
        return output; 
    }     
}
