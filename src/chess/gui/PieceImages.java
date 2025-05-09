package chess.gui;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PieceImages {
    private static final Map<String, ImageIcon> images = new HashMap<>();

    static {
        loadImages();
    }

    private static void loadImages() {
        loadImage("WHITE_PAWN", "/pieces/white/pawn-white.png");
        loadImage("BLACK_PAWN", "/pieces/black/pawn-black.png");
        loadImage("WHITE_KNIGHT", "/pieces/white/knight-white.png");
        loadImage("BLACK_KNIGHT", "/pieces/black/knight-black.png");
        loadImage("WHITE_BISHOP", "/pieces/white/bishop-white.png");
        loadImage("BLACK_BISHOP", "/pieces/black/bishop-black.png");
        loadImage("WHITE_ROOK", "/pieces/white/rook-white.png");
        loadImage("BLACK_ROOK", "/pieces/black/rook-black.png");
        loadImage("WHITE_QUEEN", "/pieces/white/queen-white.png");
        loadImage("BLACK_QUEEN", "/pieces/black/queen-black.png");
        loadImage("WHITE_KING", "/pieces/white/king-white.png");
        loadImage("BLACK_KING", "/pieces/black/king-black.png");
    }

    private static void loadImage(String key, String path) {
        URL resource = PieceImages.class.getResource(path);
        if (resource != null) {
            images.put(key, new ImageIcon(resource));
        } else {
            System.err.println("Nie znaleziono pliku: " + path);
        }
    }

    public static ImageIcon getImage(String key) {
        return images.get(key);
    }
}