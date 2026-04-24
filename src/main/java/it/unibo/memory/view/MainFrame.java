package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;

/**
 * Questa è la finestra principale del gioco.
 * Usa un CardLayout per scambiare il Menu con il Gioco.
 */
public class MainFrame extends JFrame {
    
    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    
    // Nomi per le due "carte" (schermate)
    public static final String MENU_CARD = "MENU";
    public static final String GAME_CARD = "GIOCO";

    public MainFrame() {
        // 1. Setup della finestra
        setTitle("Memory Game - Progetto PSS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 900); 
        setLocationRelativeTo(null);

        // 2. Creiamo il "mazzo di carte" (il contenitore)
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Per ora la finestra è vuota. 
        // Appena Manu sistema i file, aggiungeremo qui il Menu e il Gioco.

        add(mainContainer);
    }

    /**
     * Metodo per passare da una schermata all'altra.
     */
    public void mostraSchermata(String nomeCarta) {
        cardLayout.show(mainContainer, nomeCarta);
    }
}