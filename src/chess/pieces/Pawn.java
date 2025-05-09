package chess.pieces;

import chess.board.Move;
import chess.board.Position;
import chess.Color;

public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Move move) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        int startRow = (color == Color.WHITE) ? 6 : 1;

        int deltaX = move.getTo().getX() - move.getFrom().getX();
        int deltaY = move.getTo().getY() - move.getFrom().getY();

        // ruch o jedno pole do przodu
        if (deltaX == direction && deltaY == 0 &&
                board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY()) == null) {
            return true;
        }

        // ruch o dwa pola z pozycji początkowej
        if (move.getFrom().getX() == startRow && deltaX == 2 * direction && deltaY == 0 &&
                board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY()) == null &&
                board.getPieceAtPosition(move.getFrom().getX() + direction, move.getFrom().getY()) == null) {
            return true;
        }

        // bicie
        if (deltaX == direction && Math.abs(deltaY) == 1) {
            Piece target = board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY());
            return target != null && target.getColor() != color;
        }

        return false;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);

        boolean reachedEnd = (color == Color.WHITE && position.getX() == 0) ||
                (color == Color.BLACK && position.getX() == 7);

        if (reachedEnd) {
            Queen promotedQueen = new Queen(color, position);
            promotedQueen.setBoard(board); // 🔥 KONIECZNE!
            board.setPieceAtPosition(position.getX(), position.getY(), promotedQueen);
        }
    }
}