package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;
import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;

/**
 * Pannello che visualizza la griglia di carte del gioco.
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final GameController controller;
    private final JButton[] buttons;

    public GamePanel(final Board board, final GameController controller) {
        this.controller = controller;

        // ACCESSO CORRETTO: Passiamo per getDifficulty()
        final int totalCards = board.getDifficulty().totalCards();
        this.buttons = new JButton[totalCards];

        // IMPOSTAZIONE GRIGLIA: Chiediamo righe e colonne alla Difficulty
        this.setLayout(new GridLayout(
            board.getDifficulty().getRows(), 
            board.getDifficulty().getCols(), 
            5, 5
        ));
        
        this.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < totalCards; i++) {
            final int position = i;

            buttons[i] = new JButton("?");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));

            buttons[i].addActionListener(e -> {
                controller.onCardClicked(position);
                updateDisplay();
            });

            this.add(buttons[i]);
        }
    }

    /**
     * Aggiorna i bottoni in base allo stato del modello.
     */
    public void updateDisplay() {
        // Sarà implementato per mostrare le icone delle carte
    }
}