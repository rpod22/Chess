package chess.board;

import chess.pieces.Piece;

public class Move {
    private Position from;
    private Position to;
    private Piece piece;

    public Move(Position from, Position to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }

    public void execute() {
        piece.setPosition(to);
    }

    @Override
    public String toString() {
        return piece.getClass().getSimpleName() + " z " + from + " na " + to;
    }
}