package it.unibo.memory.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private final List<Card> cards;

    //Board prende la matrice dalla difficoltà scelta 
    public Board(final Difficulty diff) {
        this.cards = new ArrayList<>();
        genCards(diff.totalPairs());
    }  
    //Organizza e mescola la lista delle carte.
   
    private void genCards(final int pairs) {
        for (int symbol = 0; symbol < pairs; symbol++) {
            cards.add(new Card(symbol));
            cards.add(new Card(symbol));
        }
        Collections.shuffle(cards); // mischio le carte dentro la lista randomico
    }

    public Card getCard(final int index) {
        return cards.get(index); //Restituisce l'indice della carta
    }

    //meccanismo di vittoria, ci serve per vedere quando tutte le coppie sono matchate
    public boolean allMatched() {
        return cards.stream().allMatch(Card::isMatched);
    }
}
