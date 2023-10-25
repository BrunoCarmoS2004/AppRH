package com.br.AppRH.AppRH.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.Column;
//Genereted value e genereted type, gerar um id automatico
import jakarta.persistence.GeneratedValue;
//Id, mostra q o campo q esta com @id é um id
import jakarta.persistence.Id;
//ManyToOne, um cantidato para muitas vagas 
import jakarta.persistence.ManyToOne;
//start validation, para validações
import jakarta.validation.constraints.NotEmpty;

//É porque o candidato é uma entidade da tabela
@Entity
public class Candidato {
    //Mostra para o código que isso é um id
    @Id
    //Gera um id aleatório
    @GeneratedValue
    private long id;
    //Para setar que esse numero tem que ser unico
    @Column(unique = true)
    private String rg;

    @NotEmpty
    private String nomeCandidato;

    @NotEmpty
    private String email;

    @ManyToOne
    private Vaga vaga;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
