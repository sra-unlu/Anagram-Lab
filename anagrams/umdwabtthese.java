package anagrams;

import java.util.List;
import java.util.ArrayList;

public class umdwabtthese {
    public static List<String> getAnagrams(String word, LookupList dict) {
        ArrayList<String> panagrams = new ArrayList<>();
        ArrayList<String> lanagrams = new ArrayList<>();
        getAllAnagrams("", word, panagrams);
        for (String anagram : panagrams) {
            if (dict.contains(anagram) && !lanagrams.contains(anagram)) {
                lanagrams.add(anagram);
            }
        }
        return lanagrams;
    }

    private static void getAllAnagrams(String prefix, String word, List<String> anagramList) {
        if (word.length() == 0)
            anagramList.add(prefix);
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            String wordRemaining = word.substring(0, i) + word.substring(i + 1);
            getAllAnagrams(prefix + current, wordRemaining, anagramList);
        }
    }
}
