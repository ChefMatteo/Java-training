package com.springexercise.postgreSQLexercise;


import com.springexercise.postgreSQLexercise.interfaces.*;
import com.springexercise.postgreSQLexercise.models.*;

import java.sql.*;

public class main {
  public static void main(String[] args) {
    Connection db = null;
    try {
      //Class.forName("org.postgresql.Driver");
      db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dataBase", "postgres",                             "Greta28898");

      Statement st = db.createStatement();

      //Studenti(id, id_indirizzo(Indirizzi),nome,cognome)
      st.execute("CREATE TABLE IF NOT EXISTS indirizzi (ID SERIAL   PRIMARY KEY , " +
              "     NOME  TEXT    NOT NULL)");

      st.execute("CREATE TABLE IF NOT EXISTS studenti (" +
              "     ID SERIAL   PRIMARY KEY , " +
              "     NOME  TEXT    NOT NULL," +
              "     COGNOME TEXT NOT NULL," +
              "     IDINDIRIZZO INT NOT NULL,"+
              "     FOREIGN KEY (IDINDIRIZZO) REFERENCES Indirizzo(ID),"+
              "     unique(NOME,COGNOME))");

      //Prenotazione(id(studente), id(esame)
      st.execute("CREATE TABLE IF NOT EXISTS prenotazioni(" +
              "     IDSTUDENTE INT NOT NULL,"+
              "     IDESAME INT NOT NULL,"+
              "     FOREIGN KEY (IDSTUDENTE) REFERENCES studente(id),"+
              "     FOREIGN KEY (IDESAME) REFERENCES esame(id),"+
              "     PRIMARY KEY (IDSTUDENTE, IDESAME))");

      //Esame(id, nome)
      st.execute("CREATE TABLE IF NOT EXISTS esami(" +
              "     ID SERIAL PRIMARY KEY," +
              "     NOME TEXT NOT NULL" +
              "     UNIQUE(NOME))");


      StudenteClass s = new StudenteClass("ajeje", "brazorf", null, db);

      System.out.println(s.getNome() + s.getCognome());

      db.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.print("Hello!");
  }
}
