package chess.pieces;

import chess.board.Move;
import chess.board.Position;
import chess.Color;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Move move) {
        int deltaX = Math.abs(move.getTo().getX() - move.getFrom().getX());
        int deltaY = Math.abs(move.getTo().getY() - move.getFrom().getY());

        if (deltaX == deltaY) { // ruch po przekątnej
            return board.isPathClear(move.getFrom(), move.getTo());
        }
        return false;
    }
}
