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

        return (deltaX == deltaY) || (deltaX == 0 || deltaY == 0); //ruch po przekatnej albo w linii prostej
    }
}