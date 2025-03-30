package chess.pieces;

import chess.board.Move;
import chess.board.Position;
import chess.Color;

public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Move move) {
        int deltaX = move.getTo().getX() - move.getFrom().getX();
        int deltaY = move.getTo().getY() - move.getFrom().getY();

        return (deltaX == 0 || deltaY == 0); //ruch w linii prostej
    }
}