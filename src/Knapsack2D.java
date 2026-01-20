import java.util.Arrays;

public class Knapsack2D {

    // ----------------------------
    // 1) Enfoque recursivo puro
    // f(i, w, b) = mejor valor usando items[0..i-1] con capacidad w y presupuesto b
    // ----------------------------
    public static int solveRecursive(Product[] items, int i, int w, int b) {
        if (i == 0 || w == 0 || b == 0) return 0;

        Product it = items[i - 1];

        // No tomar
        int best = solveRecursive(items, i - 1, w, b);

        // Tomar si cabe (peso y presupuesto)
        if (it.weight <= w && it.cost <= b) {
            int take = it.value + solveRecursive(items, i - 1, w - it.weight, b - it.cost);
            best = Math.max(best, take);
        }
        return best;
    }

    // ----------------------------
    // 2) Enfoque Top-Down (memoización)
    // ----------------------------
    public static int solveTopDown(Product[] items, int w, int b) {
        int n = items.length;
        int[][][] memo = new int[n + 1][w + 1][b + 1];
        for (int i = 0; i <= n; i++) {
            for (int cw = 0; cw <= w; cw++) {
                Arrays.fill(memo[i][cw], -1);
            }
        }
        return td(items, n, w, b, memo);
    }

    private static int td(Product[] items, int i, int w, int b, int[][][] memo) {
        if (i == 0 || w == 0 || b == 0) return 0;
        if (memo[i][w][b] != -1) return memo[i][w][b];

        Product it = items[i - 1];

        int best = td(items, i - 1, w, b, memo);
        if (it.weight <= w && it.cost <= b) {
            int take = it.value + td(items, i - 1, w - it.weight, b - it.cost, memo);
            best = Math.max(best, take);
        }

        memo[i][w][b] = best;
        return best;
    }

    // ----------------------------
    // 3) Enfoque Bottom-Up (DP iterativo)
    // dp[i][w][b]
    // ----------------------------
    public static int solveBottomUp(Product[] items, int W, int B) {
        int n = items.length;
        int[][][] dp = new int[n + 1][W + 1][B + 1];

        for (int i = 1; i <= n; i++) {
            Product it = items[i - 1];
            for (int w = 0; w <= W; w++) {
                for (int b = 0; b <= B; b++) {
                    // no tomar
                    dp[i][w][b] = dp[i - 1][w][b];

                    // tomar si cabe
                    if (it.weight <= w && it.cost <= b) {
                        dp[i][w][b] = Math.max(
                                dp[i][w][b],
                                it.value + dp[i - 1][w - it.weight][b - it.cost]
                        );
                    }
                }
            }
        }
        return dp[n][W][B];
    }

    // Reconstrucción (opcional pero te sube puntos): qué items se eligieron en Bottom-Up
    public static boolean[] reconstructBottomUp(Product[] items, int W, int B) {
        int n = items.length;
        int[][][] dp = new int[n + 1][W + 1][B + 1];

        for (int i = 1; i <= n; i++) {
            Product it = items[i - 1];
            for (int w = 0; w <= W; w++) {
                for (int b = 0; b <= B; b++) {
                    dp[i][w][b] = dp[i - 1][w][b];
                    if (it.weight <= w && it.cost <= b) {
                        dp[i][w][b] = Math.max(dp[i][w][b], it.value + dp[i - 1][w - it.weight][b - it.cost]);
                    }
                }
            }
        }

        boolean[] chosen = new boolean[n];
        int w = W, b = B;
        for (int i = n; i >= 1; i--) {
            if (dp[i][w][b] != dp[i - 1][w][b]) {
                chosen[i - 1] = true;
                w -= items[i - 1].weight;
                b -= items[i - 1].cost;
            }
        }
        return chosen;
    }
}
