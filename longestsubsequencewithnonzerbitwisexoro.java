class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        // XOR of entire array is non-zero
        if (xor != 0) {
            return nums.length;
        }

        // All elements are zero
        if (!hasNonZero) {
            return 0;
        }

        // Total XOR is zero, but there is a non-zero element
        return nums.length - 1;
    }
}