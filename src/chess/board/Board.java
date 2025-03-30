package chess.board;

import chess.Color;
import chess.pieces.*;

public class Board {
    private Piece[][] squares = new Piece[8][8];

    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            squares[1][i] = new Pawn(Color.BLACK, new Position(1, i));
            squares[6][i] = new Pawn(Color.WHITE, new Position(6, i));
        }

        placePieces(0, Color.BLACK);
        placePieces(7, Color.WHITE);
    }

    private void placePieces(int row, Color color) {
        squares[row][0] = new Rook(color, new Position(row, 0));
        squares[row][7] = new Rook(color, new Position(row, 7));
        squares[row][1] = new Knight(color, new Position(row, 1));
        squares[row][6] = new Knight(color, new Position(row, 6));
        squares[row][2] = new Bishop(color, new Position(row, 2));
        squares[row][5] = new Bishop(color, new Position(row, 5));
        squares[row][3] = new Queen(color, new Position(row, 3));
        squares[row][4] = new King(color, new Position(row, 4));
    }

    public Piece getPieceAtPosition(int x, int y) {
        return squares[x][y];
    }

    public void movePiece(Move move) {
        squares[move.getTo().getX()][move.getTo().getY()] = move.getPiece();
        squares[move.getFrom().getX()][move.getFrom().getY()] = null;
    }
}
