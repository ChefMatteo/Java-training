package com.springexercise.postgreSQLexercise.models;

import com.springexercise.postgreSQLexercise.interfaces.*;


import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class StudenteClass implements Studente {
    //Attributi
    private int idStudente;
    private String nome;
    private String cognome;
    private Integer idIndirizzo;
    private Indirizzo indirizzo = null;
    private Connection db = null;
    private Set<Esame> esami = null;


    //Costruttore getFromDatabase
    public StudenteClass(Connection db, Integer idStudente) throws SQLException {
        ResultSet resultSet = db.createStatement().executeQuery("SELECT * FROM studente WHERE id = " + idStudente + ";");
        this.idStudente = idStudente;
        nome = resultSet.getString("nome");
        cognome = resultSet.getString("cognome");
        idIndirizzo = resultSet.getInt("idindirizzo");
    }

    //costruttore
    public StudenteClass(String nome, String cognome, Integer idIndirizzo, Connection db) throws SQLException {
        this.nome = nome;
        this.cognome = cognome;
        this.idIndirizzo = idIndirizzo;
        this.db = db;
        idStudente = saveOnDB(nome, cognome, idIndirizzo);
    }

    //metodi
    private int saveOnDB(String nome, String cognome, Integer idIndirizzo) throws SQLException {
        ResultSet resultSet = db.createStatement().executeQuery("INSERT INTO studenti(nome, cognome, idindirizzo) " +
                                               "VALUES ("+ nome +","+ cognome +","+idIndirizzo+") RETURNING id;");
        return resultSet.getInt("id");
    }


    //getter
    @Override
    public Integer id() {
        return idStudente;
    }

    @Override
    public String getNome() {
        ResultSet resultSet = null;
        String risultato = "";

        try {
            resultSet = db.createStatement().executeQuery("SELECT nome FROM Studente AS s WHERE s.ID = " + this.idStudente);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            while(resultSet.next()){
                risultato += resultSet.getString("nome") + " ";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return risultato;
    }

    @Override
    public String getCognome() {
        ResultSet resultSet = null;
        String risultato = "";

        try {
            resultSet = db.createStatement().executeQuery("SELECT cognome FROM Studente AS s WHERE s.ID = " + this.idStudente);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            while(resultSet.next()){
                risultato += resultSet.getString("cognome") + " ";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return risultato;
    }

    @Override
    public Indirizzo getIndirizzo() {
        ResultSet resultSet = null;
        Indirizzo risultato = null;

        try {
            resultSet = db.createStatement().executeQuery("SELECT indirizzo FROM Studente AS s WHERE s.ID = " + this.idStudente);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            while(resultSet.next()){
                risultato = resultSet.getObject("indirizzo", Indirizzo.class);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return risultato;
    }

    @Override
    public Set<Esame> getEsami() {
        return null;
    }
}
