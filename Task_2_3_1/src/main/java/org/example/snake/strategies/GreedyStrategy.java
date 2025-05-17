package org.example.snake.strategies;

import org.example.snake.model.*;
import javafx.geometry.Point2D;

import java.util.Comparator;

// Жадная стратегия: движемся к ближайшей еде
public class GreedyStrategy implements Strategy {
    @Override
    public Direction nextDirection(GameModel model, RobotSnake s) {
        Point2D head = s.getHead();
        Point2D target = model.getFoods().stream()
                .min(Comparator.comparingDouble(pt -> pt.distance(head)))
                .orElse(head);
        int dx = (int)(target.getX() - head.getX());
        int dy = (int)(target.getY() - head.getY());
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? Direction.RIGHT : Direction.LEFT;
        } else if (dy != 0) {
            return dy > 0 ? Direction.DOWN : Direction.UP;
        }
        return s.dir;
    }
}