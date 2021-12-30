package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class RaceFeed extends Layout {

//    ArrayList<Racer> racers;
    RaceController raceController;

    public RaceFeed(RaceController raceController){
        super();
        this.raceController = raceController;
//        this.racers = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int numberOfRacers = raceController.getNumberOfRacers();
        ArrayList<Integer> racerPositions = raceController.getRacerPositions();
        ArrayList<Boolean> racersWinningStatus = raceController.getRacersWinningStatus();

        for (int i=0; i < numberOfRacers; i++) {
            if (racersWinningStatus.get(i)) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(
                Constants.START_X_CO_ORD + racerPositions.get(i),
                Constants.START_Y_CO_ORD + i * (Constants.SQUARE_SIZE + Constants.RACE_HEIGHT_GAP),
                Constants.SQUARE_SIZE,
                Constants.SQUARE_SIZE);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.raceController.startRace();
        Timer timer = new Timer(Constants.RENDERING_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }


// for non threaded racers
//    @Override
//    public void run() {
//        int numberOfRacers = racers.size();
//        for (int i=0; i < numberOfRacers; i++) {
//            try {
//                racers.get(i).move();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        repaint();
//    }
}
