public class Tecnico {
    String name;
    StatusTecnico status = StatusTecnico.LIBERO;

    public void concludiLavoro(Riparazione riparazione){
        status = StatusTecnico.LIBERO;
        riparazione.done = true;
    }
}

