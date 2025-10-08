package dev.animals;

import java.util.Random;

public final class Turtle extends Animal {
    private final String name = "Tortue";
    private final String icon = "🐢";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }

}
