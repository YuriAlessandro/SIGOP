package br.ufrn.sigestagios.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joao on 31/10/17.
 */

public class Internship extends Offer implements Serializable {
    private String title;
    private String responsible;
    private int numberPositions;
    private int grantValue;
    private int auxTransport;
    private Date endOffer;

    public Internship(String description, String email, String companyName, String responsible, int numberPositions, int grantValue, int auxTransport, Date endOffer, String title) {
        super(description, companyName, 0, email);
        this.responsible = responsible;
        this.numberPositions = numberPositions;
        this.grantValue = grantValue;
        this.auxTransport = auxTransport;
        this.endOffer = endOffer;
        this.title = title;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public int getNumberPositions() {
        return numberPositions;
    }

    public void setNumberPositions(int numberPositions) {
        this.numberPositions = numberPositions;
    }

    public int getGrantValue() {
        return grantValue;
    }

    public void setGrantValue(int grantValue) {
        this.grantValue = grantValue;
    }

    public int getAuxTransport() {
        return auxTransport;
    }

    public void setAuxTransport(int auxTransport) {
        this.auxTransport = auxTransport;
    }

    public Date getEndOffer() {
        return endOffer;
    }

    public void setEndOffer(Date endOffer) {
        this.endOffer = endOffer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
