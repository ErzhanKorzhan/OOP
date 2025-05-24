import javafx.geometry.Point2D;
import org.example.snake.model.Direction;
import org.example.snake.model.GameModel;
import org.example.snake.model.Snake;
import org.example.snake.model.SnakeModel;
import org.example.snake.snakes.PlayerSnake;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTests {
    @Test
    void moveSnakeWithoutGrowth() {
        GameModel game = new SnakeModel(10, 10, 1, 5);
        Snake snake = new PlayerSnake(new Point2D(5, 5));
        snake.setDirection(Direction.RIGHT);
        int initialLength = snake.getBody().size();

        snake.move(game);

        assertEquals(new Point2D(6, 5), snake.getHead());
        assertEquals(initialLength, snake.getBody().size());

        snake.move(game);

        assertEquals(initialLength, snake.getBody().size());
    }

    @Test
    void growIncreasesLength() {
        GameModel model = new SnakeModel(10, 10, 1, 5);
        Snake snake = new PlayerSnake(new Point2D(5, 5));

        snake.grow();
        snake.move(model);

        snake.grow();
        snake.move(model);

        assertEquals(3, snake.getBody().size());
    }

    @Test
    void fakeSelfCollision() {
        GameModel gameModel = new SnakeModel(10, 10, 1, 5);
        Snake snake = new PlayerSnake(new Point2D(5, 5));

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