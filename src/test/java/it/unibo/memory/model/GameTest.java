package it.unibo.memory.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void testGameLogic() {
        // 1. SETUP: Usiamo le coppie della difficoltà media di Manu invece di un numero fisso
        Game game = new Game(Difficulty.MEDIUM.totalPairs()); 
        
        // 2. AZIONE: Aggiungo una mossa
        game.addMove(); 
        assertEquals(1, game.getMoves()); 
        
        // 3. AZIONE: Trovo una coppia
        game.addMatchedPair();
        assertEquals(1, game.getMatchedPairs()); 
        
        // 4. VERIFICA FINALE: La partita non deve essere finita
        assertFalse(game.isGameOver()); 
    }

    @Test
    void testWinCondition() {
        // 1. SETUP: Per il test di vittoria possiamo anche tenere un numero piccolo, 
        // ma è più elegante usare la difficoltà EASY
        Game smallGame = new Game(Difficulty.EASY.totalPairs());
        int pairsToWin = Difficulty.EASY.totalPairs();
        
        // 2. AZIONE: Troviamo tutte le coppie tranne l'ultima
        for (int i = 0; i < pairsToWin - 1; i++) {
            smallGame.addMatchedPair();
            assertFalse(smallGame.isGameOver(), "La partita non dovrebbe finire alla coppia " + (i+1));
        }
        
        // 3. AZIONE: Troviamo l'ultima coppia
        smallGame.addMatchedPair();
        
        // 4. VERIFICA FINALE:
        assertTrue(smallGame.isGameOver(), "La partita dovrebbe essere finita ora!");
    }
}