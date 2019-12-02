package DictionaryProject;
//


import java.io.Serializable;
import java.util.LinkedList;

public class Word implements  Serializable {
    private String word;
    private String first_used;
    private String origin;
    private LinkedList<String> meanings;

    public Word(){}
    public Word(String word, String first_used, String origin, LinkedList<String> meanings) {
        this.word = word;
        this.first_used = first_used;
        this.origin = origin;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getfirst_used() {
        return first_used;
    }

    public void setfirst_used(String first_used) {
        this.first_used = first_used;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LinkedList<String> getMeanings() {
        return meanings;
    }

    public void setMeanings(LinkedList<String> meanings) {
        this.meanings = meanings;
    }

}
