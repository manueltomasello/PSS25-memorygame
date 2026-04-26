package it.unibo.memory.view;

import javax.swing.*;
import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Difficulty;
import it.unibo.memory.model.Game;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final Color LIGHT_PANEL_BG;
    private static final Color LIGHT_FG;

    static {
        Color c = UIManager.getColor("Panel.background");
        LIGHT_PANEL_BG = c != null ? c : new Color(238, 238, 238);
        c = UIManager.getColor("Label.foreground");
        LIGHT_FG = c != null ? c : Color.BLACK;
    }

    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private GameView gameView;

    public static final String MENU_CARD = "MENU";
    public static final String GAME_CARD = "GIOCO";

    public MainFrame() {
        setTitle("Memory Game - Progetto PSS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 900);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        mainContainer.add(new MenuPanel(this), MENU_CARD);

        add(mainContainer);
        revalidate();
        repaint();
    }

    public void avviaPartita(final Difficulty difficulty) {
        if (gameView != null) {
            mainContainer.remove(gameView);
        }

        final Board board = new Board(difficulty);
        final Game game = new Game(difficulty.totalPairs());
        final GameController controller = new GameController(board, game, () -> repaint());
        final StatoPanel stato = new StatoPanel();
        final GamePanel griglia = new GamePanel(board, controller);
        controller.setStatoPanel(stato);
        gameView = new GameView(griglia, stato);

        mainContainer.add(gameView, GAME_CARD);
        mainContainer.revalidate();
        mostraSchermata(GAME_CARD);
    }

    public void mostraSchermata(final String nomeCarta) {
        cardLayout.show(mainContainer, nomeCarta);
    }

    public void applicaTema(final boolean dark) {
        final Color panelBg = dark ? new Color(60, 63, 65) : LIGHT_PANEL_BG;
        final Color fg      = dark ? new Color(187, 187, 187) : LIGHT_FG;
        applicaTemaRicorsivo(mainContainer, panelBg, fg);
        repaint();
    }

    private void applicaTemaRicorsivo(final Container container, final Color panelBg, final Color fg) {
        container.setBackground(panelBg);
        for (final Component comp : container.getComponents()) {
            if (comp instanceof JTextArea area) {
                area.setBackground(panelBg);
                area.setForeground(fg);
            } else if (comp instanceof JLabel label) {
                label.setForeground(fg);
            } else if (comp instanceof AbstractButton btn) {
                btn.setForeground(fg);
            }
            if (comp instanceof Container c) {
                applicaTemaRicorsivo(c, panelBg, fg);
            }
        }
    }
}
