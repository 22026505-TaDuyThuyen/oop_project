package com.example.cmd;

public class Word implements Comparable<Word>{
    String word_target; // tu tieng anh
    String word_explain; // nghia tieng viet
    String word_pronounce; // phien am

    public Word( String word_target, String word_explain) {
        this.word_explain = word_explain.trim().toLowerCase();
        this.word_target = word_target.trim().toLowerCase();
    }


    public Word( String word_target, String word_explain, String word_pronounce) {
        this.word_explain = word_explain.trim().toLowerCase();
        this.word_target = word_target.trim().toLowerCase();
        this.word_pronounce = word_pronounce;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target){
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain){
        this.word_explain = word_explain;
    }

    public String getWord_pronounce() {
        return word_pronounce;
    }

    public void setWord_pronounce(String word_pronounce) {
        this.word_pronounce = word_pronounce;
    }

    public int compareTo(Word anotherWord){
        return this.getWord_target().compareTo(anotherWord.getWord_target());
    }
}
