package it.unibo.memory.util;
import java.util.Map;
import java.util.HashMap;
import it.unibo.memory.model.Difficulty;

/* manager di punteggio, tiene traccia solo quando è eseguito */
public class ScoreManager {
    private final Map<Difficulty, Integer> scores = new HashMap<>();

    /*restituisce il best score */
    public int getBestScore(Difficulty diff) {
        return scores.getOrDefault(diff, Integer.MAX_VALUE);
    }
/* funz per sostituire il valore se maggiore */
    public boolean updateIfBetter(Difficulty diff, int moves) {
        if (moves < getBestScore(diff)) {
            scores.put(diff, moves);
            return true;
        }
        return false;
    }
}

/* promemoria, va bene temporaneamente ma poi devo far si che restino i valori  */