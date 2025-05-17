import javafx.geometry.Point2D;
import org.example.snake.model.Direction;
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
        Point2D food = model.getFoods().iterator().next();

        snake.setDirection(food.getX() > 5 ? Direction.RIGHT : Direction.LEFT);
        model.update(); // Двигаемся к еде

        assertFalse(model.getFoods().contains(food));
        assertTrue(snake.getLength() > 1);
    }

    @Test
    void winConditionTriggered() {
        GameModel model = new GameModel(10, 10, 1, 3);
        Snake snake = new Snake(new Point2D(0, 0));
        model.addSnake(snake);

        snake.grow();
        snake.grow();
        model.update(); // Длина становится 3

        assertTrue(snake.isWinner());
    }
}