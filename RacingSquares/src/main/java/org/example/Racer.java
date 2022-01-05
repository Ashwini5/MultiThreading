package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Racer implements Runnable {

    private Integer id;
    private static Integer idCounter = 0;
    private Integer position;
    private Random random;
//    private Boolean isWinner;
    private static AtomicInteger raceCompletedBy;
//    private static AtomicBoolean isRaceWon = new AtomicBoolean(false);
    private final Lock pauseRacerLock;
    private final Condition condition;
    private AtomicBoolean pauseRacer;
    private Integer rank;

    public Racer(Lock pauseRacerLock, Condition condition, AtomicBoolean pauseRacer) {
        this.id = idCounter++;
        this.pauseRacerLock = pauseRacerLock;
        this.condition = condition;
        this.pauseRacer = pauseRacer;
        this.position = 0;
        this.random = new Random();
//        this.isWinner = false;
        raceCompletedBy = new AtomicInteger(0);
        this.rank = 0;
    }

//    public Boolean getIsWinner(){
//        return this.isWinner;
//    }

    public Integer getId() {
        return this.id;
    }

    public Integer getRank() {
        return this.rank;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void move() throws InterruptedException {

        if (this.pauseRacer.get()) {
            pauseRacerLock.lock();
            try {
                condition.await();
            } finally {
                pauseRacerLock.unlock();
            }
        }

        if (this.position < Constants.END_X_DISTANCE) {
            this.position += random.nextInt(Constants.RACER_SPEED_MAX - Constants.RACER_SPEED_MIN) + Constants.RACER_SPEED_MIN;
            if (this.position >= Constants.END_X_DISTANCE) {
//                boolean updateStatus = isRaceWon.compareAndSet(false, true);
//                if(updateStatus) {
//                    isWinner = true;
//                }
                this.rank = raceCompletedBy.incrementAndGet();

                }
            }
            Thread.sleep(Constants.RACER_REFRESH_RATE_IN_MILLI_SECS);
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
