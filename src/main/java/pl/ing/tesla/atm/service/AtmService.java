package pl.ing.tesla.atm.service;

import org.springframework.stereotype.Service;
import pl.ing.tesla.atm.model.Atm;
import pl.ing.tesla.atm.model.AtmServiceTask;

import java.util.List;

@Service
public class AtmService {

    public List<Atm> calculateAtmsOrder(List<AtmServiceTask> atmServiceTasks) {
        return atmServiceTasks.stream()
                .sorted()
                .map(r -> new Atm(r.region(), r.atmId()))
                .distinct()
                .toList();
    }
}
