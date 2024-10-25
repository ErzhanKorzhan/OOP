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
        pl.points = 0;
        pl.amt = 0;
        int amt = rnd.nextInt(52);
        for (int i = 0; i < amt; ++i)
        {
            deck.put_card(pl);
        }
        for (int i = 0; i < amt; ++i)
        {
            if(!(pl.cards_num.get(i) < 56 && pl.cards_num.get(i) >= 0))
            {
                flag = false;
                break;
            }
        }
        if (flag && (pl.points >= amt))
        {
            assertEquals(pl.amt,amt);
        }
        else
        {
            assertEquals(1,0);
        }
    }

    @Test
    void Checks_ace()
    {
        pl.points = 22;
        pl.check_ace = deck.check_for_ace(pl.points, false);
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
        pl.points = 21;
        assertEquals(pl.check_win(pl),1);
    }

    @Test
    void Loss_check()
    {
        pl.points = 23;
        assertEquals(pl.check_win(pl),2);
    }
}