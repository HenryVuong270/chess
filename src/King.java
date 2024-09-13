public class King {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyAdjacent(this.row, this.col, endRow, endCol)){
            // Case 1: Adjacent movement to empty squares. Checks if the "target" (ending row/column) is empty, and that the movement is within one space.
            if (board.getPiece(endRow, endCol) == null){
                return true;
            } else {
                return board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
        } else {
            // Case 2: Other illegal movements.
            return false;
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;

}