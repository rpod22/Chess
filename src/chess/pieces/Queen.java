package chess.pieces;

import chess.board.Move;
import chess.board.Position;
import chess.Color;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Move move) {
        int deltaX = Math.abs(move.getTo().getX() - move.getFrom().getX());
        int deltaY = Math.abs(move.getTo().getY() - move.getFrom().getY());

        if (deltaX == deltaY || move.getFrom().getX() == move.getTo().getX() || move.getFrom().getY() == move.getTo().getY()) {
            return board.isPathClear(move.getFrom(), move.getTo());
        }
        return false;
    }
}