package pl.ing.tesla.atm.model;

public enum AtmServiceTaskType {

    FAILURE_RESTART(1),
    PRIORITY(2),
    SIGNAL_LOW(3),
    STANDARD(4);

    private final int priority;

    AtmServiceTaskType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
