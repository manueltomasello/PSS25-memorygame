package it.unibo.memory.model;
 
/**
Questa classe rappresenta la carta che è l'unità principale del ns gioco.
Ogni carta ha un simbolo usato per identificare le coppie.
bisogno tenere traccia se è girata (faccia in su) o abbinata.
 */
public class Card {
 
    private final int symbol;
    private boolean faceUp;
    private boolean matched;
 
    /**
     Crea una nuova carta con il simbolo dato (symbol).
     La carta inizia coperta e non abbinata.
    */
    public Card(final int symbol) {
        this.symbol = symbol;
        this.faceUp = false;
        this.matched = false;
    }
 
    //Restituisce il simbolo della carta.

    public int getSymbol() {
        return symbol;
    }
 
    //Restituisce se questa carta è girata su.

    public boolean isFaceUp() {
        return faceUp;
    }
 
    //Restituisce se questa carta è stata Matchata.

    public boolean isMatched() {
        return matched;
    }
 
    //Gira la carta faccia all'insu.

    public void flip() {
        this.faceUp = true;
    }
 
    // Gira le carte a faccia ingiù quando due carte sono diverse.
    public void flipDown() {
        this.faceUp = false;
    }
 
    //Questo individua due carte come "abbinate" tenendo le due carte a faccia in su.

    public void setMatched() {
        this.matched = true;
        this.faceUp = true;
    }
 
}