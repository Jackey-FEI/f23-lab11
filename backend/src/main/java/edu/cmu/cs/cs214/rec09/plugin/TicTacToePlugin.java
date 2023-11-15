package edu.cmu.cs.cs214.rec09.plugin;

import java.util.*;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

public class TicTacToePlugin  implements GamePlugin<String> {

    private static final String GAME_NAME = "TicTacToe Scissors";

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
        this.game = new TicTacToe();
    }

    @Override
    public void onNewGame() {
        //framework.setFooterText(GAME_START_FOOTER);
        // framework.setSquare(RockPaperScissors.ROCK.ordinal(), 0, "Rock");
        // framework.setSquare(RockPaperScissors.PAPER.ordinal(), 0, "Paper");
        // framework.setSquare(RockPaperScissors.SCISSORS.ordinal(), 0, "Scissors");
        framework.setFooterText("New game started. Player " + game.currentPlayer() + " to play.");
        game = new TicTacToe();
    }

    @Override
    public void onNewMove() { } // Nothing to do here.

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        // Update the framework grid display
        framework.setSquare(x, y, game.currentPlayer().toString());
        framework.setFooterText("Player " + game.currentPlayer() + "'s turn.");
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        TicTacToe.Player winner = game.winner();
        if (winner != null) {
            return "Player " + winner + " wins!";
        } else if (game.isOver()) {
            return "The game is a draw!";
        } else {
            return "Game is not over yet!";
        }
    }

    @Override
    public void onGameClosed() { } // Nothing to do here.

    @Override
    public String currentPlayer() {
        return game.currentPlayer().name();
    }
}
