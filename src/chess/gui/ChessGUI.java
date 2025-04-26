package chess.gui;

import chess.game.Game;
import chess.game.Player;
import chess.Color;

import javax.swing.*;
import java.awt.*;

public class ChessGUI extends JFrame {
    private Game game;
    private BoardPanel boardPanel;

    public ChessGUI(Game game) {  // ✅ Konstruktor przyjmujący obiekt Game
        this.game = game;
        setTitle("Szachy");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel(game.getBoard());
        add(boardPanel, BorderLayout.CENTER);

        boardPanel.addMouseListener(new InputHandler(game, boardPanel));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Player player1 = new Player(Color.WHITE, "Gracz 1");
            Player player2 = new Player(Color.BLACK, "Gracz 2");
            new Game();
        });
    }

}
