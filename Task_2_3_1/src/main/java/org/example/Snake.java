package org.example;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Point2D> segments;
    private Direction direction;
    private Direction nextDirection;

    public Snake(Point2D startPosition) {
        segments = new ArrayList<>();
        segments.add(startPosition);
        direction = Direction.RIGHT;
        nextDirection = direction;
    }

    public void setDirection(Direction newDirection) {
        if ((direction == Direction.UP && newDirection != Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection != Direction.UP) ||
                (direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection != Direction.LEFT)) {
            nextDirection = newDirection;
        }
    }

    public void move() {
        direction = nextDirection;
        Point2D newHead = getHead().add(getDirectionVector());
        segments.removeLast();
        segments.addFirst(newHead);
    }

    public void grow() {
        Point2D newHead = getHead().add(getDirectionVector());
        segments.addFirst(newHead);
    }

    public Point2D getHead() {
        return segments.get(0);
    }

    public List<Point2D> getSegments() {
        return new ArrayList<>(segments);
    }

    private Point2D getDirectionVector() {
        return switch (direction) {
            case UP -> new Point2D(0, -1);
            case DOWN -> new Point2D(0, 1);
            case LEFT -> new Point2D(-1, 0);
            case RIGHT -> new Point2D(1, 0);
        };
    }

    public boolean checkCollision(Point2D point) {
        return segments.contains(point);
    }

    public boolean checkSelfCollision() {
        Point2D head = getHead();
        return segments.subList(1, segments.size()).contains(head);
    }
}