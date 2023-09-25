package vn.edu.iuh.fit.lab01.entities;

public enum AccountStatus {
    ACTIVE(1),
    DEACTIVE(0),
    REMOVE(-1);
    private int value;

    public int getValue() {
        return value;
    }

    AccountStatus(int value) {
        this.value = value;
    }
}
