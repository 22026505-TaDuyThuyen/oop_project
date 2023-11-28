package com.example.cmd;

import com.example.cmd.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    public Dictionary() {

    }

    // kiem tra xem tu co nam trong list hay k
    public int checkList(int top, int bot, String word_target) {
        if (top <= bot) {
            int mid = (top + bot) / 2;
            Word word = words.get(mid);

            // hàm compareTo so sánh hai chuôi theo thứ tự từ điển trả về âm , 0,
            //  dương tương ứng lớn hơn bằng bé hơn
            int index = word.getWord_target().compareTo(word_target);
            if (index < 0)
                return checkList(mid + 1, bot, word_target);
            if (index > 0) {
                return checkList(top, mid - 1, word_target);
            }
            if(index==0) {
                return mid;
            }
        }
        return -1;
    }

    // tìm vị trí đầu tiên có từ bắt đầu bằng từ đã tra
    public int checkpos(String word_target) {
        int left = 0;
        int right = words.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midWord = words.get(mid).getWord_target();

            if (midWord.startsWith(word_target)) {
                // Nếu từ tại vị trí mid bắt đầu bằng word_target, tiếp tục tìm kiếm bên trái
                result = mid;
                right = mid - 1;
            } else if (midWord.compareTo(word_target) < 0) {
                // Nếu từ tại vị trí mid đứng trước word_target trong thứ tự từ điển, tìm kiếm bên phải
                left = mid + 1;
            } else {
                // Nếu từ tại vị trí mid đứng sau word_target trong thứ tự từ điển, tìm kiếm bên trái
                right = mid - 1;
            }
        }

        return result;
    }

    //them tu vao list
    public void addWord(Word word) {
        words.add(word);
    }
    //them tu va sap sep
    public void addWordSort(Word word) {
        words.add(word);
        Collections.sort(words);
    }

    // tim tu trong list
    public Word lookup(String word_target) {
        int n = words.size();
        int i = checkList(0, n - 1, word_target);
        if (i != -1)
            return words.get(i);
        return null;
    }

    // xóa từ
    public void deleteWord(Word word){
        int i = checkList(0,words.size(),word.getWord_target());
        words.remove(i);
    }

    // in ra list cac tu co tien to giong tu da cho (tu da cho nam trong tu dien) co
    // trong list . vd : book -> bookbindery,bookcase,bookbinder...
    public ArrayList<Word> searcher(String word_target) {
        ArrayList<Word> sr = new ArrayList<>();
        // in ra list cac tu co tien to giong tu da cho (tu da cho k nam trong tu dien)
        // co trong list . vd : tra ->
        // transport, translate,transform, transit,....
        int n = words.size();
        int indx = checkpos(word_target);
        if(indx < 0) {
            return null;
        }
        else {
            for (int i = indx; i < n; i++) {
                if (words.get(i).getWord_target().startsWith(word_target)) {
                    sr.add(words.get(i));
                } else {
                    break;
                }
            }
        }
        return sr;
    }

    public ObservableList<Word> searcher2(String word_target) {
        ObservableList<Word> sr = FXCollections.observableArrayList();
        // in ra list cac tu co tien to giong tu da cho (tu da cho k nam trong tu dien)
        // co trong list . vd : tra ->
        // transport, translate,transform, transit,....
        int n = words.size();
        int indx = checkpos(word_target);
        if(indx < 0) {
            return null;
        }
        else {
            for (int i = indx; i < n; i++) {
                if (words.get(i).getWord_target().startsWith(word_target)) {
                    sr.add(words.get(i));
                } else {
                    break;
                }
            }
        }
        return sr;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}
