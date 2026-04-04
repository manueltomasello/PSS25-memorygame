package it.unibo.memory.model;

import java.util.Random;

public class Board {

    private final Card[] cards;

    public Board(final Difficulty diff) {
        this.cards = new Card[diff.totalPairs() * 2];
        genCards(diff.totalPairs());
    }
//Organizza e mescola la lista delle carte
    private void genCards(final int pairs) {
        // Creo le coppie di carte
        int index = 0;
        for (int symbol = 0; symbol < pairs; symbol++) {
            cards[index] = new Card(symbol);
            index++;
            cards[index] = new Card(symbol);
            index++;
        }
        // Mischio le carte
        Random rand = new Random();
        for (int i = 0; i < cards.length; i++) {
            int j = rand.nextInt(cards.length);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card getCard(final int index) {
        return cards[index];
    }
//meccanismo di vittoria, ci serve per vedere quando tutte le coppie sono matchate
    public boolean allMatched() {
        for (int i = 0; i < cards.length; i++) {
            if (!cards[i].isMatched()) {
                return false;
            }
        }
        return true;
    }
}