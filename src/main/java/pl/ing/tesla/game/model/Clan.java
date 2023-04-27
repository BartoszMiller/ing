package pl.ing.tesla.game.model;

import java.util.Comparator;

public record Clan(int numberOfPlayers, int points) implements Comparable<Clan> {

    @Override
    public int compareTo(Clan o) {
        return Comparator.comparingInt(Clan::points)
                .thenComparingInt(Clan::numberOfPlayers)
                .reversed()
                .compare(this, o);
    }
}
