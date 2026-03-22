package it.unibo.memory.model;

public class Game {

    // I campi — lo "stato" della partita
    private int moves;         // quante mosse hai fatto
    private int matchedPairs;  // quante coppie hai trovato
    private boolean gameOver;  // la partita è finita?

    // Inizia - nasce una nuova partita
    public Game() {
        this.moves = 0;
        this.matchedPairs = 0;
        this.gameOver = false;
    }

    // Aggiunge una mossa
    public void addMove() {
        this.moves++;
    }

    // Segna una coppia trovata
    public void addMatchedPair() {
        this.matchedPairs++;
    }

    // Controlla se la partita è finita
    public boolean isGameOver() {
        return this.gameOver;
    }

    // Segna la partita come finita
    public void setGameOver() {
        this.gameOver = true;
    }

    // Dimmi quante mosse ho fatto
    public int getMoves() {
        return this.moves;
    }

    // Dimmi quante coppie ho trovato
    public int getMatchedPairs() {
        return this.matchedPairs;
    }
}