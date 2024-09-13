public class Knight {

    /**
     * Constructor.
     *
     * @param row     The current row of the pawn.
     * @param col     The current column of the pawn.
     * @param isBlack The color of the pawn.
     */
    public Knight(int row, int col, boolean isBlack) {
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
        if (Math.abs(endRow - this.row) == 1 && (Math.abs(endCol - this.col) == 2)) {
            // Case 1: Movement in the +/- 2X, +/- Y direction (one square up/down, two squares over).
            if (board.getPiece(endRow, endCol) == null) {
                return true;
            } else {
                return board.getPiece(endRow, endCol).getIsBlack() != this.isBlack; // Returns true if pieces differ in color, false otherwise.
            }
        } else if (Math.abs(endRow - this.row) == 2 && (Math.abs(endCol - this.col) == 1 && board.getPiece(endRow, endCol) == null)){
            // Case 2: Movement in the +/- X, +/- 2Y direction (two squares up/down, one square over).
            if (board.getPiece(endRow, endCol) == null) {
                return true;
            } else {
                return board.getPiece(endRow, endCol).getIsBlack() != this.isBlack; // Returns true if pieces differ in color, false otherwise.
            }
        } else {
            // Case 3: Other illegal movements.
            return false;
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;

}