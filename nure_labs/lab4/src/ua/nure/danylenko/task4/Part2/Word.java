package ua.nure.danylenko.task4.Part2;

import java.util.Objects;

public class Word implements Comparable<Word>{
	
	private String content;
	private int frequency;

	public Word(String content) {
		this.content = content;
		frequency = 1;
	}

    public String getContent(){
        return content;
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
        return Objects.equals(content, word1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public int compareTo(Word o) {
        if (o.frequency == frequency) {
            int len2 = o.content.length();
            int len1 = content.length();
            int lenMin = (len2 < len1) ? len2: len1;
            /*if (len2 != len1) {
                return len1 - len2;
            } else {*/
            char v1[] = content.toCharArray();
            char v2[] = o.content.toCharArray();
            int k = 0;
            while (k < lenMin) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c1 - c2;
                }
                k++;
            }
            if (len2 != len1) {
                return len1 - len2;
            }
            //}
        }
        return o.frequency - frequency;
    }
}
