package TP2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainInterface extends JFrame implements KeyListener {
    TileManager tileManager = new TileManager(48,48,"./img/tileSet.png");
    Dungeon dungeon = new Dungeon("./gameData/level1.txt",tileManager);
    Hero hero = Hero.getInstance();
    JPanel panel = new JPanel();
    JLabel timerLabel = new JLabel("Time left: 0:00", SwingConstants.CENTER);
    GameRender gameRender = new GameRender(dungeon,hero,timerLabel);
    WelcomeScreen welcome = new WelcomeScreen(this);
    MainInterface instance = this;
    Timer timer;
    private boolean gameRunning = true;
    private Timer chrono;
    private int limitTime=30;
    private int elapsedTimeInSeconds;

    public MainInterface() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(welcome);
        this.setSize(500,300);


        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));


        elapsedTimeInSeconds = 0;
        chrono = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTimeInSeconds++;

                int minutes = (limitTime - elapsedTimeInSeconds)/60;
                int secondes = (limitTime - elapsedTimeInSeconds)%60;

                timerLabel.setText(String.format("Time left: %d:%02d", minutes, secondes));

                System.out.println(String.format("Time left: %d:%02d", minutes, secondes));

                if (elapsedTimeInSeconds >= limitTime) {
                    System.out.println("Fin du temps réglementaire");
                    chrono.stop();
                    hero.setWalking(false);
                    showGameOverDialog();
                }
            }
        });
        //chrono.start();

        ActionListener animationTimer=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                final int speed=10;
                if (hero.isWalking()){
                    switch (hero.getOrientation()){
                        case LEFT:  hero.moveIfPossible(-speed,0,dungeon);
                            break;
                        case RIGHT: hero.moveIfPossible(speed,0,dungeon);
                            break;
                        case UP:    hero.moveIfPossible(0,-speed,dungeon);
                            break;
                        case DOWN:  hero.moveIfPossible(0,speed,dungeon);
                            break;

                    }
                }
            }
        };
        timer = new Timer(50,animationTimer);
        this.setVisible(true);

    }

    public void startGame(){
        this.setContentPane(gameRender);
        timer.start();
        this.addKeyListener(this);
        this.requestFocusInWindow();
        this.setSize((int) dungeon.getWidth()*tileManager.getWidth(), (int) dungeon.getHeight()*(tileManager.getHeigth()+3)+30);
        panel.setSize(this.getSize());
//        panel.add(timerLabel);
//        panel.add(gameRender);
        this.revalidate();
        chrono.start();
    }

    public static void main(String[] args){
        MainInterface mainInterface = new MainInterface();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                hero.setOrientation(Orientation.LEFT);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setOrientation(Orientation.RIGHT);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_UP:
                hero.setOrientation(Orientation.UP);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_DOWN:
                hero.setOrientation(Orientation.DOWN);
                hero.setWalking(true);
                break;
        }
        this.repaint();
    }

    @Override

    public void keyReleased(KeyEvent e) {

        hero.setWalking(false);
    }


    private void showGameOverScreen(){
        JOptionPane.showMessageDialog(this,"Game Over","Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void showGameOverDialog() {
        gameRunning = false;

        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        customPanel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Game Over. Que souhaitez-vous faire ?");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        customPanel.add(messageLabel, BorderLayout.CENTER);

        int option = JOptionPane.showOptionDialog(
                this,
                customPanel,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Relancer", "Quitter"},
                "Relancer"
        );

        if (option == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {


        hero.setWalking(false);
        gameRunning = true;
        elapsedTimeInSeconds=0;

        chrono.start();
    }


}
