package com.br.AppRH.AppRH.Model;
//Transforma os objetos em binarios e faz o inverso
import java.io.Serializable;
//biblioteca para lista
import java.util.List;

import org.hibernate.annotations.Cascade;

//cascade type, Para quando deletar uma vaga, ele deletar tudo ligado a ela
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
//Genereted value e genereted type, gerar um id automatico
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//Id, mostra q o campo q esta com @id é um id
import jakarta.persistence.Id;
//onetomany, uma vaga para muitos candidados
import jakarta.persistence.OneToMany;
//start validation, para validações
import jakarta.validation.constraints.NotEmpty;

//É porque o candidato é uma entidade da tabela
@Entity
public class Vaga implements Serializable{
    //Quer dizer q um atributo faz um controle de versionamento == indica se a versao do objeto é compativel com a da classe 
    private static final long serialVersionUID = 1L;
    //codigo da vaga
    @Id
    //o codigo da vaga sera gerado automaticamente
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;
    @NotEmpty
    private String data;
    @NotEmpty
    private String salario;
    //ligação entre lista de candidado e vaga
    //quando eu deletar uma vaga vai deletar tbm os candidatos
    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidato> candidatos;

    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
    public List<Candidato> getCandidatos() {
        return candidatos;
    }
    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
