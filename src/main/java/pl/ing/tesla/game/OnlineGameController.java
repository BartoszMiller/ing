package pl.ing.tesla.game;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.game.dto.Clan;
import pl.ing.tesla.game.dto.OnlineGameRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OnlineGameController {

    @PostMapping("/onlinegame/calculate")
    public List<List<Clan>> orderClans(@RequestBody OnlineGameRequest onlineGameRequest) {
        List<Clan> clans = Arrays.stream(onlineGameRequest.clans()).sorted().toList();
        return groupClans(clans, onlineGameRequest.groupCount());
    }

    private List<List<Clan>> groupClans(List<Clan> clans, int groupCount) {
        List<List<Clan>> groups = new ArrayList<>();
        for (Clan clan : clans) {
            boolean added = false;
            for (List<Clan> group : groups) {
                int playersGroup = group.stream().mapToInt(Clan::numberOfPlayers).sum();
                if (playersGroup + clan.numberOfPlayers() <= groupCount) {
                    added = group.add(clan);
                    break;
                }
            }
            if (!added) {
                ArrayList<Clan> newGroup = new ArrayList<>();
                newGroup.add(clan);
                groups.add(newGroup);
            }
        }
        return groups;
    }
}
