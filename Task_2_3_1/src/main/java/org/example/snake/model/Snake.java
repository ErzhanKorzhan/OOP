package org.example.snake.model;
import javafx.geometry.Point2D;
import java.util.*;

public class Snake {
    public Deque<Point2D> body = new LinkedList<>();
    public Direction dir = Direction.RIGHT;
    private int growthCounter = 0;
    protected boolean alive = true;
    protected boolean winner = false;

    public Snake(Point2D start) { body.add(start); }
    public Point2D getHead() { return body.peekFirst(); }
    public int getLength() { return body.size(); }
    public boolean occupies(Point2D p) { return body.contains(p); }
    public boolean selfCollision() { return new HashSet<>(body).size()<body.size(); }

    public void setDirection(Direction d) {
        if (d.dx + dir.dx != 0 || d.dy + dir.dy != 0) {
            this.dir = d;
        }
    }

    public void move(GameModel model) {
        if (!alive || winner) return;

        Point2D currentHead = getHead();
        Point2D raw = new Point2D(currentHead.getX() + dir.dx, currentHead.getY() + dir.dy);
        double newX = (raw.getX() + model.getCols()) % model.getCols();
        double newY = (raw.getY() + model.getRows()) % model.getRows();
        Point2D head = new Point2D(newX, newY);

        body.addFirst(head);
        // убираем хвост, если не растём
        if (growthCounter > 0) {
            // растём: просто уменьшаем счётчик, не убираем хвост
            growthCounter--;
        } else {
            // обычное передвижение: удаляем последний сегмент
            body.removeLast();
        }
    }

    public void grow() {
        growthCounter++;
    }

    public void setAlive(boolean a) { alive = a; }
    public void setWinner(boolean w) { winner = w; }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWinner() {
        return winner;
    }
}