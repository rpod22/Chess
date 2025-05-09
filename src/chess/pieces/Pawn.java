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

        Piece targetPiece = board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY());

        if (deltaX == direction && deltaY == 0 && targetPiece == null) {
            return true;
        }

        if (move.getFrom().getX() == startRow && deltaX == 2 * direction && deltaY == 0) {
            Position middlePosition = new Position(move.getFrom().getX() + direction, move.getFrom().getY());
            if (targetPiece == null && board.getPieceAtPosition(middlePosition.getX(), middlePosition.getY()) == null) {
                return true;
            }
        }

        if (deltaX == direction && Math.abs(deltaY) == 1 && targetPiece != null && targetPiece.getColor() != color) {
            return true;
        }

        return false;
    }
}