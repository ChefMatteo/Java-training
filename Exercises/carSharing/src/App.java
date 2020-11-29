import java.util.*;

public class App {
    private static HashSet<User> usersList = new HashSet<>();
    private static HashSet<CarSharingPark> parksList = new HashSet<>();
    private static HashSet<Vehicle> vehiclesList = new HashSet<>();
    private static HashMap<User, Vehicle> rentalInProgress = new HashMap<>();

    //singleton
    private static App instance = null;
    private App() {
    }
    public static App getCarSharing() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    //metodi search
    public boolean searchUserByEmail(String email){
        boolean flag = false;
        for (User user : usersList) {
            if(user.email.equals(email)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public User searchUser(String email){
        User userSearched = null;
        if(searchUserByEmail(email)){
            for (User user : usersList) {
                if(user.email.equals(email)) {
                    userSearched = user;
                    break;
                }
            }
        }else{
            System.out.println("Utente non presente!");
        }
        return userSearched;
    }
    public boolean searchParkByName(String name){
        boolean flag = false;
        for (CarSharingPark carSharingPark : parksList) {
            if(carSharingPark.name.equals(name)){
                flag = true;
                break;
            }
        }
        return flag;
    }
    public boolean searchVehicleByPlate(String plate){
        boolean flag = false;
        for (Vehicle vehicle : vehiclesList) {
            if(vehicle.plate.equals(plate)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public Vehicle searchVehicle(String plate) {
        Vehicle vehicleToSearch = null;
        for (Vehicle vehicle : vehiclesList) {
            if(vehicle.plate.equals(plate)){
                vehicleToSearch = vehicle;
            }
        }
        if(vehicleToSearch == null)
            System.out.println("Veicolo null");
        return vehicleToSearch;
    }

    //metodi add
    public boolean addUser(String name, String email){
        if(!searchUserByEmail(email)) {
            User user = new User(name, email);
            usersList.add(user);
            return true;
        }else{
            System.out.println("Utente già registrato!");
            return false;
        }
    }
    public boolean addCarSharingPark(String name, int slots){
        if(!searchParkByName(name)){
            parksList.add(new CarSharingPark(name, slots));
            return true;
        }else{
            System.out.println("Parcheggio esistente!");
            return false;
        }
    }
    public boolean addVehicle(Vehicle vehicle){
        if(!searchVehicleByPlate(vehicle.plate)){
            vehiclesList.add(vehicle);
            return true;
        }else{
            System.out.println("Veicolo già presente!");
            return false;
        }
    }

    //metodi
    private void rentCar(int n, User user){
        Vehicle vehicleToRent = null;
        if(n == 1){ //se cerchiamo l'auto con più carburante...
            int count = 0;
            for (Vehicle vehicle : vehiclesList) { //cerco l'auto con più carburante
                if(vehicle.percentOfFuel>count && vehicle.status != Status.OCCUPIED && vehicle instanceof Car) {
                    count = vehicle.percentOfFuel;
                    vehicleToRent = vehicle;
                }
            }
        }
        else{ //...altrimenti cerco la prima auto libera
            for (Vehicle vehicle : vehiclesList) { //cerco la prima auto disponibile
                if (vehicle.status != Status.OCCUPIED && vehicle instanceof Car) {
                    vehicleToRent = vehicle;
                    break;
                }
            }
        }
        for (Vehicle vehicle : vehiclesList) { //cambio lo status del veicolo noleggiato in occupato
            if(vehicle.plate.equals(vehicleToRent.plate)) {
                vehicle.status = Status.OCCUPIED;
                break;
            }
        }
        rentalInProgress.put(user, vehicleToRent);
    }
    private void rentVan(int n, User user){
        Vehicle vehicleToRent = null;
        if(n == 1){ //se cerchiamo il furgone con più carburante...
            int count = 0;
            for (Vehicle vehicle : vehiclesList) { //cerco il furgone con più carburante
                if(vehicle.percentOfFuel>count && vehicle.status != Status.OCCUPIED && vehicle instanceof Van) {
                    count = vehicle.percentOfFuel;
                    vehicleToRent = vehicle;
                }
            }
        }
        else{ //...altrimenti cerco il primo furgone libero
            for (Vehicle vehicle : vehiclesList) { //cerco il primo furgone disponibile
                if (vehicle.status != Status.OCCUPIED && vehicle instanceof Van) {
                    vehicleToRent = vehicle;
                    break;
                }
            }
        }
        for (Vehicle vehicle : vehiclesList) { //cambio lo status del veicolo noleggiato in occupato
            if(vehicle.plate.equals(vehicleToRent.plate)) {
                vehicle.status = Status.OCCUPIED;
                break;
            }
        }
        rentalInProgress.put(user, vehicleToRent);
    }
    protected boolean addMoneyToUser(double money, String email){
        boolean flag = false;
        if(searchUserByEmail(email)){
            for (User user : usersList) {
                if(user.email.equals(email)){
                    user.wallet += money;
                    flag = true;
                }
            }
        }else
            System.out.println("Utente non registrato!");
        return flag;
    }
    public boolean rent(String email) {
        User user = searchUser(email);
        if (searchUser(email).wallet < 0.4) { //controllo se l'utente ha fondi sufficienti
            System.out.println("Credito insufficiente per noleggiare");
            return false;
        } else if (rentalInProgress.containsKey(searchUser(email))) { //controllo se l'utente ha altri noleggi attivi
            System.out.println("L'utente ha un noleggio già attivo!");
            return false;
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("vuoi noleggiare un auto o un furgone?\nDigita AUTO o FURGONE:");
            int replyN = 0;
            while (true) {
                String reply = sc.next().toLowerCase();
                if (reply.equals("auto")) {
                    replyN = 1;
                    break;
                } else if (reply.equals("furgone")) {
                    replyN = 2;
                    break;
                } else {
                    System.out.println("Comando errato: ripetere");
                }
            }
            if (replyN == 1) {
                replyN = 0;
                System.out.println("vuoi noleggiare l'auto con più autonomia?\nDigita SI o NO:");
                while (true) {
                    String reply = sc.next().toLowerCase();
                    if (reply.equals("si")) {
                        replyN = 1;
                        break;
                    } else if (reply.equals("no")) {
                        replyN = 2;
                        break;
                    } else {
                        System.out.println("Comando errato: ripetere");
                    }
                }
                rentCar(replyN, user);
                return true;
            } else {
                replyN = 0;
                System.out.println("vuoi noleggiare il furgone con più autonomia?\nDigita SI o NO:");
                while (true) {
                    String reply = sc.next().toLowerCase();
                    if (reply.equals("si")) {
                        replyN = 1;
                        break;
                    } else if (reply.equals("no")) {
                        replyN = 2;
                        break;
                    } else {
                        System.out.println("Comando errato: ripetere");
                    }
                }
                rentVan(replyN, user);
                return true;
            }
        }
    }
    public boolean finishRent(String email){
        CarSharingPark carSharingParkToPark = null;
        Scanner sc = new Scanner(System.in);
        User userWhoWantFinishRentInProgress = searchUser(email);
        //controllo se l'utente ha noleggi in corso
        if(!rentalInProgress.containsKey(userWhoWantFinishRentInProgress)){
            System.out.println("L'utente non ha noleggi attivi!");
            return false;
        }
        else{
            int minutesCanDriveWallet = (int)(userWhoWantFinishRentInProgress.wallet / rentalInProgress.get(searchUser(email)).price);
            int minutesCanDriveFuel = rentalInProgress.get(searchUser(email)).percentOfFuel / rentalInProgress.get(searchUser(email)).percentConsumptionForMinutes;
            System.out.println("Quanti minuti hai guidato?" +
                                "\nMax minuti possibili in base al wallet o al sono: " + minutesCanDriveWallet +
                                "\nMax minuti possibili in base al carburante sono: " + minutesCanDriveFuel);
            while (true){
                int reply = Integer.parseInt(sc.next());
                if(reply>minutesCanDriveWallet || reply <= 0 || reply > minutesCanDriveFuel){ //controllo se i minuti inseriti sono validi
                    System.out.println("Forse ti sei sbagliato... Riprova");
                }else{
                    for (Vehicle vehicle : vehiclesList) {
                        if(vehicle.plate.equals(searchVehicle(rentalInProgress.get(searchUser(email)).plate))){
                            vehicle.fuelConsumption(reply);
                        }
                    }
                    userWhoWantFinishRentInProgress.rentals.put(rentalInProgress.get(userWhoWantFinishRentInProgress), reply);
                    userWhoWantFinishRentInProgress.wallet -= reply*rentalInProgress.get(userWhoWantFinishRentInProgress).price;
                    System.out.println("Il noleggio è costato: " + reply*rentalInProgress.get(userWhoWantFinishRentInProgress).price + "€");
                    break;
                }
            }
            int replyN = 0;
            System.out.println("Vuoi parcheggiare in strada o cercare un parcheggio disponibile?\nDigita STRADA o PARCHEGGIO");
            while(true){
                String reply = sc.next().toLowerCase();
                if(reply.equals("parcheggio")){
                    replyN = 1;
                    break;
                }else if(reply.equals("strada")){
                    replyN = 2;
                    break;
                }else
                    System.out.println("Comando errato: ripetere");
            }
            //cerco un parcheggio disponibile
            if(replyN == 1){
                for (CarSharingPark carSharingPark : parksList) {
                    if(carSharingPark.slotsFree>0)
                        System.out.println("Parcheggio disponibile: " + carSharingPark.name);
                }
                System.out.println("Quale parcheggio preferisci?\nDigita il nome del parcheggio");
                while(true){
                    String reply = sc.next().toLowerCase();
                    boolean flag = false;
                    for (CarSharingPark carSharingPark : App.parksList) {
                        if(carSharingPark.name.toLowerCase().equals(reply)){
                            searchVehicle(rentalInProgress.get(userWhoWantFinishRentInProgress).plate).status = Status.PARKEDINPARK;
                            carSharingPark.park(rentalInProgress.get(userWhoWantFinishRentInProgress));
                            flag = true;
                            System.out.println("Parcheggio perfiiiieeeetto!");
                            break;
                        }
                    }
                    if(!flag)
                        System.out.println("Comando errato: ripetere");
                    else
                        break;
                }
            }
            //altrimenti parcheggia in strada
            else {
                searchVehicle(rentalInProgress.get(userWhoWantFinishRentInProgress).plate).status = Status.PARKEDONSTREET;
                System.out.println("Occhio alle multe che le paghi tu!");
            }
        }
        return true;
    }
    public void historyOfUser(String email){
        System.out.println("Ecco i noleggi effettuati da " + searchUser(email).name + ":\n" + searchUser(email).rentals.toString());
    }
    public void statusOfVehicles() {
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.status == Status.OCCUPIED)
                System.out.println("Il veicolo con targa " + vehicle.plate + " è noleggiato");
            else if (vehicle.status == Status.PARKEDINPARK) {
                for (CarSharingPark carSharingPark : parksList) {
                    if (carSharingPark.slots.contains(vehicle)) {
                        System.out.println("Il veicolo con targa " + vehicle.plate + " è parcheggiato allo sharingPark " + carSharingPark.name);
                        break;
                    }
                }
            } else
                System.out.println("Il veicolo con targa " + vehicle.plate + " è parcheggiato in strada");
        }
    }
    public void howManyMinutesCanDrive(String email){
        System.out.println("L'utente " + searchUser(email).name + " può guidare un auto per " + (int)(searchUser(email).wallet/0.4) +
                            " minuti e un furgone per "+ (int)(searchUser(email).wallet/0.6) + " minuti");
    }

    //classi nidificate
    private class User {
        String name;
        double wallet;
        String email;
        HashMap<Vehicle, Integer> rentals;

        //costruttore
        protected User(String name, String email){
            wallet = 0;
            this.email = email;
            this.name = name;
            rentals = new HashMap<>();
        }
    }
    private class CarSharingPark {
        String name;
        private int slotsSize;
        ArrayList<Vehicle> slots;
        int slotsFree;

        protected CarSharingPark(String name, int slotsSize) {
            this.name = name;
            this.slotsSize = slotsSize;
            slots = new ArrayList<>();
            slotsFree = slotsSize;
        }

        protected boolean park(Vehicle vehicle) {
            if (!slots.contains(vehicle) && slotsFree > 0) {
                slots.add(vehicle);
                slotsFree--;
                return true;
            }else
                return false;

        }

        protected boolean leavePark(Vehicle vehicle) {
            if (slots.contains(vehicle)) {
                slots.remove(vehicle);
                slotsFree++;
                return true;
            }else
                return false;
        }
    }

}
