package TP2D;

import javax.swing.*;
import java.awt.*;

public class GameRender extends JPanel {
    private Dungeon dungeon;
    private Hero hero;

    private JLabel timerLabel;

    public GameRender(Dungeon dungeon, DynamicThings hero, JLabel timerLabel) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
        this.timerLabel = timerLabel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Things t : dungeon.getRenderList()){
            t.draw(g);
        }
        hero.draw(g);
       g.setColor(Color.BLACK);
       g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.drawString(timerLabel.getText(), 600,550);
    }
}
