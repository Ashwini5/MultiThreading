package org.example;

import java.util.Random;

public class Racer implements Runnable {

    private Integer position;
    private Random random;
    private Boolean isWinner;
    private static Boolean isRaceWon = false;
    private static Object sharedLock = new Object();

    public Racer() {
        this.position = 0;
        this.random = new Random();
        this.isWinner = false;
    }

    public Boolean getIsWinner(){
        return this.isWinner;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void move() throws InterruptedException {
        if (this.position < Constants.END_X_DISTANCE) {
            this.position += random.nextInt(Constants.RACER_SPEED_MAX - Constants.RACER_SPEED_MIN) + Constants.RACER_SPEED_MIN;
            if (this.position >= Constants.END_X_DISTANCE) {
                synchronized (sharedLock) {
                    if (!isRaceWon) {
                        isRaceWon = isWinner = true;
                    }
                }
            }
            Thread.sleep(Constants.RACER_REFRESH_RATE_IN_MILLI_SECS);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.move();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
