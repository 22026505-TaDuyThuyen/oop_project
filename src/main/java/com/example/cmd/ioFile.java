package com.example.cmd;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class ioFile {
    public ArrayList<Word> readFile(String file){
        File dic = new File(file);
        String line = null;
        String[] words;
        ArrayList<Word> sr = new ArrayList<>();
        try {
            BufferedReader read = Files.newBufferedReader(dic.toPath(), StandardCharsets.UTF_8);
            while(true) {
                line = read.readLine();
                if (line == null) {
                    break;
                } else {
                    words = line.split(":");
                    if (words.length == 2) {
                        Word word = new Word(words[0], words[1]);
                        sr.add(word);
                    }
                }
            }
            read.close();
        } catch (FileNotFoundException e){
            System.out.println("error");
        } catch (IOException e){
            e.printStackTrace();
        }
        return sr ;
    }
    public ArrayList<Word> readFromFile(){
        return this.readFile("src/main/java/com/example/data/dictionary.txt");
    }

    public void insert(ArrayList<Word> words,String file){
        try {
            PrintWriter write = new PrintWriter(file, StandardCharsets.UTF_8);
            for(Word word:words){
                write.println(word.getWord_target() + " : " + word.getWord_explain());
            }
            write.flush();
            write.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void insertToDictionary(ArrayList<Word> words){
        this.insert(words,"src/main/java/com/example/data/dictionary.txt");
    }

    public ArrayList<Word> readFileHistory(){
        return this.readFile("src/main/java/com/example/data/history.txt");
    }

    public void insertToHistory(ArrayList<Word> words){
        this.insert(words,"src/main/java/com/example/data/history.txt");
    }

    public void delete(String file){
        try {
            // Mở tệp để xóa nội dung
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.close(); // Đóng tệp sau khi xóa

            System.out.println("History deleted.");
        } catch (IOException e) {
            System.err.println("An error occurred while deleting history.");
            e.printStackTrace();
        }
    }

    public void deleteHistory(){
        this.delete("src/main/java/com/example/data/history.txt");
    }
}
