public class Riparazione {
    String address;
    int priority;
    boolean assigned;
    boolean done = false;

    public Riparazione(){

    }
    public Riparazione(String address, int priority, boolean assigned) {
        this.address = address;
        setPriority(priority);
        this.assigned = assigned;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if(priority > 0 && priority <= 10)
            this.priority = priority;
        else
            System.out.println("Errore, numero non valido.");
    }

    public void concludi(){

    }
}
