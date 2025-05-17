package org.example.snake.model;

import javafx.geometry.Point2D;
import java.util.*;

public class GameModel {
    private final int cols, rows;
    private final Random rand = new Random();
    private final List<Snake> snakes = new ArrayList<>();
    private final Set<Point2D> foods = new HashSet<>();
    private final int maxFood;
    private final int winLength;

    public GameModel(int cols, int rows, int maxFood, int winLength) {
        this.cols = cols;
        this.rows = rows;
        this.maxFood = maxFood;
        this.winLength = winLength;
        spawnFood();
    }

    public int getCols() { return cols; }
    public int getRows() { return rows; }
    public void addSnake(Snake snake) { snakes.add(snake); }
    public List<Snake> getSnakes() { return snakes; }
    public Set<Point2D> getFoods() { return foods; }

    public void update() {
        // каждый ход: переместить все змейки
        for (Snake s : snakes) s.move(this);
        // проверить столкновения, поедание, победу/поражение
        resolveCollisions();
        // пополнить еду
        while (foods.size() < maxFood) spawnFood();
    }

    private void spawnFood() {
        Point2D pt;
        do { pt = new Point2D(rand.nextInt(cols), rand.nextInt(rows)); }
        while (occupied(pt));
        foods.add(pt);
    }

    private boolean occupied(Point2D p) {
        for (Snake s: snakes) if (s.occupies(p)) return true;
        return foods.contains(p);
    }

    private void resolveCollisions() {
        for (Snake s : snakes) {
            Point2D head = s.getHead();
            // еда
            if (s.selfCollision() || anyOtherCollision(s)) {
                s.setAlive(false);
            }
            if (foods.remove(head)) {
                s.grow();
            }
            // выигрыш
            if (s.getLength()-1>= winLength) s.setWinner(true);
        }
    }

    private boolean anyOtherCollision(Snake s){
        for(Snake o: snakes) if(o!=s && o.occupies(s.getHead())) return true;
        return false;
    }
}