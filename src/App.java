import java.util.Random;

public class App {

    public static void main(String[] args) {

        // ============================
        // DATASET 1 (pequeño)
        // ============================
        Product[] items1 = {
                new Product("A", 60, 10, 12),
                new Product("B", 100, 20, 25),
                new Product("C", 120, 30, 30),
                new Product("D", 70, 15, 18)
        };

        runAll(items1, 50, 55);

        // ============================
        // DATASET 2 (más grande)
        // ============================
        Product[] items2 = randomDataset(
                18,     // número de productos
                1, 120, // valor min y max
                1, 40,  // peso min y max
                1, 40,  // costo min y max
                123     // semilla
        );

        runAll(items2, 60, 60);
    }

    private static void runAll(Product[] items, int W, int B) {

        System.out.println("\n==============================");
        System.out.println("Capacidad(W)=" + W + "  Presupuesto(B)=" + B);
        System.out.println("Items:");

        for (Product p : items) {
            System.out.println("  - " + p);
        }

        // ----------------------------
        // 1) Recursivo
        // ----------------------------
        if (items.length <= 22) {
            long tRec = timeIt(() ->
                    Knapsack2D.solveRecursive(items, items.length, W, B)
            );
            int rRec = Knapsack2D.solveRecursive(items, items.length, W, B);
            System.out.println("\nRecursivo -> mejor valor: " + rRec + " | tiempo(ns): " + tRec);
        } else {
            System.out.println("\nRecursivo -> omitido (dataset grande)");
        }

        // ----------------------------
        // 2) Top-Down
        // ----------------------------
        long tTD = timeIt(() ->
                Knapsack2D.solveTopDown(items, W, B)
        );
        int rTD = Knapsack2D.solveTopDown(items, W, B);
        System.out.println("Top-Down  -> mejor valor: " + rTD + " | tiempo(ns): " + tTD);

        // ----------------------------
        // 3) Bottom-Up
        // ----------------------------
        long tBU = timeIt(() ->
                Knapsack2D.solveBottomUp(items, W, B)
        );
        int rBU = Knapsack2D.solveBottomUp(items, W, B);
        System.out.println("Bottom-Up -> mejor valor: " + rBU + " | tiempo(ns): " + tBU);

        // ----------------------------
        // Reconstrucción de solución
        // ----------------------------
        boolean[] chosen = Knapsack2D.reconstructBottomUp(items, W, B);

        System.out.println("\nSeleccion (Bottom-Up):");

        int totalW = 0;
        int totalB = 0;
        int totalV = 0;

        for (int i = 0; i < items.length; i++) {
            if (chosen[i]) {
                System.out.println("  [X] " + items[i]);
                totalW += items[i].weight;
                totalB += items[i].cost;
                totalV += items[i].value;
            }
        }

        System.out.println("Totales -> valor=" + totalV +
                " peso=" + totalW +
                " costo=" + totalB);
    }

    // ----------------------------
    // Medición de tiempo
    // ----------------------------
    private static long timeIt(Runnable task) {
        // calentamiento
        for (int i = 0; i < 3; i++) {
            task.run();
        }
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return end - start;
    }

    // ----------------------------
    // Generador de dataset aleatorio
    // ----------------------------
    private static Product[] randomDataset(
            int n,
            int vMin, int vMax,
            int wMin, int wMax,
            int cMin, int cMax,
            long seed
    ) {
        Random rnd = new Random(seed);
        Product[] items = new Product[n];

        for (int i = 0; i < n; i++) {
            int value = vMin + rnd.nextInt(vMax - vMin + 1);
            int weight = wMin + rnd.nextInt(wMax - wMin + 1);
            int cost = cMin + rnd.nextInt(cMax - cMin + 1);
            items[i] = new Product("P" + (i + 1), value, weight, cost);
        }
        return items;
    }
}
