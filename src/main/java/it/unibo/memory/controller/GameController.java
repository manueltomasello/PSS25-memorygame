package it.unibo.memory.controller;

import javax.swing.Timer;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Card;
import it.unibo.memory.model.Game;

public class GameController {

    private final Board board;
    private final Game game;
    private final Runnable onBoardChanged; 
    private Card firstCard;
    private boolean waiting; 

    public GameController(final Board board, final Game game, final Runnable onBoardChanged) {
        this.board = board;
        this.game = game;
        this.onBoardChanged = onBoardChanged;
        this.firstCard = null;
        this.waiting = false;
    }

    public void onCardClicked(final int position) {
        // Se stiamo aspettando il timer, ignoriamo nuovi click
        if (waiting) return;

        Card clicked = board.getCard(position);

        // Se la carta è già girata o indovinata, non facciamo nulla
        if (clicked.isFaceUp() || clicked.isMatched()) return;

        clicked.flip();

        if (firstCard == null) {
            // PRIMA CARTA
            firstCard = clicked;
            onBoardChanged.run(); // La mostriamo subito
        } else {
            // SECONDA CARTA
            game.addMove();

            if (clicked.equals(firstCard)) {
                // COPPIA TROVATA
                clicked.setMatched(true);
                firstCard.setMatched(true);
                game.addMatchedPair();
                resetTurn();
                onBoardChanged.run(); // Diventano verdi/indovinate subito

                if (game.getMatchedPairs() == board.getDifficulty().totalPairs()) {
                    System.out.println("LOGICA: Partita Terminata!");
                }
            } else {
                // COPPIE DIVERSE
                onBoardChanged.run(); // Mostriamo la seconda carta prima dell'attesa
                waiting = true;
                
                Timer timer = new Timer(1000, e -> {
                    clicked.flip();
                    firstCard.flip();
                    resetTurn();
                    onBoardChanged.run(); // Notifica che sono state rigirate
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    private void resetTurn() {
        this.firstCard = null;
        this.waiting = false;
    }

    public boolean isGameOver() { return game.isGameOver(); }
    public int getMoves() { return game.getMoves(); }
    public int getMatchedPairs() { return game.getMatchedPairs(); }
}