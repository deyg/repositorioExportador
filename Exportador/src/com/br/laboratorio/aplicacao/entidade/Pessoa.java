package com.br.laboratorio.aplicacao.entidade;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {

    private String id;
    private String nome;
    private Integer anoNascimento;

    public Pessoa() {
    }

    public List<Pessoa> getPessoas() {
        return new ArrayList<>() ;
    }
    
    public Pessoa getPessoa() {
        return new Pessoa() ;
    }

    public Pessoa(String id, String nome, Integer anoNascimento) {
        super();
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
}
