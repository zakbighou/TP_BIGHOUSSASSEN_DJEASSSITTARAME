package TP2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JPanel {
    private boolean gameRunning=false;
    private MainInterface mainInterface;
    public WelcomeScreen(MainInterface mainInterface) {
        super();
        this.mainInterface=mainInterface;
        JButton button = new JButton("Démarrer le Jeu");
        this.add(button); // Ajout du bouton à la fenêtre

        // Ajout d'un écouteur d'événements au bouton
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    private void startGame() {
        // Logique de démarrage du jeu
        System.out.println("Le jeu commence !");
        gameRunning=true;
        mainInterface.getContentPane().removeAll();
        mainInterface.startGame();
    }
}
