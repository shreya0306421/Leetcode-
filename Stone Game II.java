class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int M = 1; M <= n; M++) {

                if (2 * M >= n - i) {
                    dp[i][M] = suffix[i];
                    continue;
                }

                int best = 0;

                
                for (int X = 1; X <= 2 * M && i + X <= n; X++) {

                  
                    int opponent = dp[i + X][Math.max(M, X)];

                    int current = suffix[i] - opponent;

                    best = Math.max(best, current);
                }

                dp[i][M] = best;
            }
        }

        return dp[0][1];
    }
}
