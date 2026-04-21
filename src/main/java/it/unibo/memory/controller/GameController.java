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

        // Se la carta è già girata o già abbinata, il click va a vuoto
        if (clicked.isFaceUp() || clicked.isMatched()) {
            return;
        }

        clicked.flip(); // gira la carta faccia in su

        // È la PRIMA carta che scopro nel mio turno?
        if (firstCard == null) {
            firstCard = clicked; // me la salvo in mano e aspetto il secondo click

        } else {
            // È la SECONDA carta! Ora si fa sul serio.
            game.addMove(); // aggiungo una mossa al conteggio globale

            // 1. Uso di equals: usiamo il nostro equals() per confrontare i simboli!
            if (clicked.equals(firstCard)) {
                
                // 2. AGGIORNATO: diciamo alla carta che è stata indovinata (true)
                clicked.setMatched(true);      
                firstCard.setMatched(true);    
                game.addMatchedPair();     // dico a Game che ho trovato una coppia in più

            } else {
                // 3. AGGIORNATO: usiamo il nostro flip() per rigirarle entrambe a faccia in giù
                clicked.flip();        
                firstCard.flip();      
            }

            // In ogni caso, il turno è finito, svuoto la mano per il turno successivo
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