package anagrams;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LookupListTester {

  private static boolean verifyOrdered(LookupList list)
  {
    for(int i = 1; i < list.size(); i++){
      if (list.get(i-1).compareTo(list.get(i)) > 0)
        return false;
    }
    return true;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner input = new Scanner(System.in);

    System.out.println("Which word list?  (large, medium, small, tiny)");
    String listSize= input.nextLine();
    String filename = "data/scrabbleWords/words_" + listSize + ".txt";

    System.out.printf("Reading in word list from file %s...\n", filename);
    double start = System.nanoTime();
    LookupList dict = new LookupList(filename);
    System.out.print("Successfully read in word list from file!\n");
    System.out.printf("Time: %.4f seconds.\n\n", (System.nanoTime()-start)/1000000000);

    System.out.println("List is correctly ordered? " + verifyOrdered(dict));

    System.out.print("Print entire list? (y/n) >>> ");
    if(input.nextLine().toLowerCase().contains("y"))
      dict.print(); //This could take a while...

    // Do word lookups.
    System.out.print("Search for a word (enter blank to stop) >>> ");
    String word = input.nextLine().toUpperCase();
    while(!word.equals("")){
      System.out.printf("Searching for \"%s\"...\n", word);
      start = System.nanoTime();
      if(dict.contains(word)) {
        System.out.printf("Dictionary has the word \"%s\" at position %d.\n", word, dict.indexOf(word));
        if(!dict.get(dict.indexOf(word)).equals(word))  // verify correct index
          System.out.print("ERROR - incorrect position!");
      }
      else
        System.out.printf("Dictionary does NOT contain the word \"%s\".\n", word);
      System.out.printf("Time: %.4f seconds.\n\n", (System.nanoTime()-start)/1000000000);

      System.out.print("Search for another word >>> ");
      word = input.nextLine().toUpperCase();
    }

    System.out.println("All done!");
    input.close();
  }

}
