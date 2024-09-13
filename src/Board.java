public class Board {

    // Instance variables
    private Piece[][] board;


    //Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
    }

    // Accessor Methods


    // Return the Piece object stored at a given row and column.
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }


    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods


    //Moves the user's piece from one square of the board to another, if the move is legal.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            board[endRow][endCol] = board[startRow][startCol];
            this.board[startRow][startCol].row = endRow;
            this.board[startRow][startCol].col = endCol;
            setPiece(startRow, startCol, null);
            System.out.println("successful movePiece");
            return true;
        }
        System.out.println("failed movePiece");
        return false;
    }


    // Determines whether the game has been ended, if one player's King has been taken.
    public boolean isGameOver() {
        boolean whiteKing = false;
        boolean blackKing = false;

        // check to see if there is a white and black king keep track of kings with variable - return if both kings are not null
        for(int i = 0; i < board.length; i ++){
           for(int j = 0; j < board[i].length; j ++) {
               if (board[i][j] != null) {
                   if(board[i][j].character == '\u2654'){
                       whiteKing = true;
                   }
                   if(board[i][j].character == '\u265a') {
                       blackKing = true;
                   }
               }
           }
        }
        return !blackKing || !whiteKing; // game is not over

    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //Sets every piece on the board to null
    public void clear() {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                setPiece(i, j, null);
            }
        }
    }

    // Movement helper functions


    //Makes sure that the player's move is legal.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if(startRow >= 8 || startCol >= 8 || endRow >= 8 || endCol >= 8) {
            return false;
        }
        if(startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0) {
            return false;
        }
        if(board[startRow][startCol] == null) {
            return false;
        }
        if(isBlack != board[startRow][startCol].getIsBlack()){
            return false;
        }
        if(board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack){
            return true;
        }
        return false;
    }


    //Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(endRow - startRow) <= 1 && Math.abs(endCol - startCol) <= 1) {
            return true;
        }
        return false;
    }



    //Checks to see if start and end position are a legal horizontal move.
    //Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            if (endCol > startCol) {
                for (int k = startCol+1; k < endCol; k++) {
                    if (board[startRow][k] != null) {
                        return false;
                    }
                }
            } else if (endCol < startCol) {
                for (int n = startCol - 1; n > endCol; n--) {
                    if (board[startRow][n] != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // Checks to see if the given start and end positions are validly moving vertical
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if(startCol == endCol) {
            if(endRow > startRow) {
                for (int i = startRow + 1; i < endRow ; i++) {
                    if (board[i][endCol] != null) {
                        return false;
                    }
                }
            } else if (endRow < startRow) {
                for (int j = startRow - 1; j > endRow ; j--) {
                    if (board[j][startCol] != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //Checks whether a given start and end position are a valid diagonal moves, returns a boolean whether:
    // the path from start to end is diagonal and if all the spaces directly between start and end are empty
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int tempRow = startRow;
        int tempCol = startCol;

        if(Math.abs(startRow - endRow) == Math.abs(endCol - startCol)){

            for(int i = Math.min(startRow, endRow)+1 ;i < Math.max(startRow, endRow) ; i++ ){
                if(tempRow == endRow && tempCol == endCol){
                    break;
                }
                else if(tempRow < endRow && tempCol < endCol){
                    tempRow ++;
                    tempCol++;
                }
                else if(tempRow < endRow && tempCol > endCol){
                    tempRow ++;
                    tempCol --;
                }
                else if(tempRow > endRow && tempCol > endCol){
                    tempRow --;
                    tempCol --;
                }
                else{
                    tempRow --;
                    tempCol ++;
                }
                if(board[tempRow][tempCol] != null){
                    return false;
                }

            }
return true;
        }
        return false;

    }

}
