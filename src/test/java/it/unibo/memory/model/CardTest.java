package it.unibo.memory.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card(5); // <-- Qui succede qualcosa...
        
        assertEquals(5, card.getSymbol()); // <-- E qui controlliamo...
        assertFalse(card.isFaceUp());
    }
}