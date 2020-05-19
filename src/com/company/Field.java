package com.company;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class Field extends JPanel {
    private boolean paused;
    private boolean pause_radius;
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);    // Класс таймер отвечает за регулярную генерацию событий ActionEvent  // При создании его экземпляра используется анонимный класс,  // реализующий интерфейс
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            repaint();
        }
    });

    public Field() {
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball : balls) {
            ball.paint(canvas);
        }
    }

    public void addBall() {
        balls.add(new BouncingBall(this));
    }

    public synchronized void pause() {
        paused = true;
    }
    public synchronized void paused() {
        pause_radius = true;
    }


    public synchronized void resume() {
    paused = false;
     notifyAll();  }
    public synchronized void resumed() {
        pause_radius = false;
        notifyAll();  }

    public synchronized void canMove(BouncingBall ball,Color color ) throws
    InterruptedException {

        if(pause_radius && (color.getGreen() + color.getBlue()) < color.getRed() ) {
            wait();

        }
    if (paused) {
        wait();}

        }
    }