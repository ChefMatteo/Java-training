public class Squadra {
    int id;
    String nome;
    Giocatore[] rosaDiGiocatori = new Giocatore[3];
    int punteggio = 0;
    int golFatti=0;
    int golSubiti=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Giocatore[] getRosaDiGiocatori() {
        return rosaDiGiocatori;
    }

    public void setRosaDiGiocatori(Giocatore pg1, Giocatore pg2,Giocatore pg3) {
        rosaDiGiocatori[0] = pg1;
        rosaDiGiocatori[1] = pg2;
        rosaDiGiocatori[2] = pg3;

    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public int getGolFatti() {
        return golFatti;
    }

    public void setGolFatti(int golFatti) {
        this.golFatti = golFatti;
    }

    public int getGolSubiti() {
        return golSubiti;
    }

    public void setGolSubiti(int golSubiti) {
        this.golSubiti = golSubiti;
    }

    public Squadra(int id, String nome, Giocatore pg1, Giocatore pg2,Giocatore pg3) {
        this.id = id;
        this.nome = nome;
        setRosaDiGiocatori(pg1, pg2, pg3);
    }
    public Squadra(){}
}
