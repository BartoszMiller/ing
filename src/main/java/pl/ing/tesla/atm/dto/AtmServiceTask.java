package pl.ing.tesla.atm.dto;

import java.util.Comparator;

public record AtmServiceTask(int region, int atmId, AtmServiceTaskType requestType) implements Comparable<AtmServiceTask> {

    @Override
    public int compareTo(AtmServiceTask o) {
        return Comparator.comparingInt(AtmServiceTask::region)
                .thenComparingInt(p -> p.requestType().getPriority())
                .compare(this, o);
    }
}
