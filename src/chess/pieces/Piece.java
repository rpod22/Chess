package chess.pieces;

import chess.board.Board;
import chess.board.Move;
import chess.board.Position;
import chess.Color;

public abstract class Piece {
    protected Color color;
    protected Position position;
    protected Board board; // <-- NOWE POLE

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public abstract boolean isValidMove(Move move);
}
