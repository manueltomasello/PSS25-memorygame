package it.unibo.memory.model;

import java.util.Random;

public class Board {

    private final Card[] cards;
    private final Random rand = new Random();

    public Board(final Difficulty diff) {
        this.cards = new Card[diff.totalPairs() * 2];
        genCards(diff.totalPairs());
    }
     //Organizza e mescola la lista delle carte
    private void genCards(final int pairs) {
        int index = 0;
        for (int symbol = 0; symbol < pairs; symbol++) {
            cards[index] = new Card(symbol);
            index++;
            cards[index] = new Card(symbol);
            index++;
        }
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

    public int getSize() {
        return cards.length;
    }
}
//rimosso meccanismo di vittoria, ci pensa il controller