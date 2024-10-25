package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public int amt = 0;
    public int points = 0;
    public List<Integer> cards_num = new ArrayList<>();
    public boolean check_ace = false;
    public int amt_wins = 0;

    public void print_score(Player player, Player dealer)
    {
        if (player.amt_wins>dealer.amt_wins)
        {
            System.out.print("Счет " + player.amt_wins + ":" + dealer.amt_wins +" в вашу пользу.\n");
        }
        else if(dealer.amt_wins>player.amt_wins)
        {
            System.out.print("Счет " + player.amt_wins + ":" + dealer.amt_wins + " в пользу дилера.\n");
        }
        else
        {
            System.out.print("Счет " + player.amt_wins + ":" + dealer.amt_wins + ".\n");
        }
    }

    public int check_win(Player player)
    {
        if(player.points == 21)
        {
            return 1;
        }
        else if (player.points > 21)
        {
            return 2;
        }
        return 0;
    }
}