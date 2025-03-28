package org.example;

public class GameState {
    private int score;
    private final int targetLength;
    private boolean gameOver;
    private boolean gameWon;
    private int level;

    public GameState(int targetLength) {
        this.targetLength = targetLength;
        this.gameOver = false;
        this.gameWon = false;
        this.level = 1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public int getTargetLength() {
        return targetLength;
    }


}