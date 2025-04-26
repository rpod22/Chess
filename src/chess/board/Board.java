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
        squares[move.getTo().getX()][move.getTo().getY()] = move.getPiece();
        squares[move.getFrom().getX()][move.getFrom().getY()] = null;
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
}


