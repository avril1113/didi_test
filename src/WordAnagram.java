import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


/**
 *    Word Anagram      Candidate: Yu-Ting Chung
 *
 *    Input: a set of input characters, ranging from a-z, case-insensitive.
 *    Output: all the possible English words from the dictionary that could be composed of these characters, case-insensitive.
 *    Note that each of these characters can only be used once.
 *
 *    The possible English words are fetched from the provided link and store to words.txt file.
 *
 **/


public class WordAnagram {
    public static void main(String[] args) {
        System.out.println("Please enter characters:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        System.out.println("Input: " + input);

        HashMap<Character, Integer> chars = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                chars.put(c, chars.getOrDefault(c, 0) + 1);
            }
        }

        HashSet<String> result = new HashSet<>();
        try {
            scanner = new Scanner(new FileReader("words.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("No dictionary (words.txt)");
            return;
        }

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            HashMap<Character, Integer> count = new HashMap<>(chars);
            boolean available = true;
            for (char c : word.toLowerCase().toCharArray()) {
                if (!Character.isLetter(c) || !count.containsKey(c)) {
                    available = false;
                    break;
                }

                int num = count.get(c);
                if (num == 1) {
                    count.remove(c);
                } else {
                    count.put(c, num-1);
                }
            }

            if (available)
                result.add(word);
        }

        System.out.println("Result:");
        for (String s : result) {
            System.out.println(s);
        }
    }
}
