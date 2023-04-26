package pl.ing.tesla.game.dto;

import java.util.Comparator;

public record Clan(int numberOfPlayers, int points) implements Comparable<Clan> {

    public double playerAvg() {
        return (double) points / numberOfPlayers;
    }

    @Override
    public int compareTo(Clan o) {
        return Comparator.comparingInt(Clan::points)
                .thenComparingDouble(Clan::playerAvg)
                .reversed()
                .compare(this, o);
    }
}
