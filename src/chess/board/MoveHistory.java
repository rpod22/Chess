package chess.board;

import java.util.Stack;

public class MoveHistory {
    private Stack<Move> moves;

    public MoveHistory() {
        this.moves = new Stack<>();
    }

    public void addMove(Move move) {
        moves.push(move);
    }

    public Move getLastMove() {
        return moves.isEmpty() ? null : moves.peek();
    }

    public Move undoLastMove() {
        return moves.isEmpty() ? null : moves.pop();
    }

    public boolean isEmpty() {
        return moves.isEmpty();
    }
}