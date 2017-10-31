package br.ufrn.sigestagios.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joao on 31/10/17.
 */

public class Internship implements Serializable {
    private String title;
    private String email;
    private String responsible;
    private String companyName;
    private int numberPositions;
    private int grantValue;
    private int auxTransport;
    private Date endOffer;
    private String description;

    public Internship(String description, String email, String companyName, String responsible, int numberPositions, int grantValue, int auxTransport, Date endOffer, String title) {
        this.description = description;
        this.email = email;
        this.companyName = companyName;
        this.responsible = responsible;
        this.numberPositions = numberPositions;
        this.grantValue = grantValue;
        this.auxTransport = auxTransport;
        this.endOffer = endOffer;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
