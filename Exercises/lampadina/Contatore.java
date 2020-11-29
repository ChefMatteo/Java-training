public class Contatore {

    private int conteggio;

    public Contatore(int a){
        conteggio = a;
    }

    public Contatore(){
        conteggio = 0;
    }

    public void incremento(int n){
        conteggio += n;
    }
    public int getConteggio(){
        return conteggio;
    }
    public void reset(){
        conteggio = 0;
    }
    public void reset(int n){
        conteggio = n;
    }

}
