package br.ufrn.sigestagios.models;

import java.io.Serializable;

public abstract class Offer implements Serializable {
    private String description;
    private String unit;
    private int idUnit;
    private String email;
    private boolean isFromSigaa;
    private long offerId;

    public Offer(long offerId, String description, String unit, int idUnit, String email, boolean isFromSigaa) {
        this.offerId = offerId;
        this.description = description;
        this.unit = unit;
        this.idUnit = idUnit;
        this.email = email;
        this.isFromSigaa = isFromSigaa;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFromSigaa(){
        return isFromSigaa;
    }

    public long getOfferId() {
        return offerId;
    }
}
