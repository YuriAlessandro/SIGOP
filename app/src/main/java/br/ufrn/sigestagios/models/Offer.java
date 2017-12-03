package br.ufrn.sigestagios.models;

import java.io.Serializable;

public class Offer implements Serializable {
    private String description;
    private String unit;
    private int idUnit;
    private String email;

    public Offer(String description, String unit, int idUnit, String email) {
        this.description = description;
        this.unit = unit;
        this.idUnit = idUnit;
        this.email = email;
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
}
