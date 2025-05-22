package org.example.snake.model;

import java.util.List;
import java.util.Set;
import javafx.geometry.Point2D;

public interface GameModel {
    int getCols();
    int getRows();
    List<Snake> getSnakes();
    void addSnake(Snake snake);
    Set<Point2D> getFoods();
    void update();
}
