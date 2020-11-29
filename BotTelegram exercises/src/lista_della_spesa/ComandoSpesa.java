package lista_della_spesa;

public enum ComandoSpesa {
    START("/start"),ACCESO("acceso"),AGGIUNGI("aggiungi"),RIMUOVI("rimuovi"),LISTA("lista della spesa"),SVUOTA("svuota lista"),ERRORE("");
    private String value;

    ComandoSpesa(String s){
        value = s;
    }

    public static ComandoSpesa fromString(String s){
        for (ComandoSpesa value : values()) {
            if(value.value.equals(s.toLowerCase())) {
                return value;
            }
        }
        return ERRORE;
    }


}
