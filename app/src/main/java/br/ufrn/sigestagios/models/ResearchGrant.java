package br.ufrn.sigestagios.models;

import java.io.Serializable;

/**
 * Created by joao on 31/10/17.
 */

public class ResearchGrant extends Offer implements Serializable {
    //knowledge area

    private int year;
    private String cpf_cnpj;
    private String responsible;
    private int positionsRemunerated;
    private int idProject;
    private int numberPositions;
    private int idWorkPlan;

    public ResearchGrant(String description, String term, String idTerm, String email, int year, String cpf_cnpj, String responsible, int positionsRemunerated, int idProject, int numberPositions, int idWorkPlan) {
        super(description, term, idTerm, email);
        this.year = year;
        this.cpf_cnpj = cpf_cnpj;
        this.responsible = responsible;
        this.positionsRemunerated = positionsRemunerated;
        this.idProject = idProject;
        this.numberPositions = numberPositions;
        this.idWorkPlan = idWorkPlan;
    }

    public int getYear() {
        return year;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getResponsible() {
        return responsible;
    }

    public int getPositionsRemunerated() {
        return positionsRemunerated;
    }

    public int getIdProject() {
        return idProject;
    }

    public int getNumberPositions() {
        return numberPositions;
    }

    public int getIdWorkPlan() {
        return idWorkPlan;
    }
}
