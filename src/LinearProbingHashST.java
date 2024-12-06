public class LinearProbingHashST<Key, Value> {
    private int M;
    private Value[] vals;
    private Key[] keys;
    private boolean oldHash;
    private int cost;


    public LinearProbingHashST(int M, boolean oldHash)
    {
        this.M = M;
        this.oldHash = oldHash;
        cost = 0;
        vals = (Value[]) new Object[M];
        keys = (Key[]) new Object[M];
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
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            cost++;
            if (keys[i].equals(key))
                break;
        }
        keys[i] = key;
        vals[i] = val;
    }
    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (key.equals(keys[i]))
                return vals[i];
        return null;
    }
    public boolean contains(String word){
        for (Value val : vals) {
            if (val != null && val.equals(word))
                return true;
        }
        return false;
    }
}


