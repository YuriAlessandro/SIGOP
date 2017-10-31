package br.ufrn.sigestagios.models;

import java.io.Serializable;

public class Offer implements Serializable {
    private int year;
    private String description;
    private String responsible;
    private String term;
    private int vacanciesRemunerated;
    private int vacanciesVolunteers;

    public Offer(int year, String description, String responsible, String term, int vacanciesRemunerated, int vacanciesVolunteers) {
        this.year = year;
        this.description = description;
        this.responsible = responsible;
        this.term = term;
        this.vacanciesRemunerated = vacanciesRemunerated;
        this.vacanciesVolunteers = vacanciesVolunteers;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getTerm() {
        return term;
    }

    public int getVacanciesRemunerated() {
        return vacanciesRemunerated;
    }

    public int getVacanciesVolunteers() {
        return vacanciesVolunteers;
    }
}
