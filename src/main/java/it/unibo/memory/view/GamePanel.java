package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;
import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;

/**
 * Pannello che visualizza la griglia di carte del gioco.
 * Fa parte del livello VIEW nel pattern MVC: non gestisce logica,
 * delega tutto al Controller.
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L; //Serve solo a identificare questa versione del pannello di Java

    private final GameController controller;
    private final JButton[] buttons; // un bottone per ogni carta della griglia

    /**
     * @param board      da cui leggiamo righe e colonne
     * @param controller a cui deleghiamo i click dell'utente
     */
    public GamePanel(final Board board, final GameController controller) {
        this.controller = controller;

        final int totalCards = board.getRows() * board.getCols();
        this.buttons = new JButton[totalCards];

        // GridLayout dispone i componenti in una griglia rigida; 5,5 sono i pixel di spazio tra le celle
        this.setLayout(new GridLayout(board.getRows(), board.getCols(), 5, 5));
        this.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < totalCards; i++) {
            final int position = i; // final necessario: le lambda catturano solo variabili effectively final

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
     * Da completare quando il modello esporrà lo stato faceUp di ogni carta.
     */
    public void updateDisplay() {
        // TODO: iterare su buttons[], chiedere al modello se la carta è scoperta
        // e aggiornare testo/icona di conseguenza
    }
}