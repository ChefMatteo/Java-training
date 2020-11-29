public class Quadrato extends FormaGeometrica {
    int lato;

    public Quadrato(String nome, int lato){
        super.setNome(nome);
        this.lato = lato;
    }

    public int getPerimetro(int lato) {
        return super.getPerimetro(lato, lato);
    }
}
