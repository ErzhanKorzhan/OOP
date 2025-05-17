import javafx.geometry.Point2D;
import org.example.snake.model.Direction;
import org.example.snake.model.GameModel;
import org.example.snake.model.Snake;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTests {
    @Test
    void moveSnakeWithoutGrowth() {
        GameModel game = new GameModel(10, 10, 1, 5);
        Snake snake = new Snake(new Point2D(5, 5));
        snake.setDirection(Direction.RIGHT);
        int initialLength = snake.getLength();

        snake.move(game);

        assertEquals(new Point2D(6, 5), snake.getHead());
        assertEquals(initialLength, snake.getLength());

        snake.move(game);

        assertEquals(initialLength, snake.getLength());
    }

    @Test
    void growIncreasesLength() {
        GameModel model = new GameModel(10, 10, 1, 5);
        Snake snake = new Snake(new Point2D(5, 5));

        snake.grow();
        snake.move(model);

        snake.grow();
        snake.move(model);

        assertEquals(3, snake.getLength());
    }

    @Test
    void fakeSelfCollision() {
        GameModel gameModel = new GameModel(10, 10, 1, 5);
        Snake snake = new Snake(new Point2D(5, 5));

        snake.grow();
        snake.move(gameModel);

        snake.grow();
        snake.move(gameModel);

        snake.setDirection(Direction.UP);
        snake.move(gameModel);

        snake.setDirection(Direction.LEFT);
        snake.move(gameModel);

        snake.setDirection(Direction.DOWN);
        snake.move(gameModel);

        assertTrue(snake.isAlive());
    }
}