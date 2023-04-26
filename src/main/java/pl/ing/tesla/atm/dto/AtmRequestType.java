package pl.ing.tesla.atm.dto;

public enum AtmRequestType {

    FAILURE_RESTART(1),
    PRIORITY(2),
    SIGNAL_LOW(3),
    STANDARD(4);

    private final int priority;

    AtmRequestType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
