package br.ufrn.sigestagios.models;

import java.io.Serializable;

/**
 * Created by joao on 31/10/17.
 */

public class AssociatedAction extends Offer implements Serializable {
    private int year;
    private int cpf_cnpj;
    private String responsible;
    private int positionsRemunerated;
    private int idProject;

    public AssociatedAction(String description, String term, int idTerm, String email, int year, int cpf_cnpj, String responsible, int positionsRemunerated, int idProject) {
        super(description, term, idTerm, email);
        this.year = year;
        this.cpf_cnpj = cpf_cnpj;
        this.responsible = responsible;
        this.positionsRemunerated = positionsRemunerated;
        this.idProject = idProject;
    }

    public int getYear() {
        return year;
    }

    public int getCpf_cnpj() {
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
}
