package TP2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {

    public StartScreen() {
        setTitle("Bienvenue dans le Jeu!");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du bouton
        JButton startButton = new JButton("Commencer le Jeu");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lancer MainInterface
                new MainInterface().setVisible(true);
                dispose(); // Fermer l'écran de démarrage
            }
        });

        // Ajout du bouton à la fenêtre
        setLayout(new BorderLayout());
        add(startButton, BorderLayout.CENTER);
    }
}
