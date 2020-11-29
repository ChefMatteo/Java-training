import java.util.HashSet;
import java.util.TreeMap;

public class Dizionario{
    TreeMap<Character, TreeMap<String, HashSet<String>>> dizionario = new TreeMap<>();

    //aggiungi lettera iniziale, parola e significato
    public boolean addParola(Character letter,  String parola, String significato) throws SignificatoEsistente{
        if(!dizionario.containsKey(letter)){
            TreeMap<String, HashSet<String>> mappaParola = new TreeMap<>();
            HashSet<String> significati = new HashSet<>();
            significati.add(significato);
            mappaParola.put(parola, significati);
            dizionario.put(letter, mappaParola);
            return true;
        }
        else{
            if(!dizionario.get(letter).containsKey(parola)){
                HashSet<String> significati = new HashSet<>();
                significati.add(significato);
                dizionario.get(letter).put(parola, significati);
                return true;
            }
            else{
                if(!dizionario.get(letter).get(parola).contains(significato)){
                    dizionario.get(letter).get(parola).add(significato);
                    return true;
                }
                else {
                    throw new SignificatoEsistente();
                }
            }
        }
    }



    //stampaDizionario con String.join
    public void stampaDizionarioJoin(){
        int contatore = 0;
        String risultatoFinale = "";
        for (Character lettera : dizionario.keySet()) {
            HashSet<String> listaSignificati = new HashSet<>();
            for(String parola : dizionario.get(lettera).keySet()){
                String significati = String.join(";\n", dizionario.get(lettera).get(parola));
                listaSignificati.add("\nParola" + ": "  + parola.toUpperCase() + "\n" + significati);
            }
            if(contatore == 0) {
                risultatoFinale += "Lettera: " + Character.toUpperCase(lettera) + " " + String.join(",", listaSignificati + "\n");
            }
            else
                risultatoFinale += "\nLettera: " + Character.toUpperCase(lettera) + " " + String.join(",", listaSignificati + "\n");
            contatore++;
        }
        System.out.println(risultatoFinale);
    }

    //stampaDizionario senza String.join
    public void stampaDizionario() throws SignificatoEsistente {
        for (Character lettera : dizionario.keySet()) {
            int contatoreParola = 1;
            for (String parola : dizionario.get(lettera).keySet()) {
                int sizeParole = dizionario.get(lettera).size();
                for (int i = 0; i < dizionario.get(lettera).get(parola).toArray().length; i++) {
                    if (sizeParole == 1) {
                        if (dizionario.get(lettera).get(parola).toArray().length == 1)
                            System.out.println("Lettera: " + Character.toUpperCase(lettera) + "\nParola: " + parola.toUpperCase() + " (significato" + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + ")");
                        else if (i == 0 && dizionario.get(lettera).get(parola).toArray().length > 1)
                            System.out.print("Lettera: " + Character.toUpperCase(lettera) + "\nParola: " + parola.toUpperCase() + " (significato" + (i + 1) + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + "; ");
                        else if (i > 0 && i < dizionario.get(lettera).get(parola).toArray().length - 1)
                            System.out.print("significato" + (i + 1) + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + "; ");
                        else
                            System.out.println("significato" + (i + 1) + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + ")");
                    } else {
                        if (dizionario.get(lettera).get(parola).toArray().length == 1)
                            System.out.println("Parola" + contatoreParola + ": " + parola.toUpperCase() + " (significato" + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + ")");
                        else if (i == 0 && dizionario.get(lettera).get(parola).toArray().length > 1)
                            System.out.print("Lettera: " + Character.toUpperCase(lettera) + "\nParola"+ contatoreParola + ": " + parola.toUpperCase() + " (significato" + (i + 1) + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + "; ");
                        else if (i > 0 && i < dizionario.get(lettera).get(parola).toArray().length - 1)
                            System.out.print("significato" + (i + 1) + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + "; ");
                        else
                            System.out.print("significato" + (i + 1) + ": " + (dizionario.get(lettera).get(parola).toArray()[i]) + ")\n");
                    }
                }
                contatoreParola++;
            }
        }
    }


    public static void main(String[] args) throws SignificatoEsistente {
        Dizionario dizionario = new Dizionario();
        dizionario.addParola('c', "calcio", "sport");
        dizionario.addParola('c', "calcio", "elemento chimico");
        dizionario.addParola('c', "castoro", "animale brutto");
        dizionario.addParola('c', "calcio", "trasmettere forza cinetica attraverso il movimento volontario di una gamba");
        dizionario.addParola('a', "assonnato", "stato in cui versava il developer di questo codice");

        System.out.println("stampaDizionario SENZA String.join");
        dizionario.stampaDizionario();
        System.out.println("\n"+(char) 27 + "[33m" + "stampaDizionario CON String.join");
        dizionario.stampaDizionarioJoin();
    }



}
