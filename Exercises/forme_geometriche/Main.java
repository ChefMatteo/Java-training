public class Main {
    public static void main(String[] args) {
        Quadrato quadrato = new Quadrato("quadrato", 5);
        Rettangolo rettangolo = new Rettangolo("rettangolo", 5, 10);

        System.out.println(quadrato.getPerimetro(quadrato.lato) + "cm");
        System.out.println(quadrato.getArea(quadrato.lato, quadrato.lato)+ "cm²");

        System.out.println(rettangolo.getPerimetro(rettangolo.b, rettangolo.h)+ "cm");
        System.out.println(rettangolo.getArea(rettangolo.b, rettangolo.h)+ "cm²");
        System.out.println(rettangolo.getNome());
        System.out.println(quadrato.getNome());
    }
}
