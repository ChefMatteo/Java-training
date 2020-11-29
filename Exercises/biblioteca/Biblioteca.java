public class Biblioteca {
    int[] values;

    public Biblioteca(int[] values){
        this.values = values;
    }

    public boolean esisteLibro(int indice){
        boolean flag = false;
        for(int i = 0; i < values.length; i++) {
            if (indice == values[i]) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public int[] getIndiciLibriOrdinati(){
        int[] copiaValues = values;
        int[] valuesOrdinato = new int[values.length];
        int contatore = Integer.MAX_VALUE;
        for(int j = 0; j < copiaValues.length; j++) {
            for (int i = 0; i < copiaValues.length; i++) {
                if (contatore > copiaValues[i]) {
                    contatore = copiaValues[i];
                }
            }
            for(int i =0; i<copiaValues.length;i++){
                if(contatore == copiaValues[i])
                    copiaValues[i] = Integer.MAX_VALUE;
            }
            valuesOrdinato[j] = contatore;
            contatore = Integer.MAX_VALUE;
        }
        return valuesOrdinato;
    }
}
