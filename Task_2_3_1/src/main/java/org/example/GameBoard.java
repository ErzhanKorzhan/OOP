package org.example;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private final int width;
    private final int height;
    private final List<Point2D> foods;
    private final List<Point2D> obstacles;
    private final Random random;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.foods = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.random = new Random();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void generateFood(Snake snake) {
        // Стандартное количество еды
        int foodCount = 1;
        while (foods.size() < foodCount) {
            Point2D newFood;
            do {
                newFood = new Point2D(
                        random.nextInt(width),
                        random.nextInt(height)
                );
            } while (!isPositionValid(newFood, snake));

            foods.add(newFood);
        }
    }

    public List<Point2D> getFoods() { return new ArrayList<>(foods); }

    private boolean isPositionValid(Point2D point, Snake snake) {
        return !snake.checkCollision(point)
                && !foods.contains(point)
                && !obstacles.contains(point)
                && point.getX() >= 0
                && point.getX() < width
                && point.getY() >= 0
                && point.getY() < height;
    }
}