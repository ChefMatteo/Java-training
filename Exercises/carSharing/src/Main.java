import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static void action(int instruction) {
        if (instruction == 1) {
            System.out.println("addUser\nInserisci il nome");
            String name = sc.next().toLowerCase();
            System.out.println("Inserisci l'email");
            String plate = sc.next().toLowerCase();
            App.getCarSharing().addUser(name, plate);
        } else if (instruction == 2) {
            System.out.println("addCarSharingPark\nInserisci il nome");
            String name = sc.next().toLowerCase();
            System.out.println("Inserisci il numero di posti");
            int numberOfParks = Integer.parseInt(sc.next());
            App.getCarSharing().addCarSharingPark(name, numberOfParks);
        } else if (instruction == 3) {
            String name;
            System.out.println("addVehicle\nInserisci tipo (AUTO o FURGONE)");
            while (true) {
                name = sc.next().toLowerCase();
                if (name.equals("auto"))
                    break;
                else if (name.equals("furgone"))
                    break;
                else
                    System.out.println("Comando errato: ripetere");
            }
            System.out.println("Inserisci la targa (AA111AA)");
            String plate = sc.next().toLowerCase();
            if (name.equals("auto"))
                App.getCarSharing().addVehicle(new Car(plate));
            else
                App.getCarSharing().addVehicle(new Van(plate));
        } else if (instruction == 4) {
            System.out.println("addMoneyToUser\nInserisci la quantità di denaro");
            double money = Double.parseDouble(sc.next());
            System.out.println("Inserisci l'email");
            String email = sc.next().toLowerCase();
            App.getCarSharing().addMoneyToUser(money, email);
        } else if (instruction == 5) {
            System.out.println("rent\nInserisci l'email");
            String email = sc.next().toLowerCase();
            App.getCarSharing().rent(email);
        } else if (instruction == 6) {
            System.out.println("finishRent\nInserisci l'email");
            String email = sc.next().toLowerCase();
            App.getCarSharing().finishRent(email);
        } else if (instruction == 7) {
            System.out.println("historyOfUser\nInserisci l'email");
            String email = sc.next().toLowerCase();
            App.getCarSharing().historyOfUser(email);
        } else if (instruction == 8) {
            System.out.println("statusOfVehicles");
            App.getCarSharing().statusOfVehicles();
        } else if (instruction == 9) {
            System.out.println("howManyMinutesCanDrive\nInserisci l'email");
            String email = sc.next().toLowerCase();
            App.getCarSharing().howManyMinutesCanDrive(email);
        } else if (instruction == 10)
            commands();
    }
    static void commands() {
        System.out.println("\n        add's methods\n" +
                "        1 for addUser (String name, String email)\n" +
                "        2 for addCarSharingPark (String name, int slots)\n" +
                "        3 for addVehicle (Vehicle vehicle)\n" +
                "        \n" +
                "        methods\n" +
                "        4 for addMoneyToUser (double money, String email)\n" +
                "        5 for rent (String email)\n" +
                "        6 for finishRent (String email)\n" +
                "        7 for historyOfUser (String email)\n" +
                "        8 for statusOfVehicles()\n" +
                "        9 for howManyMinutesCanDrive (String email)\n" +
                "        \n" +
                "        10 for commands\n" +
                "        11 for EXIT");
    }
    public static void main(String[] args) {
        System.out.println("Benvenuto in CarSharing!\n" +
                "Inserisci il primo utente, il primo parcheggio e il primo veicolo\n" +
                "questi sono i comandi\n(puoi richiamare la lista digitando 10)");
        commands();
        action(1);
        action(2);
        action(3);
        while (true) {
            System.out.println("Inserire comando");
            int instruction = Integer.parseInt(sc.next());
            if (instruction == 11) {
                break;
            } else
                action(instruction);
        }
    }
}