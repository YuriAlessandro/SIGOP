package br.ufrn.sigestagios.models;

import java.io.Serializable;

/**
 * Created by joao on 31/10/17.
 */

public class SupportService extends Offer implements Serializable {
    //List<Courses> coursesOfInterest
    private String abbrevTerm;
    private int idOpportunity;

    public SupportService(String description, String term, int idTerm, String email, String abbrevTerm, int idOpportunity) {
        super(0, description, term, idTerm, email, true);
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
