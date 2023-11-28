package com.example.cmd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandline{
    protected ArrayList<Word> words = new ArrayList<>();
    protected Dictionary dictionary = new Dictionary();

    public void showAll(){
        System.out.println("No   | English        | Vietnamese");

        //sap xep danh sach tu vung theo tieng anh
        words.sort(Comparator.comparing(Word::getWord_target));

        int number = 0;
        for(Word word : words){
            System.out.printf("%-5d| %-15s| %-15s%n", number, word.getWord_target(), word.getWord_explain());
            number++;
        }
    }

    public void dictionarySearcher(){
        System.out.print("Please enter the word you want to search : ");
        Scanner src = new Scanner(System.in);
        String word_target = src.nextLine();
        words = dictionary.searcher(word_target);
        showAll();
    }

    public void DictionaryAdvanced(){
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");//thoat
        System.out.println("[1] Update Dictionary"); // them tu vao tu diem
        System.out.println("[2] Delete History"); //xoa lich su tim kiem
        System.out.println("[3] History"); // in lich su tim kiem
        System.out.println("[4] Show Dictionary");//in ra danh sach tu
        System.out.println("[5] Search");//tra tu cung tien to
        System.out.println("[6] Look Up");// tra nghia
        System.out.println("[7] Game");// game
        System.out.print("Your action: ");
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public ArrayList<Word> getWords(){
        return words;
    }

    public void setDictionary(Dictionary dic){
        this.dictionary = dic;
    }

}
