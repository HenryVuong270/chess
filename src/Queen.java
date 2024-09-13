public class Queen {

    /**
     * Constructor.
     *
     * @param row     The current row of the pawn.
     * @param col     The current column of the pawn.
     * @param isBlack The color of the pawn.
     */
    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     *
     * @param board  The game board.
     * @param endRow The row of the destination square.
     * @param endCol The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyVertical(this.row, this.col, endRow, endCol)){
            // Case 1: Vertical movement checks.
            if (board.getPiece(endRow, endCol) == null){
                return true;
            } else {
                return board.getPiece(endRow, endCol).getIsBlack() != this.isBlack; // Compares target and capturing piece's colors. If they do not match, returns true.
            }
        } else if (board.verifyHorizontal(this.row, this.col, endRow, endCol)){
            //Case 2: Horizontal movement checks. Includes checks for both empty space logic, and capture logic.
            if (board.getPiece(endRow, endCol) == null){
                return true;
            } else {
                return board.getPiece(endRow, endCol).getIsBlack() != this.isBlack; // Compares target and capturing piece's colors. If they do not match, returns true.
            }
        } else if (board.verifyDiagonal(this.row, this.col, endRow, endCol)){
            // Case 3: Diagonal movement. Checks that the movement is diagonal only, then proceeds.
            if (board.getPiece(endRow, endCol) == null) { // If the board piece is null (empty), move.
                return true;
            } else { // If the ending location is not empty, follow capture logic.
                return board.getPiece(endRow, endCol).getIsBlack() != this.isBlack; // Compares target and capturing piece's colors. If they do not match, returns true.
            }
        } else {
            // Case 4: Other illegal movements.
            return false;
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;

}