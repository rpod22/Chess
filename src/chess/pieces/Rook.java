package chess.pieces;

import chess.Color;
import chess.board.Board;
import chess.board.Move;
import chess.board.Position;

public class Rook extends Piece {
    private boolean hasMoved = false;

    public Rook(Color color, Position position) {
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
        int dx = move.getTo().getX() - move.getFrom().getX();
        int dy = move.getTo().getY() - move.getFrom().getY();

        if (dx == 0 || dy == 0) {
            return board.isPathClear(move.getFrom(), move.getTo());
        }

        return false;
    }
}