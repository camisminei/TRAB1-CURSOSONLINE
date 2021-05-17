package com.example.cursosonline5;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id;//id chave primária
    private String nome, email, cpf, telefone,nomeCurso, qtdHoras;

    public Aluno(){} //construtor vazio

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(String qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    @Override
    public String toString() {
        return nome.toString() + " Curso: " + nomeCurso.toString();
    }
}
