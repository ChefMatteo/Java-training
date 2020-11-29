import java.util.Objects;

public abstract class Bevanda {
    private String code;
    private double price;

    public Bevanda(String code, double price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bevanda bevanda = (Bevanda) o;
        return Objects.equals(code, bevanda.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
