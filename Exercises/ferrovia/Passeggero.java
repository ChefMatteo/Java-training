public abstract class Passeggero {
    String name;
    String ticket;
    int wagonAssigned;
    int idArriveStation;
    Treno train = null;

    //COSTRUTTORE
    public Passeggero(String name, String ticket, int wagonAssigned, int idArriveStation) {
        this.name = name;
        this.ticket = ticket;
        this.wagonAssigned = wagonAssigned;
        this.idArriveStation = idArriveStation;
    }

}
