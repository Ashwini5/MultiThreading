package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.example.RaceController.RacerPositions;

public class RaceFeed extends Layout {

//    ArrayList<Racer> racers;
    RaceController raceController;
    Timer timer;
    ArrayList<Image> carImages;
    Boolean isInitialized = true;

    public RaceFeed(RaceController raceController){
        super();
        this.raceController = raceController;
        this.loadCarImages();
        this.timer = new Timer(Constants.RENDERING_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        this.isInitialized = true;
//        this.racers = new ArrayList<>();
    }

    private void loadCarImages() {
        this.carImages = new ArrayList<>();
        this.carImages.add(new ImageIcon(getClass().getResource("/green_car.png")).getImage());
        this.carImages.add(new ImageIcon(getClass().getResource("/blue_car.png")).getImage());
//        this.carImages.add(new ImageIcon(getClass().getResource("/grey_car.png")).getImage());
        this.carImages.add(new ImageIcon(getClass().getResource("/orange_car.png")).getImage());
        this.carImages.add(new ImageIcon(getClass().getResource("/purple_car.png")).getImage());
        this.carImages.add(new ImageIcon(getClass().getResource("/red_car.png")).getImage());
//        this.carImages.add(new ImageIcon(getClass().getResource("/white_car.png")).getImage());
        this.carImages.add(new ImageIcon(getClass().getResource("/yellow_car.png")).getImage());
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!isInitialized) { return; }
        super.paintComponent(g);
        int numberOfRacers = raceController.getNumberOfRacers();
        RacerPositions racerPositions = raceController.getRacerPositions();
//        ArrayList<Boolean> racersWinningStatus = raceController.getRacersWinningStatus();
/*        ArrayList<Integer> racerIndices = new ArrayList<>();
//        for (Integer i=0; i < racerPositions.size(); i++) {
//            racerIndices.add(i);
//        }
//        racerIndices = (ArrayList<Integer>) racerIndices.stream().sorted(Comparator.comparingInt(racerPositions::get)).collect(Collectors.toList());
//        Collections.reverse(racerIndices);*/


        for (int i=0; i < numberOfRacers; i++) {
            ImmutablePair<Integer, Integer> racerPosition = racerPositions.get(i);
            if (racerPosition.left == 1 && racerPosition.right >= Constants.END_X_DISTANCE) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
//            g.fillRect(
//                Constants.START_X_CO_ORD + racerPosition.right,
//                Constants.START_Y_CO_ORD + i * (Constants.SQUARE_SIZE + Constants.RACE_HEIGHT_GAP),
//                Constants.SQUARE_SIZE,
//                Constants.SQUARE_SIZE);
//            g.setColor(Color.GREEN);
//            g.setFont(new Font(null, 0, 20));
//            g.drawString(
//                String.valueOf(racerPosition.left) + " : " + String.valueOf(racerPosition.right),
//                Constants.START_X_CO_ORD + racerPosition.right + 5,
//                Constants.START_Y_CO_ORD + i * (Constants.SQUARE_SIZE + Constants.RACE_HEIGHT_GAP) + 15);
            g.drawImage(
                    this.carImages.get(i),
                    Constants.START_X_CO_ORD + racerPosition.right,
                    Constants.START_Y_CO_ORD + i * (Constants.SQUARE_SIZE + Constants.RACE_HEIGHT_GAP),
                    Constants.SQUARE_SIZE,
                    Constants.SQUARE_SIZE,
                    this);
            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font(null, 1, 22));
            g.drawString(
                    String.valueOf(racerPosition.left) + " : " + String.valueOf(racerPosition.right),
                    Constants.START_X_CO_ORD + racerPosition.right + 5,
                    Constants.START_Y_CO_ORD + i * (Constants.SQUARE_SIZE + Constants.RACE_HEIGHT_GAP));
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.getStartButton()) {
            this.raceController.startRace();
            this.timer.start();
        } else if (e.getSource() == this.getPauseButton()) {
            this.timer.stop();
            this.raceController.pauseRace();
        } else if (e.getSource() == this.getUnpauseButton()) {
            this.timer.start();
            this.raceController.unpauseRace();
        }
    }


}
