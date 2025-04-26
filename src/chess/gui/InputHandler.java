package chess.gui;

import chess.board.Move;
import chess.board.Position;
import chess.game.Game;
import chess.pieces.Piece;
import chess.Color;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputHandler extends MouseAdapter {
    private Game game;
    private BoardPanel boardPanel;
    private Position selectedPosition = null;

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

        if (selectedPosition == null) {
            if (clickedPiece != null && clickedPiece.getColor() == game.getCurrentPlayer().getColor()) {
                selectedPosition = clickedPosition;
                boardPanel.setSelectedPosition(clickedPosition);
                boardPanel.repaint();
            }
        } else {
            Piece selectedPiece = game.getBoard().getPieceAtPosition(selectedPosition.getX(), selectedPosition.getY());

            if (selectedPiece != null) {
                Move move = new Move(selectedPosition, clickedPosition, selectedPiece);
                Piece destinationPiece = game.getBoard().getPieceAtPosition(move.getTo().getX(), move.getTo().getY());

                if (destinationPiece == null || destinationPiece.getColor() != selectedPiece.getColor()) {
                    if (selectedPiece.isValidMove(move)) {
                        // Tymczasowo wykonaj ruch
                        game.getBoard().movePiece(move);
                        move.execute();

                        // Sprawdź czy po ruchu własny król jest w szachu
                        if (game.getRuleValidator().isCheck(game.getBoard(), selectedPiece.getColor())) {
                            // Cofnij ruch
                            game.getBoard().movePiece(new Move(move.getTo(), move.getFrom(), selectedPiece));
                            if (destinationPiece != null) {
                                game.getBoard().setPieceAtPosition(move.getTo().getX(), move.getTo().getY(), destinationPiece);
                            }
                            selectedPiece.setPosition(move.getFrom());

                            JOptionPane.showMessageDialog(boardPanel, "Nie możesz zostawić swojego króla w szachu!", "Nieprawidłowy ruch", JOptionPane.WARNING_MESSAGE);
                        } else {
                            // Ruch poprawny: sprawdzamy szach-mat/pat PRZED zmianą tury!
                            Color opponentColor = (game.getCurrentPlayer().getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;

                            // Debugowanie
                            System.out.println("DEBUG: opponentColor = " + opponentColor);
                            System.out.println("DEBUG: check = " + game.getRuleValidator().isCheck(game.getBoard(), opponentColor));
                            System.out.println("DEBUG: mate = " + game.getRuleValidator().isCheckmate(game.getBoard(), opponentColor));

                            if (game.getRuleValidator().isCheckmate(game.getBoard(), opponentColor)) {
                                JOptionPane.showMessageDialog(boardPanel, "Szach-mat! Wygrywa " + game.getCurrentPlayer().getName(), "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            } else if (game.getRuleValidator().isStalemate(game.getBoard(), opponentColor)) {
                                JOptionPane.showMessageDialog(boardPanel, "Pat! Remis!", "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            } else {
                                // Zmieniamy turę dopiero TERAZ
                                game.switchTurn();

                                // Teraz sprawdzamy czy nowy gracz jest w szachu
                                if (game.getRuleValidator().isCheck(game.getBoard(), game.getCurrentPlayer().getColor())) {
                                    JOptionPane.showMessageDialog(boardPanel, "Szach dla gracza: " + game.getCurrentPlayer().getName(), "Szach!", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }

            selectedPosition = null;
            boardPanel.setSelectedPosition(null);
            boardPanel.repaint();
        }
    }
}
