package pl.ing.tesla.game.service;

import org.springframework.stereotype.Service;
import pl.ing.tesla.game.model.Clan;
import pl.ing.tesla.game.model.Group;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineGameService {

    public List<Group> groupClans(List<Clan> clans, int groupCount) {
        final List<Group> groups = new ArrayList<>();
        for (Clan clan : clans.stream().sorted().toList()) {
            boolean added = addClanToExistingGroups(groupCount, groups, clan);
            if (!added) {
                groups.add(new Group(clan));
            }
        }
        return groups;
    }

    private boolean addClanToExistingGroups(int groupCount, List<Group> groups, Clan clan) {
        boolean added = false;
        for (Group group : groups) {
            if (group.getNumberOfPlayers() + clan.numberOfPlayers() <= groupCount) {
                added = group.add(clan);
                break;
            }
        }
        return added;
    }
}
