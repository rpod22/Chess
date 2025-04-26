package chess.game;

import chess.board.Board;
import chess.board.Move;
import chess.board.MoveHistory;
import chess.pieces.Piece;
import chess.pieces.King;
import chess.gui.ChessGUI;
import chess.Color;


public class Game {
    private Board board;
    private Player[] players;
    private MoveHistory moveHistory;
    private RuleValidator ruleValidator;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
        this.players = new Player[]{
                new Player(Color.WHITE, "Gracz 1"),
                new Player(Color.BLACK, "Gracz 2")
        };
        this.moveHistory = new MoveHistory();
        this.ruleValidator = new RuleValidator();
        this.currentPlayer = players[0]; // Białe zawsze zaczynają
        board.initializeBoard();
    }

    public void startGame() {
        System.out.println("Gra rozpoczęta!");
        System.out.println("Gracz rozpoczynający: " + currentPlayer.getName());

        // Uruchamiamy GUI
        new ChessGUI(this);
    }

    public boolean makeMove(Move move) {
        if (!ruleValidator.isValidMove(move, board)) {
            System.out.println("Nieprawidłowy ruch!");
            return false;
        }
        move.execute();
        moveHistory.addMove(move);
        switchTurn();
        return true;
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
        System.out.println("Teraz ruch gracza: " + currentPlayer.getName());
    }

    public boolean isCheckmate(Color color) {
        return ruleValidator.isCheckmate(board, color);
    }

    public boolean isStalemate(Color color) {
        return ruleValidator.isStalemate(board, color);
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public RuleValidator getRuleValidator() {
        return ruleValidator;
    }
}
