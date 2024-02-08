import TP2D.StartScreen;

public class Main {

    public static void main(String[] args) {
        // Afficher l'écran de démarrage
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartScreen startScreen = new StartScreen();
                startScreen.setVisible(true);
            }
        });
    }
}
