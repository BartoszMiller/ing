package pl.ing.tesla.atm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.atm.dto.Atm;
import pl.ing.tesla.atm.dto.AtmServiceTask;

import java.util.List;

@RestController
@RequestMapping("/atms")
public class AtmController {

    @PostMapping("/calculateOrder")
    public List<Atm> calculateAtmsOrder(@RequestBody List<AtmServiceTask> atmServiceTasks) {
        return atmServiceTasks.stream()
                .sorted()
                .map(r -> new Atm(r.region(), r.atmId()))
                .distinct()
                .toList();
    }
}
