package it.unibo.memory.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class BoardTest {

    @Test
    void testBoardInitialization() {
        //Board diff media
        Board board = new Board(Difficulty.MEDIUM);
        // la dimensione del board deve essere 36 nella casistica media 
        assertEquals(36, board.getSize());
        
        //Tutte le carte devono essere coperte all'inizio
        for (int i = 0; i < board.getSize(); i++) {
            assertFalse(board.getCard(i).isFaceUp());
        }
    }
}