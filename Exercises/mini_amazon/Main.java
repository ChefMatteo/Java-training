public class Main {
    public static void main(String[] args) {
        Utente utente = new Utente("Marco");
        Amazon.getInstance().registrazione(utente);
    }
}
