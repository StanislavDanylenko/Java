package ua.nure.danylenko.Practice6.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// you can extend this class from one of the core container
// or aggregate it inside of class 
public class WordContainer {

    List<Word> words;

    public WordContainer() {
        words = new ArrayList<>();
    }

    public void add(Word obj) {
        for (Word w : words) {
            if (w.equals(obj)) {
                w.incrementFrequency();
                return;
            }
        }
        words.add(obj);
    }

    public void print(){
        Collections.sort(words);
        for (Word w : words) {
            System.out.println(w.getWord() + ":" + w.getFrequency());
        }
    }
}
