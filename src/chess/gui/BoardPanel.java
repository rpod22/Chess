package chess.gui;

import chess.board.Board;
import chess.board.Position;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private Board board;
    private final int TILE_SIZE = 70;

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(TILE_SIZE * 8, TILE_SIZE * 8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                Piece piece = board.getPieceAtPosition(row, col);
                if(piece != null) {
                    g.setColor(Color.RED);
                    g.drawString(piece.getClass().getSimpleName(), col * TILE_SIZE + 20, row * TILE_SIZE + 40);
                }
            }
        }
    }
}