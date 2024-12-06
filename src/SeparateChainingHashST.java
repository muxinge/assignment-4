import edu.princeton.cs.algs4.*;
public class SeparateChainingHashST<Key, Value> {
    private int M; // number of chains
    private int n;
    private Node[] st;// array of chains
    private int cost;
    private boolean oldHash;
    //private SequentialSearchST<Key, Value>[] st;

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    public SeparateChainingHashST(int M, boolean oldHash)
    {
        this.M = M;
        this.oldHash = oldHash;
        st = new Node[M];
        cost = 0;
    }
    public int getCost(){
        return cost;
    }
    private int hash(Key key) {
        if (oldHash) {
            return (oldHashCode(Integer.toString((Integer) key)) & 0x7fffffff) % M;
        } else {
            return (currentHashCode(Integer.toString((Integer) key)) & 0x7fffffff) % M;
        }
    }

    public int oldHashCode(String string) {
        int hash = 0;
        int skip = Math.max(1, string.length() / 8);
        for (int i = 0; i < string.length(); i += skip)
            hash = (hash * 37) + string.charAt(i);
        return hash;
    }

    public int currentHashCode(String string) {
        int hash = 0;
        for (int i = 0; i < string.length(); i++)
            hash = (hash * 31) + string.charAt(i);
        return hash;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            cost++;
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key, val, st[i]);
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) return (Value) x.val;
        return null;
    }



}