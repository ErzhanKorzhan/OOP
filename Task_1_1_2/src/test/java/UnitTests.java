import org.example.Player;
import org.junit.jupiter.api.Test;

import java.util.Random;
import org.example.Deck;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests
{
    Random rnd = new Random();
    Player pl = new Player();
    Deck deck = new Deck();

    @Test
    void Add_cards()
    {
        boolean flag = true;
        pl.check_ace = false;
        pl.amt_points[1] = 0;
        pl.amt_points[0] = 0;
        int amt = rnd.nextInt(52);
        for (int i = 0; i < amt; ++i)
        {
            deck.put_card(pl.cards_num, pl.amt_points, pl.check_ace);
        }
        for (int i = 0; i < amt; ++i)
        {
            if(!(pl.cards_num[i] < 56 && pl.cards_num[i] >= 0))
            {
                flag = false;
                break;
            }
        }
        if (flag && (pl.amt_points[1] >= amt))
        {
            assertEquals(pl.amt_points[0],amt);
        }
        else
        {
            assertEquals(1,0);
        }
    }

    @Test
    void Checks_ace()
    {
        pl.amt_points[1] = 22;
        pl.check_ace = deck.check_for_ace(pl.amt_points, false);
        assertTrue(pl.check_ace);
    }

    @Test
    void Card_name()
    {
        assertEquals(deck.card_name(20),"Семерка Пики (7)");
    }

    @Test
    void Win_check()
    {
        pl.amt_points[1] = 21;
        assertEquals(pl.check_win(pl),1);
    }

    @Test
    void Loss_check()
    {
        pl.amt_points[1] = 23;
        assertEquals(pl.check_win(pl),2);
    }
}