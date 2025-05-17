//import javafx.geometry.Point2D;
//import org.example.snake.model.Direction;
//import org.example.snake.model.GameModel;
//import org.example.snake.model.Snake;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class SnakeTests {
//    @Test
//    void moveSnakeWithoutGrowth() {
//        Snake snake = new Snake(new Point2D(5, 5));
//        snake.setDirection(Direction.RIGHT);
//        int initialLength = snake.getLength();
//
//        snake.move(new GameModel(10, 10, 1, 5));
//        assertEquals(new Point2D(6, 5), snake.getHead());
//        assertEquals(initialLength + 1, snake.getLength());
//
//        snake.move(new GameModel(10, 10, 1, 5));
//        assertEquals(initialLength, snake.getLength());
//    }
//
//    @Test
//    void growIncreasesLength() {
//        Snake snake = new Snake(new Point2D(5, 5));
//        snake.grow();
//        snake.setDirection(Direction.RIGHT);
//
//        snake.move(new GameModel(10, 10, 1, 5));
//        snake.move(new GameModel(10, 10, 1, 5));
//
//        assertEquals(3, snake.getLength());
//    }
//
//    @Test
//    void selfCollisionKillsSnake() {
//        Snake snake = new Snake(new Point2D(5, 5));
//        snake.setDirection(Direction.UP);
//        snake.move(new GameModel(10, 10, 1, 5)); // (5,4)
//        snake.setDirection(Direction.LEFT);
//        snake.move(new GameModel(10, 10, 1, 5)); // (4,4)
//        snake.setDirection(Direction.DOWN);
//        snake.move(new GameModel(10, 10, 1, 5)); // (4,5)
//        snake.setDirection(Direction.RIGHT); // Следующий ход в (5,5), который уже в теле
//
//        snake.move(new GameModel(10, 10, 1, 5));
//        assertFalse(snake.isAlive());
//    }
//}