package it.unibo.memory.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Difficulty;

public class GamePanel extends JPanel {

    // Un bottone per ogni carta
    private final JButton[] buttons;
    // Il controller che gestisce i click
    private final GameController controller;

    // Nasce il pannello con la griglia di carte
    public GamePanel(final Difficulty diff, final Board board, final GameController controller) {
        this.controller = controller;
        this.buttons = new JButton[diff.totalCards()];

        // Imposto la griglia con righe e colonne
        setLayout(new GridLayout(diff.getRows(), diff.getCols()));

        // Creo un bottone per ogni carta
        for (int i = 0; i < diff.totalCards(); i++) {
            JButton button = new JButton("?");
            final int posizione = i;
            button.addActionListener(e -> controller.onCardClicked(posizione));
            buttons[i] = button;
            add(button);
        }
    }

    // Aggiorna la griglia — mostra le carte girate o coperte
    public void updateView(final Board board) {
        for (int i = 0; i < buttons.length; i++) {
            if (board.getCard(i).isFaceUp()) {
                buttons[i].setText(String.valueOf(board.getCard(i).getSymbol()));
            } else {
                buttons[i].setText("?");
            }
        }
    }
}