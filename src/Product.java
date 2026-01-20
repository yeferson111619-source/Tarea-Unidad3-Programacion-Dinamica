public class Product {
    public final String name;
    public final int value;   // beneficio
    public final int weight;  // peso/capacidad
    public final int cost;    // costo/presupuesto

    public Product(String name, int value, int weight, int cost) {
        this.name = name;
        this.value = value;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " (valor=" + value + ", peso=" + weight + ", costo=" + cost + ")";
    }
}
