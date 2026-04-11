package it.unibo.memory.controller;

import it.unibo.memory.model.Board;
import it.unibo.memory.model.Card;
import it.unibo.memory.model.Game;

public class GameController {

    private final Board board;
    private final Game game;
    private Card firstCard;

    public GameController(final Board board, final Game game) {
        this.board = board;
        this.game = game;
        this.firstCard = null;
    }

    public void onCardClicked(final int position) {
        if (game.isGameOver()) {
            return;
        }

        Card clicked = board.getCard(position);

        if (clicked.isFaceUp() || clicked.isMatched()) {
            return;
        }

        clicked.flip();

        if (firstCard == null) {
            firstCard = clicked;
        } else {
            game.addMove();

            if (clicked.matches(firstCard)) {
                clicked.setMatched();
                firstCard.setMatched();
                game.addMatchedPair();
            } else {
                clicked.flipDown();
                firstCard.flipDown();
            }

            firstCard = null;
        }
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }

    public int getMoves() {
        return game.getMoves();
    }

    public int getMatchedPairs() {
        return game.getMatchedPairs();
    }
}