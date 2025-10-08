package dev.animals;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public sealed class Animal permits Turtle, Rabbit, Horse {
    private final double totalDistance = 100;
    private double position = 0;
    private String name;
    private String icon;
    public Random rand = new Random();

    public Animal() {
        RaceManager.getInstance().addAnimal(this);
    }

    /**
     * Starts the animal's movement.
     */
    public synchronized void run(){

//        Check of the race is over
        while (!RaceManager.getInstance().isRaceOver()) {

//            increment position with a random integer
            this.position += rand.nextInt(10);

            checkPosition();

//            try catch block to determine the interval of each call of the run method
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
//                The catch block is triggered when the thread is interrupted by the checkPosition method
                RaceManager.getInstance().endRace();
            }
        }
    };

    public void checkPosition(){
//            Check if position is equals to or exceeds the total distance of the race
        if (position >= totalDistance ) {
//                If so, set the race as over and the winner in the RaceManager singleton instance and interrupt the thread
            this.position = totalDistance;
            RaceManager.getInstance().setRaceIsOver(true);
            RaceManager.getInstance().setWinner(this);
            RaceManager.getInstance().addWinner(this);
            Thread.currentThread().interrupt();
        } else {
//                If not, print the position of the animal
            System.out.println(createPositionString());
        }
    }

    /**
     * Creates a string representing the animal's position.
     *
     * @return A string representing the animal's position.
     */
    public String createPositionString(){
        int percent = (int)((this.position / this.totalDistance) * 100);
        String positionString = "-".repeat(Math.max(0, percent)) +
                this.getIcon() +
                "-".repeat(Math.max(0, 100 - percent));
        return (this.getName() + " : " + positionString + percent + "%");
    }
    public double getTotalDistance() {
        return totalDistance;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getIcon();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Animal animal)) return false;
        return Double.compare(position, animal.position) == 0 && Objects.equals(name, animal.name) && Objects.equals(icon, animal.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalDistance, position, name, icon);
    }
}