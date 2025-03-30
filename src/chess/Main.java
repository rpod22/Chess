package chess;

import chess.game.Game;
import chess.gui.ChessGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Szachy - Start");

        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            new ChessGUI(game);
        });
    }
}