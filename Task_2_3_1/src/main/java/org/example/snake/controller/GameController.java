package org.example.snake.controller;

import javafx.application.Platform;
import org.example.snake.model.*;
import org.example.snake.snakes.PlayerSnake;
import org.example.snake.snakes.RobotSnake;
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

    private enum CellType { EMPTY, SNAKE_PLAYER, SNAKE_PLAYER_HEAD, SNAKE_ROBOT, SNAKE_ROBOT_HEAD, FOOD }
    private CellType[][] prevState;
    private CellType[][] currentState;
    private GameModel model;
    private GraphicsContext gc;
    private AnimationTimer timer;
    private boolean dirChangedInTick = false;

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        startNewGame();
    }

    private void startNewGame() {
        if (timer != null) timer.stop();
        model = new SnakeModel(30, 30, 5, 5);
        model.addSnake(new PlayerSnake(new Point2D(5, 5)));

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
        gameCanvas.setOnKeyPressed(this::onKey);

        prevState = new CellType[model.getCols()][model.getRows()];
        currentState = new CellType[model.getCols()][model.getRows()];
        initGridState(prevState);
        initGridState(currentState);

        for (int x = 0; x < currentState.length; x++) {
            for (int y = 0; y < currentState[0].length; y++) {
                drawCell(x, y, currentState[x][y]);
            }
        }

        timer = new AnimationTimer() {
            private long lastUpdate = 0;
            private long accumulatedTime = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                long deltaTime = now - lastUpdate;
                accumulatedTime += deltaTime;
                lastUpdate = now;

                boolean updated = false;
                // Обновляем модель столько раз, сколько прошло интервалов
                // Фиксированный интервал (200 мс)
                long interval = 200_000_000;
                while (accumulatedTime >= interval) {
                    dirChangedInTick = false; // Сброс флага изменения направления
                    model.update();
                    checkRestart(); // Проверка условий завершения игры
                    accumulatedTime -= interval;
                    updated = true;
                }

                if (updated) {
                    render(); // Рендеринг после обновления модели
                }
            }
        };
        timer.start();
    }

    private void initGridState(CellType[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = CellType.EMPTY;
            }
        }
    }

    private void updateGridState() {
        // Очищаем текущее состояние
        initGridState(currentState);

        // Заполняем змейками
        for (Snake snake : model.getSnakes()) {
            for (Point2D p : snake.getBody()) {
                int x = (int) p.getX();
                int y = (int) p.getY();
                if (p == snake.getHead()) {
                    currentState[x][y] = snake instanceof RobotSnake ? CellType.SNAKE_ROBOT_HEAD : CellType.SNAKE_PLAYER_HEAD;
                } else {
                    currentState[x][y] = snake instanceof RobotSnake ? CellType.SNAKE_ROBOT : CellType.SNAKE_PLAYER;
                }
            }
        }

        // Заполняем еду
        for (Point2D food : model.getFoods()) {
            int x = (int) food.getX();
            int y = (int) food.getY();
            if (x >= 0 && x < currentState.length &&
                    y >= 0 && y < currentState[0].length) {
                currentState[x][y] = CellType.FOOD;
            }
        }
    }

    @FXML
    private void onKey(KeyEvent e) {
        Snake player = model.getSnakes().getFirst();
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
        Snake player = model.getSnakes().getFirst();
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
                String content = "Твой счет: " + (player.getBody().size()-1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
                alert.setHeaderText(title);
                alert.showAndWait();
                startNewGame();
            });
        }
    }

    private void render() {
        updateGridState();

        // Определяем измененные клетки
        for (int x = 0; x < currentState.length; x++) {
            for (int y = 0; y < currentState[0].length; y++) {
                if (currentState[x][y] != prevState[x][y]) {
                    drawCell(x, y, currentState[x][y]);
                    prevState[x][y] = currentState[x][y];
                }
            }
        }

        lengthLabel.setText(String.valueOf(model.getSnakes().getFirst().getBody().size()-1));
    }

    private void drawCell(int x, int y, CellType type) {
        int cellSize = 20;

        switch (type) {
            case EMPTY:
                gc.setFill(Color.LIGHTGREEN);
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                break;
            case SNAKE_PLAYER:
                gc.setFill(Color.YELLOW);
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                break;
            case SNAKE_ROBOT:
                gc.setFill(Color.CORNFLOWERBLUE);
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                break;
            case FOOD:
                gc.setFill(Color.RED);
                gc.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
                break;
            case SNAKE_ROBOT_HEAD:
                gc.setFill(Color.DARKBLUE);
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                break;
            case SNAKE_PLAYER_HEAD:
                gc.setFill(Color.ORANGE);
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                break;
        }
    }
}