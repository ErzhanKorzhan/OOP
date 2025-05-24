package org.example.snake.snakes;

import javafx.geometry.Point2D;
import org.example.snake.model.Direction;
import org.example.snake.model.GameModel;
import org.example.snake.model.Strategy;

// Робозмейка, управляющаяся стратегией
public class RobotSnake extends PlayerSnake {
    private final Strategy strategy;

    public RobotSnake(Point2D start, Strategy strategy) {
        super(start);
        this.strategy = strategy;
    }

    @Override
    public void move(GameModel model) {
        // Передвижение по направлению, выданному стратегией
        Direction newDir = strategy.nextDirection(model, this);
        setDirection(newDir);
        super.move(model);
    }
}