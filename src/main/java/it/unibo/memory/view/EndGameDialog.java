package it.unibo.memory.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.memory.model.Difficulty;
import it.unibo.memory.util.ScoreManager;

public class EndGameDialog extends JDialog {

    public EndGameDialog(
            final JFrame parent,
            final Difficulty difficulty,
            final int moves,
            final ScoreManager scoreManager,
            final Runnable onNewGame,
            final Runnable onMenu) {

        super(parent, "Partita Terminata", true);

        final boolean newRecord = scoreManager.updateIfBetter(difficulty, moves);

        final JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        final JLabel title = new JLabel("VITTORIA!", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(title);
        content.add(Box.createVerticalStrut(12));

        final JLabel diffLabel = new JLabel("Difficoltà: " + difficulty.name(), SwingConstants.CENTER);
        diffLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(diffLabel);
        content.add(Box.createVerticalStrut(8));

        final JLabel movesLabel = new JLabel("Le tue mosse: " + moves, SwingConstants.CENTER);
        movesLabel.setFont(movesLabel.getFont().deriveFont(Font.BOLD, 16f));
        movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(movesLabel);
        content.add(Box.createVerticalStrut(8));

        final JLabel recordLabel = new JLabel(
                "Record " + difficulty.name() + ": " + scoreManager.getBestScoreText(difficulty),
                SwingConstants.CENTER);
        recordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(recordLabel);

        if (newRecord) {
            content.add(Box.createVerticalStrut(8));
            final JLabel newRecordLabel = new JLabel("Nuovo Record!", SwingConstants.CENTER);
            newRecordLabel.setFont(newRecordLabel.getFont().deriveFont(Font.BOLD, 14f));
            newRecordLabel.setForeground(new Color(0, 140, 0));
            newRecordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(newRecordLabel);
        }

        content.add(Box.createVerticalStrut(20));

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        final JButton newGameBtn = new JButton("Nuova Partita");
        newGameBtn.addActionListener(e -> { dispose(); onNewGame.run(); });
        final JButton menuBtn = new JButton("Menu");
        menuBtn.addActionListener(e -> { dispose(); onMenu.run(); });
        buttonPanel.add(newGameBtn);
        buttonPanel.add(menuBtn);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(buttonPanel);

        setContentPane(content);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }
}
// manca l'integrazione con l'api per la frase randomica