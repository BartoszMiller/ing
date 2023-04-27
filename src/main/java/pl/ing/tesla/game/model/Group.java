package pl.ing.tesla.game.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @JsonValue
    private final List<Clan> clans = new ArrayList<>();
    private int numberOfPlayers = 0;

    public Group(Clan clan) {
        add(clan);
    }

    public boolean add(Clan clan) {
        numberOfPlayers += clan.numberOfPlayers();
        return clans.add(clan);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
