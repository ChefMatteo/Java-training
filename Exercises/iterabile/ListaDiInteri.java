public class ListaDiInteri  implements Iterabile{
    int[]elementi;
    public int contatore = -1;

    public ListaDiInteri(int[] elementi) {
        this.elementi = elementi;
    }

    @Override
    public int getContatore() {
        return contatore;
    }

    @Override
    public Object next() {
        if(contatore == -1)
            contatore++;
        else {
            if (contatore < elementi.length-1)
                contatore++;
        }
        return elementi[contatore];
    }

    @Override
    public boolean hasNext() {
        if(contatore == (elementi.length-1))
            return false;
        else
            return true;
    }

    @Override
    public void reset() {
       contatore = -1;
    }
}
