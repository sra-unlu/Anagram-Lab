package anagrams;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnagramFinder {

  public static List<String> getAnagrams(String word, LookupList dict) {
    ArrayList<String> anagrams = new ArrayList<>();
    permutation("", word, dict, anagrams);
    System.out.println(anagrams);
    return (List<String>) anagrams;
  }

  private static void permutation(String prefix, String remaining, LookupList dict, ArrayList<String> anagrams) {
    if (remaining.length() == 0) {
      if (dict.contains(prefix) && !anagrams.contains(prefix)) {
        anagrams.add(prefix);
        System.out.println(prefix);
      }
      return;
    }
    for (int i = 0; i < remaining.length(); i++) {
      String a = prefix + remaining.charAt(i);
      System.out.println(a);
      String b = remaining.substring(0, i) + remaining.substring(i + 1);
      // {} from begining of b
      System.out.println(b);
      permutation(a, b, dict, anagrams);
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner input = new Scanner(System.in);

    System.out.println("Which word list?  (large, medium, small, tiny)");
    String listSize = input.nextLine();
    String filename = "data/scrabbleWords/words_" + listSize + ".txt";

    System.out.printf("Reading in word list from file %s...\n", filename);
    double start = System.nanoTime();
    LookupList dict = new LookupList(filename);
    System.out.print("Successfully read in word list from file!\n");
    System.out.printf("Time: %.4f seconds.\n\n", (System.nanoTime() - start) / 1000000000);

    System.out.print("\n\nEnter word/letters to find anagrams (enter blank to stop) >>>  ");
    String word = input.nextLine().toUpperCase();
    while (!word.equals("")) {
      start = System.nanoTime();
      System.out.printf("Searching for anagrams of \"%s\"...\n", word);
      List<String> anagrams = getAnagrams(word, dict);
      System.out.printf("Found %d anagrams!\n", anagrams.size());
      System.out.println(anagrams);
      System.out.printf("Time: %.4f seconds.\n\n", (System.nanoTime() - start) / 1000000000);

      System.out.print("Enter another word to find anagrams >>> ");
      word = input.nextLine().toUpperCase();
      ;
    }
    System.out.println("All done!");
    input.close();
  }

}
