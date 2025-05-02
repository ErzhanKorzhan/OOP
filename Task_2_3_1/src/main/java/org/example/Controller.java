package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;

public class Controller {
    @FXML private Canvas gameCanvas;
    private Snake snake;
    private GameBoard gameBoard;
    private GameState gameState;
    private static final int CELL_SIZE = 20;

    @FXML
    public void initialize() {
        gameBoard = new GameBoard(20, 15);
        snake = new Snake(new Point2D(10, 7));
        gameState = new GameState(10);
        gameBoard.generateFood(snake);

        gameCanvas.setFocusTraversable(true);
        gameCanvas.requestFocus();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    updateGame();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                render();
            }
        };
        timer.start();
    }

    private void updateGame() throws InterruptedException {
        if (gameState.isGameOver() || gameState.isGameWon()) return;

        Thread.sleep(200);
        snake.move();
        checkCollisions();
        checkFood();
        checkWin();
    }

    private void checkCollisions() {
        Point2D head = snake.getHead();
        if (head.getX() < 0 || head.getX() >= gameBoard.getWidth() ||
                head.getY() < 0 || head.getY() >= gameBoard.getHeight() ||
                snake.checkSelfCollision()) {
            gameState.setGameOver(true);
        }
    }

    private void checkFood() {
        Point2D head = snake.getHead();
        if (gameBoard.getFoods().contains(head)) {
            snake.grow();
            gameBoard.getFoods().remove(head);
            gameBoard.generateFood(snake);
        } else {

            snake.getSegments().remove(snake.getSegments().size() - 1);
        }
    }

    private void checkWin() {
        if (snake.getSegments().size() >= gameState.getTargetLength()) {
            gameState.setGameWon(true);
        }
    }

    private void render() {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Отрисовка змейки
        gc.setFill(javafx.scene.paint.Color.GREEN);
        for (Point2D segment : snake.getSegments()) {
            gc.fillRect(segment.getX() * CELL_SIZE, segment.getY() * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
        }

        // Отрисовка еды
        gc.setFill(javafx.scene.paint.Color.RED);
        for (Point2D food : gameBoard.getFoods()) {
            gc.fillOval(food.getX() * CELL_SIZE, food.getY() * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
        }

        if (gameState.isGameOver()) {
            gc.fillText("Game Over!", 10, 20);
        } else if (gameState.isGameWon()) {
            gc.fillText("You Win!", 10, 20);
        }
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> snake.setDirection(Direction.UP);
            case DOWN -> snake.setDirection(Direction.DOWN);
            case LEFT -> snake.setDirection(Direction.LEFT);
            case RIGHT -> snake.setDirection(Direction.RIGHT);
        }
    }
}