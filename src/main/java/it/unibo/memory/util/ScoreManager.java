package it.unibo.memory.util;

import java.util.Properties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import it.unibo.memory.model.Difficulty;

/* manager di punteggio, tiene traccia solo quando è eseguito */
public class ScoreManager {
    private static final String FILE_NAME = ".memory-pss-scores.properties";
    private static final Path SCORE_FILE = Paths.get(System.getProperty("user.home"), FILE_NAME);
    private final Properties props;

    //richiamo il file e carico 
     
    public ScoreManager() {
        props = new Properties();
        load();
    }
    /*restituisce il best score, 
    il final mi serve per evitare che cambi dentro il metodo
     implementazione controlli in caso file corrotto
    */
    public int getBestScore(final Difficulty diff) {
        final String value = props.getProperty(diff.name());
        if (value == null) {
            return Integer.MAX_VALUE;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
/* metodo per sostituire il valore se maggiore */
    public boolean updateIfBetter(final Difficulty diff, final int mosse) {
        if (mosse < getBestScore(diff)) {
            props.setProperty(diff.name(), String.valueOf(mosse));
            save();
            return true;
        }
        return false;
    }

    public String getBestScoreText(final Difficulty diff) {
        final int top = getBestScore(diff);
        return top == Integer.MAX_VALUE ? "#" : String.valueOf(top) + " mosse";
    }
 //metodo di load   
    private void load() {
        if (Files.exists(SCORE_FILE)) {
            try (var in = Files.newInputStream(SCORE_FILE)) {
                props.load(in);
            } catch (IOException e) {}
        }
    }
//metodo salvataggio
    private void save() {
        try (var out = Files.newOutputStream(SCORE_FILE)) {
            props.store(out, "Miglior Punteggio");
        } catch (IOException e) {}
    }

}
