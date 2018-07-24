package ua.nure.danylenko.Practice6.part1;

import java.util.Objects;

public class Word implements Comparable<Word>{
	
	private String word;
	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

    public String getWord(){
        return word;
    }

    public int getFrequency(){
        return frequency;
    }
    public void incrementFrequency(){
        frequency++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public int compareTo(Word o) {
        if (o.frequency == frequency) {
            int len2 = o.word.length();
            int len1 = word.length();
            if (len2 != len1) {
                return len1 - len2;
            } else {
                char v1[] = word.toCharArray();
                char v2[] = o.word.toCharArray();
                int k = 0;
                while (k < len1) {
                    char c1 = v1[k];
                    char c2 = v2[k];
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    k++;
                }
            }
        }
        return o.frequency - frequency;
    }
}
