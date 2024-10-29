package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int count_of_cards = 0;
    private int points = 0;
    private final List<Integer> cards_num = new ArrayList<>();
    private boolean flag_ace = false;
    private int wins = 0;

    public void setCountCards(int val){
        this.count_of_cards = val;
    }

    public void setPoints(int val){
        this.points = val;
    }

    public void setAmtWins(int val){
        this.wins = val;
    }

    public int getAmtWins(){
        return wins;
    }

    public void setFlag(boolean val){
        this.flag_ace = val;
    }

    public int getCardNum(int id){
        return cards_num.get(id);
    }

    public void setCardNum(int id, int val){
        this.cards_num.set(id, val);
    }

    public void getNewCard(int val){
        this.cards_num.add(val);
    }

    public void cardsClear(){
        this.cards_num.clear();
    }

    public int getCountCards(){
        return count_of_cards;
    }

    public int getPoints(){
        return points;
    }

    public boolean getFlag(){
        return flag_ace;
    }

    public void print_score(Player player, Player dealer)
    {
        if (player.getAmtWins()>dealer.getAmtWins())
        {
            System.out.print("Счет " + player.getAmtWins() + ":" + dealer.getAmtWins() +" в вашу пользу.\n");
        }
        else if(dealer.getAmtWins()>player.getAmtWins())
        {
            System.out.print("Счет " + player.getAmtWins() + ":" + dealer.getAmtWins() + " в пользу дилера.\n");
        }
        else
        {
            System.out.print("Счет " + player.getAmtWins() + ":" + dealer.getAmtWins() + ".\n");
        }
    }

    public int check_win(Player player)
    {
        if(player.getPoints() == 21)
        {
            return 1;
        }
        else if (player.getPoints() > 21)
        {
            return 2;
        }
        return 0;
    }
}