import org.example.*;
import org.example.Number;
import org.junit.jupiter.api.Test;

public class UnitTests
{
    @Test
    void ReadStrTest()
    {
        Expression expr = ExpressionParser.parse("3 + 2 * x");

        assert expr.equals(new Add(new Number(3), new Mul(new Number (2), new Variable("x"))));
    }

    @Test
    void SimplifyTest()
    {
        Expression expr = ExpressionParser.parse("x + 4 * 0");
        assert expr.simplify().equals(new Variable("x"));
    }

    @Test
    void EvalTest()
    {
        Expression expr = ExpressionParser.parse("(x + 7*y + 3 - 71) / z");
        assert(expr.eval("x = 10; y = 10; z = 3") == 4);
    }

    @Test
    void DerivativeTest()
    {
        Expression expr = ExpressionParser.parse("x + 7*y + 3 - 71");
        assert expr.derivative("y").simplify().equals(new Number(7));
    }

    @Test
    void EqualsTest()
    {
        Expression expr = ExpressionParser.parse("y + 4");
        assert expr.equals(new Add(new Number(4), new Variable("y")));
    }
}