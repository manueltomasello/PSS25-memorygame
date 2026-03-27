package it.unibo.memory.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Difficulty;

public class GamePanel extends JPanel {

    private final JButton[] buttons;         // un bottone per ogni carta
    private final GameController controller; // il controller che gestisce i click

    // Crea il pannello con la griglia di carte
    public GamePanel(final Difficulty diff, final Board board, final GameController controller) {
        this.controller = controller;
        this.buttons = new JButton[diff.totalCards()];

        setLayout(new GridLayout(diff.getRows(), diff.getCols())); // imposto la griglia

        // creo un bottone per ogni carta e lo aggiungo alla griglia
        for (int i = 0; i < diff.totalCards(); i++) {
            JButton button = new JButton("?"); // carta coperta
            final int position = i;

            button.addActionListener(e -> controller.onCardClicked(position)); // al click chiama il controller

            buttons[i] = button; // salvo il bottone
            add(button);         // aggiungo alla lavagna
        }
    }

    // Aggiorna la visualizzazione delle carte
    public void updateView(final Board board) {
        for (int i = 0; i < buttons.length; i++) {
            if (board.getCard(i).isFaceUp()) {
                buttons[i].setText(String.valueOf(board.getCard(i).getSymbol())); // mostra il simbolo
            } else {
                buttons[i].setText("?"); // carta coperta
            }
        }
    }
}