package com.example.cmd;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    protected Dictionary dictionary = new Dictionary();

    protected ArrayList<Word> words= new ArrayList<>();

    public void insertFromCommandLine() {
        Scanner src = new Scanner(System.in);
        System.out.print("Enter the word you want to add : ");
        String word_target = src.nextLine();
        System.out.print("Meaning of the word you want to add : ");
        String word_explain = src.nextLine();
        Word word = new Word(word_target, word_explain);
        dictionary.addWord(word);
    }

    public void dictionaryLookup(){
        System.out.print("Enter the word you want to look up : ");
        Scanner src = new Scanner(System.in);
        String word_target = src.nextLine();
        Word word = dictionary.lookup(word_target);
        if(word == null){
            System.out.println("this word is not in dictionary" );
        } else {
            System.out.println(word.getWord_explain());
            words.add(word);
        }
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void setDictionary(Dictionary dic){
        this.dictionary = dic;
    }

}
