package dev;

import dev.animals.Animal;

public class Main {
    public static void main(String[] args) {
        boolean gameOver = false;

        Animal turtle = new Animal("Tortue", "🐢");
        Animal rabbit = new Animal("Lapin", "🐇");
        Animal horse = new Animal("Cheval", "🐎");

        Thread t1 = new Thread(turtle::run);
        Thread t2 = new Thread(rabbit::run);
        Thread t3 = new Thread(horse::run);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Course terminée !");
        System.out.println("Classement final :");
        System.out.println(turtle.createPositionString());
        System.out.println(rabbit.createPositionString());
        System.out.println(horse.createPositionString());

    }
}