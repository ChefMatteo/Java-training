public abstract class FormaGeometrica {
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getArea(int base, int altezza){
        int area = base*altezza;

        return area;

    }

    public int getPerimetro(int lato1, int lato2){
        int perimetro = (lato1+lato2)*2;

        return perimetro;

    }

    public String toString(){
        String nome = "";


        return nome;
    }
}
