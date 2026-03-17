package com.example.weddingapp.enums;

public enum InvitationStatus {
    PENDING("Pendiente"),
    SENT("Enviada"),
    CONFIRMED("Confirmada"),
    DECLINED("Rechazada");

    private final String displayName;

    InvitationStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
