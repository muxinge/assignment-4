public class Main
{
    private static LinearProbingHashST<Integer, String> dict3 =
            new LinearProbingHashST<>(20000, true);
    public static boolean strongPassword(String password)
    {

        if (password.length() < 8) {
            return false;
        }


        if (dict3.contains(password)) {
            return false;
        }

        for (int i = 0; i < 10; i++) { // Loop through digits 0-9
            String trimmedWord = password.substring(0, password.length() - 1); // Extract word part
            if (password.endsWith(String.valueOf(i)) && dict3.contains(trimmedWord)) {
                return false;
            }
        }
        return true;
    }
    public static void main (String[] args)
    {
        SeparateChainingHashST<Integer, String> dict1 =
                new SeparateChainingHashST<>(1000, true);
        SeparateChainingHashST<Integer, String> dict2 =
                new SeparateChainingHashST<>(1000, false);
        In in = new In("wordlist.txt");

        LinearProbingHashST<Integer, String> dict4 =
                new LinearProbingHashST<>(20000, false);

        int i = 0;
        while (in.hasNextLine())
        {
            // Read the words
            String word = in.readLine();

            // Insert words into different types of dictionaries
            dict1.put(++i, word);
            dict2.put(++i, word);
            dict3.put(++i, word);
            dict4.put(++i, word);

        }
        System.out.println(dict1);
        System.out.println("cost of old hash code separate chain:" + dict1.getCost());
        System.out.println("cost of current hash code separate chain:" + dict2.getCost());
        System.out.println("cost of old hash code line probing:" + dict3.getCost());
        System.out.println("cost of current hash code line probing:" + dict4.getCost());

        System.out.println("strong password:" + strongPassword("account8"));
        System.out.println("strong password:" + strongPassword("accountability"));

        System.out.println("strong password:" + strongPassword("9a$D#qW7!uX&Lv3zT"));
        System.out.println("strong password:" + strongPassword("B@k45*W!c$Y7#zR9P"));
        System.out.println("strong password:" + strongPassword("X$8vQ!mW#3Dz&Yr4K5"));

    }
}