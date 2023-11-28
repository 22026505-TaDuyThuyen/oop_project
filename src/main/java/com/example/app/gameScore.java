package com.example.app;

public class gameScore {
    private static gameScore ins = new gameScore();

    private int diemCuaBan = 0 ;
    private int maxScore = 0 ;

    public int getDiemCuaBan() {
        return diemCuaBan;
    }

    public void setDiemCuaBan(int diemCuaBan) {
        this.diemCuaBan = diemCuaBan;
    }

    private gameScore() {}

    public static gameScore getIns() {
        return ins;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxMark) {
        this.maxScore = maxMark;
    }
}
