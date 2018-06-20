package br.ufrn.sigestagios.models;

import android.util.Log;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joao on 31/10/17.
 */

public class Internship extends Offer implements Serializable {
    private String title, phone, location;
    private String responsible;
    private int numberPositions;
    private int grantValue;
    private int auxTransport;

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    private String endOffer;
    boolean isFromSigaa;
    boolean isFromApi;

    public Internship(int offerId, String description, String email, String companyName, String responsible,
                      int numberPositions, int grantValue, int auxTransport, String endOffer,
                      String title, String phone, String location) {
        super(offerId, description, companyName, 0, email, false);
        Log.e("AAAAAAAA", String.valueOf(offerId));
        this.responsible = responsible;
        this.numberPositions = numberPositions;
        this.grantValue = grantValue;
        this.auxTransport = auxTransport;
        this.endOffer = endOffer;
        this.title = title;
        this.isFromSigaa = false;
        this.isFromApi = false;
        this.phone = phone;
        this.location = location;
    }

    public Internship(int offerId, String description, String title, int numberPositions, int grantValue,
                      int auxTransport, String endOffer){
        super(offerId, description, "", 0, "", false);
        this.numberPositions = numberPositions;
        this.grantValue = grantValue;
        this.auxTransport = auxTransport;
        this.endOffer = endOffer;
        this.title = title;
        this.isFromSigaa = true;
        this.isFromApi = false;
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

    public String getEndOffer() {
        return endOffer;
    }

    public void setEndOffer(String endOffer) {
        this.endOffer = endOffer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFromSigaa() { return isFromSigaa; }
}
