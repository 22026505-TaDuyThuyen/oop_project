package com.example.cmd;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;

public class Game {

    //day la list viet , sau nay minh se cai tien list nay nhap vao tu file txt
    ioFile sr = new ioFile();
    ArrayList<Word> WordList = sr.readFromFile();

    int NumberOfWord = WordList.size();

    public String chooseWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(NumberOfWord);
        return WordList.get(randomIndex).getWord_target();
    }

    public char getGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your Guess: ");
        return scanner.next().charAt(0);
    }

    public boolean checkWord(String word, char guess) {
        return word.indexOf(guess) >= 0;
    }

    public String update(String guessWord, String word, char guess) {
        StringBuilder updatedWord = new StringBuilder(guessWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                updatedWord.setCharAt(i, guess);
            }
        }
        return updatedWord.toString();
    }

    public void renderGame(int count, String guessWord) {
        System.out.println(guessWord);
        System.out.println("Number of wrong guesses: " + count);
    }





    public void displayFinalGame(boolean won, String word) {
            if (!won) {
                System.out.println("You Lose!");
                System.out.println("The correct word is " + word);
            } else {
                System.out.println("Congratulations, You win!");
            }
        }

    public void Run() {
        boolean won = true;
        String word = chooseWord();
        String guessWord = "";
        for(int i=0;i<word.length();i++){
            guessWord += '-';
        }
        int count = 0;
        System.out.println("Guess which word is hidden and you have 10 times to guess wrong!");
        System.out.println("Good Luck!! ^^");
        do {
            renderGame(count, guessWord);
            char guess = getGuess();
            if (checkWord(word, guess)) {
                guessWord = update(guessWord, word, guess);
            } else {
                count++;
            }
        } while (count <= 10 && !word.equals(guessWord));

        renderGame(count, guessWord);

        if (count > 10) {
            won = false;
            displayFinalGame(won, word);
        } else {
            displayFinalGame(won, word);
        }
    }
}
