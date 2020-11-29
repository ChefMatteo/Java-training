public abstract class Binari {
    Treno train = null;

    //COSTRUTTORE
    protected Binari(Treno train) {
        this.train = train;
    }

    //METODI
    public Binari getSuccessivo(){
        return train.binaryList.get((train.binaryList.indexOf(train.binary))+1);
    }
    public Binari percorri(){
        if(!train.arrived) {
            int counter = 0;
            if (getSuccessivo() instanceof BinarioCapolinea) {
                train.enterStation();
                for (Wagon w : train.wagonList) {
                    for (Passeggero p : w.passengersList) {
                        if (p.idArriveStation == ((BinarioCapolinea) getSuccessivo()).idStation) {
                            w.passengersList.remove(p);
                            counter++;
                        }
                    }
                }
                System.out.println("Il treno " + train.code + " è arrivato al capolinea " +
                        getSuccessivo() + " in cui sono scese " + counter + " persone");
                train.arrived = true;
                train.binary = getSuccessivo();
                return getSuccessivo();
            } else if (getSuccessivo() instanceof BinarioStazione) {
                train.enterStation();
                for (Wagon w : train.wagonList) {
                    for (Passeggero p : w.passengersList) {
                        if (p.idArriveStation == ((BinarioStazione) getSuccessivo()).idStation) {
                            w.passengersList.remove(p);
                            counter++;
                        }
                    }
                }
                System.out.println("Il treno " + train.code + " è arrivato alla stazione " +
                        ((BinarioStazione) getSuccessivo()).idStation + " in cui sono scese " + counter + " persone");
                train.start();
                train.binary = getSuccessivo();
                return getSuccessivo();
            } else {
                train.binary = getSuccessivo();
                System.out.println("Il treno " + train.code + " sta percorrendo il binario " + getSuccessivo());
                return getSuccessivo();
            }
        }
        else {
            System.out.println("Impossibile andare avanti: treno arrivato al capolinea!");
            return train.binary;
        }
    }

}
