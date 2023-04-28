package pl.ing.tesla.game.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.game.model.Group;
import pl.ing.tesla.game.model.Players;
import pl.ing.tesla.game.service.OnlineGameService;

import java.util.List;

@RestController
@RequestMapping("/onlinegame")
public class OnlineGameController {

    private final OnlineGameService onlineGameService;

    public OnlineGameController(OnlineGameService onlineGameService) {
        this.onlineGameService = onlineGameService;
    }

    @PostMapping("/calculate")
    public List<Group> groupClans(@RequestBody Players players) {
        return onlineGameService.groupClans(players.clans(), players.groupCount());
    }
}
