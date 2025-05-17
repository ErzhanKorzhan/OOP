package org.example.snake.strategies;

import org.example.snake.model.*;
import java.util.*;

// Простейшая стратегия: случайный выбор направления
public class RandomStrategy implements Strategy {
    private final Random rnd = new Random();

    @Override
    public Direction nextDirection(GameModel model, RobotSnake s) {
        Direction[] dirs = Direction.values();
        return dirs[rnd.nextInt(dirs.length)];
    }
}