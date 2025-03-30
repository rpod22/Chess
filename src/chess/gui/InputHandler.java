package chess.gui;

import chess.board.Move;
import chess.board.Position;
import chess.game.Game;
import chess.pieces.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class InputHandler extends MouseAdapter {
    private Game game;
    private BoardPanel boardPanel;
    private Position selectedPosition;

    public InputHandler(Game game, BoardPanel boardPanel) {
        this.game = game;
        this.boardPanel = boardPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / 70;
        int row = e.getY() / 70;

        Position clickedPosition = new Position(row, col);
        Piece clickedPiece = game.getBoard().getPieceAtPosition(row, col);

    }

}