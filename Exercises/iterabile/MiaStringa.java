public class MiaStringa implements Iterabile{
    char[] string;
    public int contatore = -1;

    public int getContatore() {
        return contatore;
    }

    public MiaStringa(String s){
        string = s.toCharArray();
    }

    @Override
    public Object next() {
        if(contatore == -1) {
            contatore++;
            return string[contatore];
        }
        else {
            if (contatore < string.length-1)
                contatore++;
        }
        return string[contatore];
    }

    @Override
    public boolean hasNext() {
        if(contatore == (string.length-1))
            return false;
        else
            return true;
    }

    @Override
    public void reset() {
        contatore = -1;
    }
}
