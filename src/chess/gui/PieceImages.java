package chess.gui;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class PieceImages {
    private static final Map<String, ImageIcon> images = new HashMap<>();

    static {
        loadImages();
    }

    private static void loadImages() {
        images.put("WHITE_PAWN", new ImageIcon("resources/pieces/white/pawn-white.png"));
        images.put("BLACK_PAWN", new ImageIcon("resources/pieces/black/pawn-black.png"));
        images.put("WHITE_KNIGHT", new ImageIcon("resources/pieces/white/knight-white.png"));
        images.put("BLACK_KNIGHT", new ImageIcon("resources/pieces/black/knight-black.png"));
        images.put("WHITE_BISHOP", new ImageIcon("resources/pieces/white/bishop-white.png"));
        images.put("BLACK_BISHOP", new ImageIcon("resources/pieces/black/bishop-black.png"));
        images.put("WHITE_ROOK", new ImageIcon("resources/pieces/white/rook-white.png"));
        images.put("BLACK_ROOK", new ImageIcon("resources/pieces/black/rook-black.png"));
        images.put("WHITE_QUEEN", new ImageIcon("resources/pieces/white/queen-white.png"));
        images.put("BLACK_QUEEN", new ImageIcon("resources/pieces/black/queen-black.png"));
        images.put("WHITE_KING", new ImageIcon("resources/pieces/white/king-white.png"));
        images.put("BLACK_KING", new ImageIcon("resources/pieces/black/king-black.png"));
    }

    public static ImageIcon getImage(String key) {
        return images.get(key);
    }
}