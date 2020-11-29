public interface Operazioni {

    void start();
    void brake();
    void enterStation();
    boolean addW(Wagon wagon, Treno treno);
    boolean removeW(Wagon wagon, Treno treno);

}
