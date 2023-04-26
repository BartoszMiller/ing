package pl.ing.tesla.atm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.atm.dto.Atm;
import pl.ing.tesla.atm.dto.AtmRequest;

import java.util.List;

@RestController
public class AtmController {

    @PostMapping("/atms/calculateOrder")
    public List<Atm> orderAtms(@RequestBody List<AtmRequest> atmRequests) {
        return atmRequests.stream()
                .sorted()
                .map(r -> new Atm(r.region(), r.atmId()))
                .distinct()
                .toList();
    }
}
