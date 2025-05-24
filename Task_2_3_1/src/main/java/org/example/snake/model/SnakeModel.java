package org.example.snake.model;

import javafx.geometry.Point2D;
import java.util.*;

public class SnakeModel implements GameModel {
    private final int cols, rows;
    private final Random rand = new Random();
    private final List<Snake> snakes = new ArrayList<>();
    private final Set<Point2D> foods = new HashSet<>();
    private final int maxFood;
    private final int winLength;

    public SnakeModel(int cols, int rows, int maxFood, int winLength) {
        this.cols = cols;
        this.rows = rows;
        this.maxFood = maxFood;
        this.winLength = winLength;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    @Override
    public List<Snake> getSnakes() {
        return snakes;
    }

    @Override
    public Set<Point2D> getFoods() {
        return foods;
    }

    @Override
    public void update() {
        for (Snake s : snakes) s.move(this);
        resolveCollisions();
        spawnFood();
    }

    private void spawnFood() {
        List<Point2D> freeCells = new ArrayList<>();
        // Собираем все свободные клетки
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                Point2D pt = new Point2D(x, y);
                if (!occupied(pt)) {
                    freeCells.add(pt);
                }
            }
        }
        while (foods.size() < maxFood) {
            // Добавляем случайную свободную клетку
            Point2D selected = freeCells.remove(rand.nextInt(freeCells.size()));
            foods.add(selected);
        }
    }

    private boolean occupied(Point2D p) {
        for (Snake s : snakes) {
            if (s.occupies(p)) return true;
        }
        return foods.contains(p);
    }

    private void resolveCollisions() {
        for (Snake s : snakes) {
            Point2D head = s.getHead();
            if (s.selfCollision() || anyOtherCollision(s)) {
                s.setAlive(false);
            }
            if (foods.remove(head)) {
                s.grow();
            }
            if (s.getBody().size() - 1 >= winLength) {
                s.setWinner(true);
            }
        }
    }

    private boolean anyOtherCollision(Snake s) {
        for (Snake o : snakes) {
            if (o != s && o.occupies(s.getHead())) {
                return true;
            }
        }
        return false;
    }
}