package chess.board;

import chess.Color;
import chess.pieces.*;

public class Board {
    private Piece[][] squares = new Piece[8][8];

    public Piece[][] getSquares() {
        return squares;
    }

    public void setPieceAtPosition(int x, int y, Piece piece) {
        squares[x][y] = piece;
    }


    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            squares[1][i] = new Pawn(Color.BLACK, new Position(1, i));
            squares[1][i].setBoard(this);
            squares[6][i] = new Pawn(Color.WHITE, new Position(6, i));
            squares[6][i].setBoard(this);
        }

        placePieces(0, Color.BLACK);
        placePieces(7, Color.WHITE);
    }

    private void placePieces(int row, Color color) {
        squares[row][0] = new Rook(color, new Position(row, 0));
        squares[row][0].setBoard(this);

        squares[row][7] = new Rook(color, new Position(row, 7));
        squares[row][7].setBoard(this);

        squares[row][1] = new Knight(color, new Position(row, 1));
        squares[row][1].setBoard(this);

        squares[row][6] = new Knight(color, new Position(row, 6));
        squares[row][6].setBoard(this);

        squares[row][2] = new Bishop(color, new Position(row, 2));
        squares[row][2].setBoard(this);

        squares[row][5] = new Bishop(color, new Position(row, 5));
        squares[row][5].setBoard(this);

        squares[row][3] = new Queen(color, new Position(row, 3));
        squares[row][3].setBoard(this);

        squares[row][4] = new King(color, new Position(row, 4));
        squares[row][4].setBoard(this);
    }


    public Piece getPieceAtPosition(int x, int y) {
        return squares[x][y];
    }

    public void movePiece(Move move) {
        Piece piece = move.getPiece();
        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        squares[toX][toY] = piece;
        squares[fromX][fromY] = null;

        if (piece instanceof King) {
            ((King) piece).setHasMoved(true);

            if (move.isCastling()) {
                int row = fromX;

                if (toY == 6) { // Roszada krótka
                    Piece rook = getPieceAtPosition(row, 7);
                    if (rook instanceof Rook) {
                        squares[row][5] = rook;
                        squares[row][7] = null;
                        ((Rook) rook).setHasMoved(true);
                        rook.setPosition(new Position(row, 5));
                    }
                } else if (toY == 2) { // Roszada długa
                    Piece rook = getPieceAtPosition(row, 0);
                    if (rook instanceof Rook) {
                        squares[row][3] = rook;
                        squares[row][0] = null;
                        ((Rook) rook).setHasMoved(true);
                        rook.setPosition(new Position(row, 3));
                    }
                }
            }
        }

        if (piece instanceof Rook) {
            ((Rook) piece).setHasMoved(true);
        }
    }

    public boolean isPathClear(Position from, Position to) {
        int deltaX = Integer.compare(to.getX(), from.getX());
        int deltaY = Integer.compare(to.getY(), from.getY());

        int x = from.getX() + deltaX;
        int y = from.getY() + deltaY;

        while (x != to.getX() || y != to.getY()) {
            if (squares[x][y] != null) {
                return false;
            }
            x += deltaX;
            y += deltaY;
        }

        return true;
    }

    public boolean isCastlingPossible(Color color, boolean kingside) {
        int row = (color == Color.WHITE) ? 7 : 0;
        int kingCol = 4;
        int rookCol = kingside ? 7 : 0;

        Piece king = getPieceAtPosition(row, kingCol);
        Piece rook = getPieceAtPosition(row, rookCol);

        if (!(king instanceof King) || !(rook instanceof Rook)) return false;
        if (((King) king).hasMoved() || ((Rook) rook).hasMoved()) return false;

        int step = kingside ? 1 : -1;
        for (int i = 1; i <= 2; i++) {
            int col = kingCol + i * step;
            if (getPieceAtPosition(row, col) != null) return false;

            Position from = new Position(row, kingCol);
            Position to = new Position(row, col);
            Move tempMove = new Move(from, to, king);

            movePiece(tempMove);
            king.setPosition(to);

            boolean inCheck = new chess.game.RuleValidator().isCheck(this, color);

            movePiece(new Move(to, from, king));
            king.setPosition(from);
            setPieceAtPosition(row, col, null);

            if (inCheck) return false;
        }

        if (!kingside && getPieceAtPosition(row, 1) != null) return false;

        return true;
    }
}


