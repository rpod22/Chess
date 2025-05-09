package chess.pieces;

import chess.Color;
import chess.board.Board;
import chess.board.Move;
import chess.board.Position;

public class King extends Piece {
    private boolean hasMoved = false;

    public King(Color color, Position position) {
        super(color, position);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public boolean isValidMove(Move move) {
        int dx = Math.abs(move.getTo().getX() - move.getFrom().getX());
        int dy = Math.abs(move.getTo().getY() - move.getFrom().getY());

        if (dx <= 1 && dy <= 1) {
            return true;
        }

        if (!hasMoved && dx == 0 && dy == 2) {
            return board.isCastlingPossible(color, true); // kingside
        }

        if (!hasMoved && dx == 0 && dy == 2 && move.getTo().getY() == 2) {
            return board.isCastlingPossible(color, false); // queenside
        }

        return false;
    }
}