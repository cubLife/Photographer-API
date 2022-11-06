package com.gmail.serhiisemiv;

public enum OrderStatus {
    NEW("New"), APPROVED("Approved"), COMPLETE("Complete");

    private final String status;

    OrderStatus(String status) {
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}
