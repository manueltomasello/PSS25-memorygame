package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe è un contenitore che tiene insieme 
 * lo StatoPanel (di Manu) e il GamePanel (il mio).
 */
public class GameView extends JPanel {

    public GameView(GamePanel gamePanel, JPanel statoPanel) {
        // Usiamo il BorderLayout: è come una bussola (Nord, Sud, Centro...)
        setLayout(new BorderLayout());

        // Mettiamo lo StatoPanel di Manu in alto (Nord)
        if (statoPanel != null) {
            add(statoPanel, BorderLayout.NORTH);
        }

        // Mettiamo il tuo GamePanel al centro (Centro)
        if (gamePanel != null) {
            add(gamePanel, BorderLayout.CENTER);
        }
    }
}