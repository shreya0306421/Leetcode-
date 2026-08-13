class longestsubstringifonerepeatingcharacter {

    static class Node {
        int len;
        int prefix;
        int suffix;
        int max;
        char leftChar;
        char rightChar;

        Node(int len, int prefix, int suffix, int max,
             char leftChar, char rightChar) {
            this.len = len;
            this.prefix = prefix;
            this.suffix = suffix;
            this.max = max;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }

    Node[] tree;
    char[] arr;

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(1, 1, 1, 1, arr[l], arr[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        int len = a.len + b.len;

        int prefix = a.prefix;
        if (a.prefix == a.len && a.rightChar == b.leftChar) {
            prefix = a.len + b.prefix;
        }

        int suffix = b.suffix;
        if (b.suffix == b.len && a.rightChar == b.leftChar) {
            suffix = b.len + a.suffix;
        }

        int max = Math.max(a.max, b.max);

        if (a.rightChar == b.leftChar) {
            max = Math.max(max, a.suffix + b.prefix);
        }

        return new Node(
            len,
            prefix,
            suffix,
            max,
            a.leftChar,
            b.rightChar
        );
    }

    void update(int node, int l, int r, int index, char ch) {
        if (l == r) {
            arr[index] = ch;
            tree[node] = new Node(1, 1, 1, 1, ch, ch);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {
        int n = s.length();
        int k = queryIndices.length;

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].max;
        }

        return ans;
    }
}
