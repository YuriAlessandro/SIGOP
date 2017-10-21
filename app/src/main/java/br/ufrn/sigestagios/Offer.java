package br.ufrn.sigestagios;

import java.io.Serializable;

public class Offer implements Serializable {
    private int year;
    private String description;
    private String responsible;
    private String unit;
    private int vacanciesRemunerated;
    private int vacanciesVolunteers;

    public Offer(int year, String description, String responsible, String unit, int vacanciesRemunerated, int vacanciesVolunteers) {
        this.year = year;
        this.description = description;
        this.responsible = responsible;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public int getVacanciesRemunerated() {
        return vacanciesRemunerated;
    }

    public int getVacanciesVolunteers() {
        return vacanciesVolunteers;
    }
}
