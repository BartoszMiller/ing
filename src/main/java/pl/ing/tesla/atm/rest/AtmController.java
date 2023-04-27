package pl.ing.tesla.atm.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.atm.model.Atm;
import pl.ing.tesla.atm.model.AtmServiceTask;
import pl.ing.tesla.atm.service.AtmService;

import java.util.List;

@RestController
@RequestMapping("/atms")
public class AtmController {

    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/calculateOrder")
    public List<Atm> calculateAtmsOrder(@RequestBody List<AtmServiceTask> atmServiceTasks) {
        return atmService.calculateAtmsOrder(atmServiceTasks);
    }
}
