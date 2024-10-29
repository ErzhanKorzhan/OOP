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
        deck.uniqueListGenerator();
        boolean flag = true;
        pl.setFlag(false);
        pl.setPoints(0);
        pl.setCountCards(0);
        int amt = rnd.nextInt(52);
        for (int i = 0; i < amt; ++i)
        {
            deck.put_card(pl);
        }
        for (int i = 0; i < amt; ++i)
        {
            if(!(pl.getCardNum(i) < 56 && pl.getCardNum(i) >= 0))
            {
                flag = false;
                break;
            }
        }
        if (flag && (pl.getPoints() >= amt))
        {
            assertEquals(pl.getCountCards(),amt);
        }
        else
        {
            assertEquals(1,0);
        }
    }

    @Test
    void Checks_ace()
    {
        pl.setPoints(22);
        pl.setFlag(deck.check_for_ace(pl.getPoints(), false));
        assertTrue(pl.getFlag());
    }

    @Test
    void Card_name()
    {
        assertEquals(deck.card_name(20),"Семерка Пики (7)");
    }

    @Test
    void Win_check()
    {
        pl.setPoints(21);
        assertEquals(pl.check_win(pl),1);
    }

    @Test
    void Loss_check()
    {
        pl.setPoints(23);
        assertEquals(pl.check_win(pl),2);
    }
}