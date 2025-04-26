package chess.gui;

import chess.board.Board;
import chess.board.Position;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private Board board;
    private final int TILE_SIZE = 70;
    private Position selectedPosition = null;

    public void setSelectedPosition(Position position) {
        this.selectedPosition = position;
    };

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(TILE_SIZE * 8, TILE_SIZE * 8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // Jeśli to jest wybrane pole, rysujemy niebieski obramowanie
                if (selectedPosition != null && selectedPosition.getX() == row && selectedPosition.getY() == col) {
                    g.setColor(Color.BLUE);
                    g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    g.drawRect(col * TILE_SIZE + 1, row * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
                }

                Piece piece = board.getPieceAtPosition(row, col);
                if (piece != null) {
                    drawPiece(g, piece, col, row);
                }
            }
        }
    }


    private void drawPiece(Graphics g, Piece piece, int col, int row) {
        Graphics2D g2d = (Graphics2D) g;
        String pieceKey = piece.getColor().toString() + "_" + piece.getClass().getSimpleName().toUpperCase();

        ImageIcon icon = PieceImages.getImage(pieceKey);

        if (icon != null) {
            int x = col * TILE_SIZE;
            int y = row * TILE_SIZE;
            g2d.drawImage(icon.getImage(), x, y, TILE_SIZE, TILE_SIZE, this);
        } else {
            // Jeśli brak grafiki, narysuj nazwę figury jako tekst (awaryjnie)
            g2d.setColor(Color.RED);
            g2d.drawString(piece.getClass().getSimpleName(), col * TILE_SIZE + 10, row * TILE_SIZE + 40);
        }
    }
}
