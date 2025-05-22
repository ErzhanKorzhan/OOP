import javafx.geometry.Point2D;
import org.example.snake.model.GameModel;
import org.example.snake.model.Snake;
import org.example.snake.model.SnakeModel;
import org.example.snake.snakes.PlayerSnake;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTests {
    @Test
    void modelAndSnakeInit() {
        GameModel model = new SnakeModel(10, 10, 1, 5);
        Snake snake = new PlayerSnake(new Point2D(5, 5));
        model.addSnake(snake);
        assertTrue(model.getFoods().isEmpty());
        assertEquals(1, snake.getBody().size());
    }

    @Test
    void winConditionTriggered() {
        GameModel model = new SnakeModel(10, 10, 1, 3);
        Snake snake = new PlayerSnake(new Point2D(0, 0));
        model.addSnake(snake);

        snake.grow();
        snake.move(model);

        snake.grow();
        snake.move(model);

        snake.grow();
        snake.move(model);

        model.update();

        assertTrue(snake.isWinner());
    }
}