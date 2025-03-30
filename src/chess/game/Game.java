package chess.game;

import chess.board.Board;
import chess.board.Move;
import chess.board.MoveHistory;
import chess.pieces.Piece;
import chess.pieces.King;
import chess.game.RuleValidator;
import chess.game.Player;
import chess.gui.ChessGUI;
import chess.Color;

public class Game {
    private Board board;
    private Player[] players;
    private MoveHistory moveHistory;
    private RuleValidator ruleValidator;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.players = new Player[]{
                new Player(chess.Color.WHITE, "Gracz 1"),
                new Player(chess.Color.BLACK, "Gracz 2")
        };
        this.moveHistory = new MoveHistory();
        this.ruleValidator = new RuleValidator();
        this.currentPlayer = player1;
        board.initializeBoard();
    }

    public Game() {
        this(new Player(Color.WHITE, "Gracz 1"), new Player(Color.BLACK, "Gracz 2"));
    }

    public void startGame() {
        System.out.println("Gra rozpoczęta!");
        System.out.println("Gracz rozpoczynający: " + currentPlayer.getName());

        // Uruchamiamy interfejs graficzny
        new ChessGUI(this);
    }

    public boolean makeMove(Move move) {
        if(!ruleValidator.isValidMove(move, board)) {
            System.out.println("Nieprawidłowy ruch!");
            return false;
        };
        move.execute();
        moveHistory.addMove(move);
        switchTurn();
        return true;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
        System.out.println("Teraz ruch gracza: " + currentPlayer.getName());
    }

    public boolean isCheckmate() {
        return ruleValidator.isCheckmate(board, currentPlayer.getColor());
    }

    public boolean isStalemate() {
        return ruleValidator.isStalemate(board, currentPlayer.getColor());
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}