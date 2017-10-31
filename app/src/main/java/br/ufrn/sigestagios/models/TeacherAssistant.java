package br.ufrn.sigestagios.models;

import java.io.Serializable;

/**
 * Created by joao on 31/10/17.
 */

public class TeacherAssistant extends Offer implements Serializable {

    private int year;
    private String cpf_cnpj;
    private int idProject;
    private int idProjectTA;
    private String responsible;
    private int positionsRemunerated;
    private int positionsVolunteers;

    public TeacherAssistant(String description, String term, String idTerm, String email, int year, String cpf_cnpj, int idProject, int idProjectTA, String responsible, int positionsRemunerated, int positionsVolunteers) {
        super(description, term, idTerm, email);
        this.year = year;
        this.cpf_cnpj = cpf_cnpj;
        this.idProject = idProject;
        this.idProjectTA = idProjectTA;
        this.responsible = responsible;
        this.positionsRemunerated = positionsRemunerated;
        this.positionsVolunteers = positionsVolunteers;
    }

    public int getYear() {
        return year;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public int getIdProject() {
        return idProject;
    }

    public int getIdProjectTA() {
        return idProjectTA;
    }

    public String getResponsible() {
        return responsible;
    }

    public int getPositionsRemunerated() {
        return positionsRemunerated;
    }

    public int getPositionsVolunteers() {
        return positionsVolunteers;
    }
}
