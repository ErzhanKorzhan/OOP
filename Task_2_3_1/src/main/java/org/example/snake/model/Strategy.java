package org.example.snake.model;

import org.example.snake.snakes.RobotSnake;

// Общий интерфейс для стратегий управления робозмейками
public interface Strategy {
    Direction nextDirection(GameModel model, RobotSnake me);
}
