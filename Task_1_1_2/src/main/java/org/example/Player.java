package org.example;

public class Player {
    public int[] amt_points = {0,0};
    public int[] cards_num = new int[52];
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
        if(player.amt_points[1] == 21)
        {
            return 1;
        }
        else if (player.amt_points[1] > 21)
        {
            return 2;
        }
        return 0;
    }
}