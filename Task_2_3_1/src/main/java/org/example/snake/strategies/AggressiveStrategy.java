package org.example.snake.strategies;

import org.example.snake.model.*;
import javafx.geometry.Point2D;
import java.util.*;

public class AggressiveStrategy implements Strategy {
    private final Random rnd = new Random();
    private final int lengthThreshold;

    public AggressiveStrategy(int lengthThreshold) {
        this.lengthThreshold = lengthThreshold;
    }

    @Override
    public Direction nextDirection(GameModel model, RobotSnake s) {
        Point2D head = s.getHead();

        // Выбираем цель: еда или игрок
        Point2D target;
        if (s.getLength() < lengthThreshold && !model.getFoods().isEmpty()) {
            // ближайшая еда
            target = model.getFoods().stream()
                    .min(Comparator.comparingDouble(pt -> pt.distance(head)))
                    .orElse(head);
        } else {
            // голова игрока
            target = model.getSnakes().get(0).getHead();
        }

        // Генерим кандидатов направлений, отсортированных по приоритету
        List<Direction> priorities = new ArrayList<>(List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));

        // Сортировка так, чтобы в начало попали те, что ближе к цели
        priorities.sort(Comparator.comparingDouble(d -> {
            Point2D next = head.add(d.dx, d.dy);
            return next.distance(target);
        }));

        // Проверяем каждый кандидат — первый безопасный и возвращаем
        for (Direction d : priorities) {
            Point2D next = wrap(head.add(d.dx, d.dy), model);
            if (isSafe(next, model)) {
                return d;
            }
        }

        // Если совсем нет безопасных «разумных» ходов — случайный безопасный
        List<Direction> safe = new ArrayList<>();
        for (Direction d : Direction.values()) {
            Point2D next = wrap(head.add(d.dx, d.dy), model);
            if (isSafe(next, model)) safe.add(d);
        }

        return safe.isEmpty() ? s.dir : safe.get(rnd.nextInt(safe.size()));
    }

    // Обёртка по модулю размеров поля
    private Point2D wrap(Point2D raw, GameModel m) {
        double x = (raw.getX() + m.getCols()) % m.getCols();
        double y = (raw.getY() + m.getRows()) % m.getRows();
        return new Point2D(x, y);
    }

    // Проверка, что точка не занята телом любой змейки и не пересекает границы (если нет тора)
    private boolean isSafe(Point2D pt, GameModel m) {
        // не врезаться в себя или других
        for (Snake other : m.getSnakes()) {
            if (other.occupies(pt)) return false;
        }
        return true;
    }
}
