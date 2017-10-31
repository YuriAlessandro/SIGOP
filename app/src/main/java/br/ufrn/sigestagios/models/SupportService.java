package br.ufrn.sigestagios.models;

import java.io.Serializable;

/**
 * Created by joao on 31/10/17.
 */

public class SupportService extends Offer implements Serializable {
    //List<Courses> coursesOfInterest
    private String abbrevTerm;
    private int idOpportunity;

    public SupportService(String description, String term, String idTerm, String email, String abbrevTerm, int idOpportunity) {
        super(description, term, idTerm, email);
        this.abbrevTerm = abbrevTerm;
        this.idOpportunity = idOpportunity;
    }

    public String getAbbrevTerm() {
        return abbrevTerm;
    }

    public int getIdOpportunity() {
        return idOpportunity;
    }
}
