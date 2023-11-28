package com.example.cmd;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int action ;
        Game game = new Game();
        DictionaryCommandline dc = new DictionaryCommandline();
        DictionaryManagement dm = new DictionaryManagement();
        Dictionary dic = new Dictionary();
        ioFile file = new ioFile();
        Scanner sr = new Scanner(System.in);
        ArrayList<Word> words = file.readFromFile();
        ArrayList<Word> His = new ArrayList<>();
        ArrayList<Word> Search = new ArrayList<>();
        do{
            dc.DictionaryAdvanced();
            action = sr.nextInt();
            sr.nextLine();
            if(action == 1){
                dic.setWords(words);
                dm.setDictionary(dic);
                dm.insertFromCommandLine();
                file.insertToDictionary(words);
                words = file.readFromFile();
            }
            else if (action == 2){
                file.deleteHistory();
            }
            else if(action == 3){
                His = file.readFileHistory();
                dc.setWords(His);
                dc.showAll();
            }
            else if(action == 4){
                dic.setWords(words);
                dc.setWords(words);
                dc.showAll();
            }
            else if(action==5){
                dic.setWords(words);
                dc.setWords(Search);
                dc.setDictionary(dic);
                dc.dictionarySearcher();
                Search = dc.getWords();
                His.addAll(Search);
                file.insertToHistory(His);
            }
            else if (action==6){
                dic.setWords(words);
                dm.setDictionary(dic);
                dm.setWords(His);
                dm.dictionaryLookup();
                file.insertToHistory(His);
            }
            else if(action == 7){
                game.Run();
            }

        }while(action != 0);
    }
}