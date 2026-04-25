package it.unibo.memory.view;

import javax.swing.*;

import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Difficulty;
import it.unibo.memory.model.Game;

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

       // 3. Inseriamo le "Carte" nel mazzo!
        
        // --- IL MENU ---
        MenuPanel menu = new MenuPanel(this);
        mainContainer.add(menu, MENU_CARD);
        
        // --- IL GIOCO VERO ---
        // 1.(Model - I Dati)
        //Passo la difficolta
        Board board = new Board(Difficulty.EASY); 
        Game game = new Game(); 

        // 2.(Controller - Il Cervello)
        // Il "() -> repaint()" è per dire alla grafica di aggiornarsi
        GameController controller = new GameController(board, game, () -> repaint());

        // 3. Creiamo la (View)
        StatoPanel stato = new StatoPanel(); 
        GamePanel griglia = new GamePanel(board, controller); 
        
        // 4. Colleghiamo il pannello di Manu al Controller (così può aggiornare i testi)
        controller.setStatoPanel(stato);

        // 5. Li assembliamo dentro la GameView (tipo matrioska)
        GameView vistaGioco = new GameView(griglia, stato);
        
        // 6. Infiliamo la GameView
        mainContainer.add(vistaGioco, GAME_CARD);

        // 7. Diciamo alla finestra di mostrare il contenitore
        add(mainContainer);

        // 8. Forzo il disegno immediato
        revalidate();
        repaint();
    }

    /**
     * Metodo per passare da una schermata all'altra.
     */
    public void mostraSchermata(String nomeCarta) {
        cardLayout.show(mainContainer, nomeCarta);
    }
}