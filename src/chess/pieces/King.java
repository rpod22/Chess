package chess.pieces;

import chess.board.Move;
import chess.board.Position;
import chess.Color;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Move move) {
        int deltaX = Math.abs(move.getTo().getX() - move.getFrom().getX());
        int deltaY = Math.abs(move.getTo().getY() - move.getFrom().getY());

        return (deltaX <= 1 && deltaY <= 1); //ruch o jedno pole w dowolnym kierunku
    }
}