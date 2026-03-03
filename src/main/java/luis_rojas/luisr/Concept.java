package luis_rojas.luisr;

public class Concept {
    private final int code;
    private final String name;
    private final double price;

    public Concept(int code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
