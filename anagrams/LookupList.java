package anagrams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LookupList {
  private ArrayList<String> wordlist;

  public LookupList() {
    wordlist = new ArrayList<String>();
  }

  public LookupList(String fileName) throws FileNotFoundException {
    wordlist = new ArrayList<String>();
    Scanner file = new Scanner(new FileReader(fileName));
    while (file.hasNextLine()) {
      String word = file.nextLine();
      if (word.length() == 0 || word.charAt(0) == '#') // skip blank lines or starting with #, treating them as comments
        continue;
      // insert(word);
      wordlist.add(word);
      /*
       * // Track how quickly the wordlist is forming...
       * if (wordlist.size() % 5000 == 0)
       * System.out.println("Added " + numWords + " words.");
       */
    }
    file.close();
    wordlist = mergeSort(wordlist);
  }

  private ArrayList<String> mergeSort(ArrayList<String> arr) {
    if (arr.size() < 2)
      return arr;
    ArrayList<String> a = (ArrayList<String>) arr.subList(0, arr.size() / 2);
    ArrayList<String> b = (ArrayList<String>) arr.subList(arr.size() / 2, arr.size());
    a = mergeSort(a);
    b = mergeSort(b);
    return mergeLists(a, b);
  }

  private static ArrayList<String> mergeLists(ArrayList<String> a, ArrayList<String> b) {
    ArrayList<String> merged = new ArrayList<>();
    int ai = 0;
    int bi = 0;
    int i = 0;
    while (a.size() > ai && b.size() > bi && merged.size() > i) {
      if (a.get(ai).compareTo(b.get(bi)) > 0) {
        merged.set(i, b.get(bi));
        bi++;
      } else if (a.get(ai).compareTo(b.get(bi)) < 0) {
        merged.set(i, a.get(ai));
        ai++;
      } else {
        merged.set(i, a.get(ai));
        i++;
        merged.set(i, b.get(bi));
        ai++;
        bi++;
      }
      i++;
    }
    if (a.size() > ai) {
      for (int j = ai; j < a.size(); j++) {
        merged.set(i, a.get(j));
        i++;
      }
    } else if (b.size() > bi) {
      for (int j = bi; j < b.size(); j++) {
        merged.set(i, b.get(j));
        i++;
      }
    }
    return merged;
  }

  public int size() {
    return wordlist.size();
  }

  public String get(int index) {
    return wordlist.get(index);
  }

  public int indexOf(String word) {
    return wordlist.indexOf(word);

    // TODO update to use binary search!
  }

  public boolean contains(String word) {
    return wordlist.contains(word);

    // TODO update to use binary search!
  }

  public List<String> toList() {
    return wordlist;
  }

  public void print() {
    int maxdigits = String.valueOf(wordlist.size()).length();
    ;
    String format = "%" + maxdigits + "d: %s\n";

    System.out.printf("Wordlist (%d total):\n", wordlist.size());
    for (int i = 0; i < wordlist.size(); i++) {
      System.out.printf(format, i, wordlist.get(i));
    }
  }

  public void insert(String word) {
    // This inserts a word into the list in sorted position,
    // assuming that the list is already sorted.

    for (int i = wordlist.size(); i > 0; i--) { // work backwards
      String w = wordlist.get(i - 1);
      if (word.compareTo(w) >= 0) { // if >= item in list (w),
        wordlist.add(i, word); // insert at correct place, which is right after the item
        return; // done!
      }
    }
    wordlist.add(0, word); // if < all items in list, add to front of list

    // TODO Optional?? Rewrite to work a little bit faster! Hint: Use binarySearch
    // to know WHERE to insert...
  }

}
