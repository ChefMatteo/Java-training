package primoBot;

public enum ComandoPrimobot {
    SALUTA("ciao"),MONOPATTINO("🛴"),SCOOTER("\uD83D\uDEF5"),MOTO("\uD83C\uDFCD️"),TASTIERA("tastiera"),ERRORE("");
    private String valore;

    ComandoPrimobot(String s){
        valore = s;
    }

    public static ComandoPrimobot fromString(String s){
        for (ComandoPrimobot value : values()) {
            if(value.valore.equals(s))
                return value;
        }
        return ERRORE;
    }
}
