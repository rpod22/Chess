package chess.game;

import chess.board.Board;
import chess.board.Move;
import chess.pieces.Piece;
import chess.pieces.King;

import java.awt.*;

public class RuleValidator {

    public boolean isValidMove(Move move, Board board) {
        Piece piece = move.getPiece();
        if(piece == null) return false;

        if(!piece.isValidMove(move)) return false;
        if(isPathBlocked(move, board)) return false;
        if(leavesKingInCheck(move, board)) return false;

        return true;
    }

    private boolean isPathBlocked(Move move, Board board) {
        return false; //TODO: Implementacja dla poszczególnych figur
    }

    private boolean leavesKingInCheck(Move move, Board board) {
        //tymczasowy ruch
        Piece temp = board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY());
        board.movePiece(move);

        //sprawdzenie czy po ruchu król jest w szachu
        boolean kingInCheck = isCheck(board, move.getPiece().getColor());

        //cofniecie ruchu
        board.movePiece(new Move(move.getTo(), move.getFrom(), move.getPiece()));
        board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY());

        return kingInCheck;
    }

    public boolean isCheck(Board board, chess.Color color) {
        //znalezienie króla
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Piece piece = board.getPieceAtPosition(row, col);
                if(piece instanceof King && piece.getColor() == color) {
                    //sprawdzenie czy któryś z przeciwników go atakuje
                    return isKingAttacked(board, row, col, color);
                }
            }
        }
        return false;
    }

    private boolean isKingAttacked(Board board, int kingX, int kingY, chess.Color kingColor) {
        //TODO: implementacja sprawdzenia czy król jest atakowany
        return false;
    }

    public boolean isCheckmate(Board board, chess.Color color) {
        if(!isCheck(board, color)) return false;
        //TODO: sprawdzenie czy gracz ma legalne ruchy

        return false;
    }

    public boolean isStalemate(Board board, chess.Color color) {
        if(isCheck(board, color)) return false;
        //TODO: Sprawdzenie czy nie można wykonać żadnego ruchu

        return false;
    }
}