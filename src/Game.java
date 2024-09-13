import java.util.Scanner;
public class Game {
    public static void main(String[] args){
        int startRow = 0;
        int startCol = 0;
        int endRow = 0;
        int endCol = 0;
        boolean whiteTurn = false;
        Board board = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board); //correct: "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
        System.out.print(board);
        while(!board.isGameOver()){ // Never ending loop— just testing movement logic.
            if(whiteTurn) {
                System.out.println("It is currently black's turn to move");
            } else {
                System.out.println("It is currently white's turn to move");
            }
            System.out.println("Move a piece. Format: `startRow startCol endRow endCol` (space sensitive!): ");
            Scanner loopingScanner = new Scanner(System.in);
            String selection = loopingScanner.nextLine();
            String[] splitScanner = selection.split(" ");
            startRow = Integer.parseInt(splitScanner[0]);
            startCol = Integer.parseInt(splitScanner[1]);
            endRow = Integer.parseInt(splitScanner[2]);
            endCol = Integer.parseInt(splitScanner[3]);
            board.movePiece(startRow, startCol, endRow, endCol);
            //whiteTurn = !whiteTurn; // Swaps turn.
            System.out.print(board);
        }
        System.out.println("Game over!");
        if(whiteTurn){ // Note: an extra turn will be completed by the time this happens. This is written with that fact in mind.
            System.out.println("Black wins!");
        } else{
            System.out.println("White wins!");
        }
    }
}