package org.example.snake.model;

// Общий интерфейс для стратегий управления робозмейками
public interface Strategy {
    Direction nextDirection(GameModel model, RobotSnake me);
}
