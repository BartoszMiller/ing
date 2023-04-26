package pl.ing.tesla.atm.dto;

import java.util.Comparator;

public record AtmRequest(int region, int atmId, AtmRequestType requestType) implements Comparable<AtmRequest> {

    @Override
    public int compareTo(AtmRequest o) {
        return Comparator.comparingInt(AtmRequest::region)
                .thenComparingInt(p -> p.requestType().getPriority())
                .compare(this, o);
    }
}
