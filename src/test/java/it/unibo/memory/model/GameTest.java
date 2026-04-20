package it.unibo.memory.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void testGameLogic() {
        // 1. SETUP: Partita con 8 coppie totali
        Game game = new Game(8); 
        
        // 2. AZIONE: Aggiungo una mossa
        game.addMove(); 
        assertEquals(1, game.getMoves()); // Verifico che le mosse siano salite a 1
        
        // 3. AZIONE: Trovo una coppia
        game.addMatchedPair();
        assertEquals(1, game.getMatchedPairs()); // Verifico che le coppie siano 1
        
        // 4. VERIFICA FINALE: La partita non deve essere finita (ne mancano ancora 7!)
        assertFalse(game.isGameOver()); 
    }

@Test
    void testWinCondition() {
        // 1. SETUP: Creiamo una partita veloce da 2 sole coppie per fare in fretta
        Game smallGame = new Game(2);
        
        // 2. AZIONE: Troviamo la prima coppia
        smallGame.addMatchedPair();
        assertFalse(smallGame.isGameOver()); // Non deve ancora finire
        
        // 3. AZIONE: Troviamo la seconda (l'ultima!)
        smallGame.addMatchedPair();
        
        // 4. VERIFICA FINALE: IL MOMENTO DELLA VERITÀ:
        assertTrue(smallGame.isGameOver()); // Ora deve rispondere TRUE (vinto!)
    }

}