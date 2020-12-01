package com.springexercise.postgreSQLexercise.models;

import com.springexercise.postgreSQLexercise.interfaces.*;
import com.springexercise.postgreSQLexercise.interfaces.Studente;

import java.util.Set;

public class EsameClass implements Esame{
    private String nome;
    private Set<Studente> studenti;

    @Override
    public String getNome() {

        return nome;
    }
    @Override
    public Set<Studente> getStudenti() {
        return studenti;
    }
}
