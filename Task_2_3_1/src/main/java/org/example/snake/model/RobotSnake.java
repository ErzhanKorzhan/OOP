package org.example.snake.model;
import javafx.geometry.Point2D;

// Робозмейка, управляющаяся стратегией
public class RobotSnake extends Snake {
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