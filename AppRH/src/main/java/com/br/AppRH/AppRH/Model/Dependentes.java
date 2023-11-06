package com.br.AppRH.AppRH.Model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

@Entity
public class Dependentes {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String cpf;

    @ManyToOne
    private Funcionario funcionario;

    private String nome;
    private String dataNascimento;

    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
