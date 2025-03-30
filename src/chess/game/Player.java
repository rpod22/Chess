package chess.game;

import chess.Color;

public class Player {
    private Color color;
    private String name;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void makeMove() {
        // Możemy tu dodać obsługę ruchów gracza (np. wybór ruchu w GUI)
    }
}
