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

        //ruch o jedno pole do przodu
        if(deltaX == direction && deltaY == 0) {
            return true;
        }

        //ruch o dwa pola do przodu w pierwszym ruchu
        if(move.getFrom().getX() == startRow && deltaX == 2 * direction && deltaY == 0) {
            return true;
        }

        if(deltaX == direction && Math.abs(deltaY) == 1 ) {
            return true;
        }

        return false;
    }
}