package br.ufrn.sigestagios.models;

import java.io.Serializable;

public class Offer implements Serializable {
    private String description;
    private String term;
    private String idTerm;
    private String email;

    public Offer(String description, String term, String idTerm, String email) {
        this.description = description;
        this.term = term;
        this.idTerm = idTerm;
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public String getTerm() {
        return term;
    }

    public String getIdTerm() {
        return idTerm;
    }

    public String getEmail() {
        return email;
    }
}
