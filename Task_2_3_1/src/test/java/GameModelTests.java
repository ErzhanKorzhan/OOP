import javafx.geometry.Point2D;
import org.example.snake.model.GameModel;
import org.example.snake.model.Snake;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTests {
    @Test
    void foodSpawnAndConsumption() {
        GameModel model = new GameModel(10, 10, 1, 5);
        Snake snake = new Snake(new Point2D(5, 5));
        model.addSnake(snake);
        assertFalse(model.getFoods().isEmpty());
        assertEquals(1, snake.getLength());
    }

    @Test
    void winConditionTriggered() {
        GameModel model = new GameModel(10, 10, 1, 3);
        Snake snake = new Snake(new Point2D(0, 0));
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