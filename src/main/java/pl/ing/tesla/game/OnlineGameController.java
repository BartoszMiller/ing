package pl.ing.tesla.game;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.game.dto.Clan;
import pl.ing.tesla.game.dto.Group;
import pl.ing.tesla.game.dto.Players;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/onlinegame")
public class OnlineGameController {

    @PostMapping("/calculate")
    public List<Group> groupClans(@RequestBody Players players) {
        final List<Group> groups = new ArrayList<>();
        for (Clan clan : players.clans().stream().sorted().toList()) {
            boolean added = addClanToExistingGroups(players.groupCount(), groups, clan);
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
