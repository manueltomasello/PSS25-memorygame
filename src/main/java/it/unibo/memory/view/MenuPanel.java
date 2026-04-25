package it.unibo.memory.view;

import javax.swing.*;

public class MenuPanel extends JPanel {

    public MenuPanel(MainFrame mainFrame) {
        // Creiamo un pulsante gigantesco
        JButton btnInizia = new JButton("INIZIA PARTITA (Test Manu Muoviti)");
        
        // Diciamo al pulsante cosa fare: girare pagina sul gioco!
        btnInizia.addActionListener(e -> {
            mainFrame.mostraSchermata(MainFrame.GAME_CARD); 
        });

        add(btnInizia);
    }
}