package dev.animals;

import java.util.Random;

public sealed class Animal permits Turtle, Rabbit, Horse {
    private final double totalDistance = 100;
    private double position = 0;
    private String name;
    private String icon;
    private boolean hasWon = false;
    public Random rand = new Random();

    public Animal(String name, String icon) {
        this.name = name;
        this.icon = icon;
        RaceManager.getInstance().addAnimal(this);
    }

    /**
     * Starts the animal's movement.
     */
    public synchronized void run(){
        while (!RaceManager.getInstance().isRaceOver()) {
            this.position += rand.nextInt(10);

            if (this.position > totalDistance){
                this.position = totalDistance;
            }

            if (position >= totalDistance ) {
                RaceManager.getInstance().setRaceIsOver(true);
                RaceManager.getInstance().setWinner(this);
                Thread.currentThread().interrupt();
            } else {
                System.out.println(createPositionString());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Course terminée !");
                System.out.println("Vainqueur : " + RaceManager.getInstance().getWinner());
                System.out.println("Classement final :");
                for (Animal animal : RaceManager.getInstance().getAnimals())
                {
                    System.out.println(animal.createPositionString());
                }
            }
        }
    };

    /**
     * Creates a string representing the animal's position.
     *
     * @return A string representing the animal's position.
     */
    public String createPositionString(){
        StringBuilder positionString = new StringBuilder();
        int percent = (int)((this.position / this.totalDistance) * 100);
        positionString.append("-".repeat(Math.max(0, percent)));
        positionString.append(icon);
        positionString.append("-".repeat(Math.max(0, 100 - percent)));
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

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public synchronized void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    @Override
    public String toString() {
        return name + " " + icon;
    }
}