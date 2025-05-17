package org.example.snake.controller;

import javafx.application.Platform;
import org.example.snake.model.*;
import org.example.snake.strategies.AggressiveStrategy;
import org.example.snake.strategies.GreedyStrategy;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import org.example.snake.strategies.RandomStrategy;
import java.util.List;

public class GameController {
    @FXML private Canvas gameCanvas;
    @FXML private Label lengthLabel;

    private GameModel model;
    private GraphicsContext gc;
    private AnimationTimer timer;
    private boolean dirChangedInTick = false;

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        startNewGame();
        Platform.runLater(() -> gameCanvas.getParent().requestFocus());
    }

    private void startNewGame() {
        if (timer != null) timer.stop();
        model = new GameModel(30, 30, 5, 5);
        model.addSnake(new Snake(new Point2D(5, 5)));

        // Список стратегий для робозмеек
        List<Strategy> strategies = List.of(
                new GreedyStrategy(),
                new AggressiveStrategy(4),
                new RandomStrategy()
        );
        // Стартовые точки
        Point2D[] positions = {
                new Point2D(10, 10),
                new Point2D(15, 15),
                new Point2D(20, 20)
        };
        // Создаем по одной робозмейке на каждую стратегию
        for (int i = 0; i < strategies.size(); i++) {
            model.addSnake(
                    new RobotSnake(positions[i], strategies.get(i))
            );
        }

        gameCanvas.setFocusTraversable(true);
        gameCanvas.requestFocus();

        Platform.runLater(() -> {
            gameCanvas.setOnKeyPressed(this::onKey);
            gameCanvas.getParent().requestFocus();
        });

        timer = new AnimationTimer() {
            private long last = 0;
            @Override
            public void handle(long now) {
                if (now - last < 200_000_000) return;
                last = now;
                dirChangedInTick = false;

                model.update();
                render();
                checkRestart();
            }
        };
        timer.start();
    }

    @FXML
    private void onKey(KeyEvent e) {
        Snake player = model.getSnakes().get(0);
        if (dirChangedInTick) return;
        switch (e.getCode()) {
            case UP:    player.setDirection(Direction.UP);    dirChangedInTick = true; break;
            case DOWN:  player.setDirection(Direction.DOWN);  dirChangedInTick = true; break;
            case LEFT:  player.setDirection(Direction.LEFT);  dirChangedInTick = true; break;
            case RIGHT: player.setDirection(Direction.RIGHT); dirChangedInTick = true; break;
            default: break;
        }
    }

    private void checkRestart() {
        Snake player = model.getSnakes().get(0);
        boolean anyRobotAlive = model.getSnakes().stream()
                .filter(s -> s instanceof RobotSnake)
                .anyMatch(Snake::isAlive);
        boolean anyRobotWin = model.getSnakes().stream()
                .filter(s -> s instanceof RobotSnake)
                .anyMatch(Snake::isWinner);
        if (!player.isAlive() || !anyRobotAlive || player.isWinner() || anyRobotWin) {
            Platform.runLater(() -> {
                timer.stop();
                String title = "Ничья!";
                if ((player.isAlive() && !anyRobotAlive) || (player.isWinner() && !anyRobotWin)) {
                    title = "Ты выиграл)";
                } else if ((!player.isAlive() && anyRobotAlive) || (!player.isWinner() && anyRobotWin)) {
                    title = "Робозмейки выиграли(";
                }
                String content = "Твой счет: " + (player.getLength()-1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
                alert.setHeaderText(title);
                alert.showAndWait();
                startNewGame(); // Перезапуск игры после нажатия OK
            });
        }
    }

    private void render() {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        gc.setFill(Color.RED);
        int cellSize = 20;
        for (Point2D p : model.getFoods()) {
            gc.fillOval(p.getX() * cellSize, p.getY() * cellSize, cellSize, cellSize);
        }
        for (Snake s : model.getSnakes()) {
            boolean isRobot = s instanceof RobotSnake;
            gc.setFill(isRobot ? Color.CORNFLOWERBLUE : Color.YELLOW);
            for (Point2D pt : s.body) {
                gc.fillRect(pt.getX() * cellSize, pt.getY() * cellSize, cellSize, cellSize);
            }
            gc.setFill(isRobot ? Color.DARKBLUE : Color.ORANGE);
            Point2D h = s.getHead();
            gc.fillRect(h.getX() * cellSize, h.getY() * cellSize, cellSize, cellSize);
        }
        lengthLabel.setText(String.valueOf(model.getSnakes().get(0).getLength()-1));
    }
}