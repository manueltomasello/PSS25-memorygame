package it.unibo.memory.controller;

import it.unibo.memory.model.Board;
import it.unibo.memory.model.Card;
import it.unibo.memory.model.Game;

public class GameController {

    private final Board board;  // il tabellone con tutte le carte
    private final Game game;    // lo stato della partita
    private Card firstCard;     // la prima carta cliccata (null = nessuna carta in mano)

    // Nasce il controller, riceve il tabellone e lo stato della partita
    public GameController(final Board board, final Game game) {
        this.board = board;
        this.game = game;
        this.firstCard = null;
    }

    // Gestisce il click su una carta
    public void onCardClicked(final int position) {
        Card clicked = board.getCard(position); // prendo la carta cliccata dal tabellone

        // Se la carta è già girata o già abbinata non fare nulla
        if (clicked.isFaceUp() || clicked.isMatched()) {
            return;
        }

        clicked.flip(); // gira la carta

        // ho una carta in mano? no, salvo l'impostazione come 1 carta e aspetto la 2 carta
        if (firstCard == null) {
            firstCard = clicked; // aspetto la seconda

        } else {
            // È la seconda carta!
            game.addMove(); // aggiungo una mossa

            if (clicked.matches(firstCard)) { // sono uguali?
                clicked.setMatched();      // abbina la carta cliccata
                firstCard.setMatched();    // abbina la prima carta
                game.addMatchedPair();     // segna la coppia
            } else {
                clicked.flipDown();        // rigira la carta cliccata
                firstCard.flipDown();      // rigira la prima carta
            }

            firstCard = null; // in ogni caso, nuovo turno, ricomincio
        }
    }
}