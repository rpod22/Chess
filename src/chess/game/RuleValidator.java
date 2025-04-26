package chess.game;

import chess.board.Board;
import chess.board.Move;
import chess.board.Position;
import chess.pieces.Piece;
import chess.pieces.King;

public class RuleValidator {

    public boolean isValidMove(Move move, Board board) {
        Piece piece = move.getPiece();
        if (piece == null) return false;

        if (!piece.isValidMove(move)) return false;
        if (isPathBlocked(move, board)) return false;
        if (leavesKingInCheck(move, board)) return false;

        return true;
    }

    private boolean isPathBlocked(Move move, Board board) {
        Piece piece = move.getPiece();

        // Skoczki i królowie nie potrzebują sprawdzania ścieżki
        if (piece instanceof chess.pieces.Knight || piece instanceof chess.pieces.King) {
            return false;
        }

        int deltaX = Integer.compare(move.getTo().getX(), move.getFrom().getX());
        int deltaY = Integer.compare(move.getTo().getY(), move.getFrom().getY());

        int currentX = move.getFrom().getX() + deltaX;
        int currentY = move.getFrom().getY() + deltaY;

        while (currentX != move.getTo().getX() || currentY != move.getTo().getY()) {
            if (board.getPieceAtPosition(currentX, currentY) != null) {
                return true; // jakaś figura na drodze
            }
            currentX += deltaX;
            currentY += deltaY;
        }

        return false; // nic nie blokuje drogi
    }

    private boolean leavesKingInCheck(Move move, Board board) {
        Piece captured = board.getPieceAtPosition(move.getTo().getX(), move.getTo().getY());
        board.movePiece(move);
        move.getPiece().setPosition(move.getTo());

        boolean kingInCheck = isCheck(board, move.getPiece().getColor());

        board.movePiece(new Move(move.getTo(), move.getFrom(), move.getPiece()));
        move.getPiece().setPosition(move.getFrom());
        if (captured != null) {
            board.setPieceAtPosition(move.getTo().getX(), move.getTo().getY(), captured);
        }

        return kingInCheck;
    }

    public boolean isCheck(Board board, chess.Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceAtPosition(row, col);
                if (piece instanceof King && piece.getColor() == color) {
                    return isKingAttacked(board, row, col, color);
                }
            }
        }
        return false;
    }

    private boolean isKingAttacked(Board board, int kingX, int kingY, chess.Color kingColor) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceAtPosition(row, col);
                if (piece != null && piece.getColor() != kingColor) {
                    Move attackMove = new Move(new Position(row, col), new Position(kingX, kingY), piece);
                    if (piece.isValidMove(attackMove)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(Board board, chess.Color color) {
        if (!isCheck(board, color)) {
            return false;
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceAtPosition(row, col);
                if (piece != null && piece.getColor() == color) {
                    for (int toRow = 0; toRow < 8; toRow++) {
                        for (int toCol = 0; toCol < 8; toCol++) {
                            Position from = new Position(row, col);
                            Position to = new Position(toRow, toCol);
                            Move move = new Move(from, to, piece);

                            if (piece.isValidMove(move)) {
                                Piece captured = board.getPieceAtPosition(toRow, toCol);

                                board.movePiece(move);
                                piece.setPosition(to);

                                boolean kingInCheckAfterMove = isCheck(board, color);

                                board.movePiece(new Move(to, from, piece));
                                piece.setPosition(from);
                                if (captured != null) {
                                    board.setPieceAtPosition(toRow, toCol, captured);
                                }

                                if (!kingInCheckAfterMove) {
                                    return false; // istnieje ruch ratujący króla
                                }
                            }
                        }
                    }
                }
            }
        }

        return true; // brak ruchu ratującego -> SZACH MAT
    }


    public boolean isStalemate(Board board, chess.Color color) {
        if (isCheck(board, color)) {
            return false;
        }

        for (int fromRow = 0; fromRow < 8; fromRow++) {
            for (int fromCol = 0; fromCol < 8; fromCol++) {
                Piece piece = board.getPieceAtPosition(fromRow, fromCol);
                if (piece != null && piece.getColor() == color) {
                    for (int toRow = 0; toRow < 8; toRow++) {
                        for (int toCol = 0; toCol < 8; toCol++) {
                            Position from = new Position(fromRow, fromCol);
                            Position to = new Position(toRow, toCol);

                            Move move = new Move(from, to, piece);

                            if (isValidMove(move, board)) {
                                if (canMoveWithoutCheck(board, piece, from, to)) {
                                    return false; // istnieje legalny ruch
                                }
                            }
                        }
                    }
                }
            }
        }

        return true; // Brak legalnych ruchów -> PAT
    }

    private boolean canMoveWithoutCheck(Board board, Piece piece, Position from, Position to) {
        Move move = new Move(from, to, piece);
        Piece captured = board.getPieceAtPosition(to.getX(), to.getY());

        board.movePiece(move);
        piece.setPosition(to);

        boolean kingStillInCheck = isCheck(board, piece.getColor());

        board.movePiece(new Move(to, from, piece));
        piece.setPosition(from);
        if (captured != null) {
            board.setPieceAtPosition(to.getX(), to.getY(), captured);
        }

        return !kingStillInCheck;
    }
}
