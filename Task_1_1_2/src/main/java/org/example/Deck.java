package org.example;

import java.util.Random;

public class Deck {
    public int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11,1};
    public String[] names = {"Двойка","Тройка","Четверка","Пятерка","Шестерка","Семерка","Восьмерка","Девятка","Десятка","Валет","Дама","Король","Туз"};
    public String[] mast = {"Пики","Бубны","Трефы","Червы"};
    private final int[] used_cards = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public void put_card(int[] cards_num, int[] amt_points, boolean check_ace)
    {
        Random rnd = new Random();
        int index = rnd.nextInt(52);
        while (this.used_cards[index] != 0)
        {
            index = rnd.nextInt(52);
        }
        this.used_cards[index] = 1;
        amt_points[1] += this.values[index/4];
        cards_num[amt_points[0]++] = index;
        check_ace = check_for_ace(amt_points, check_ace);
        if (check_ace)
        {
            swap_ace(cards_num, amt_points);
        }
    }

    public void init_cards(int[] cards_num, int[] amt_points, boolean check_ace)
    {
        put_card(cards_num, amt_points, check_ace);
        put_card(cards_num, amt_points, check_ace);
    }

    public boolean check_for_ace(int[] amt_points, boolean check) {
        return (amt_points[1] > 21) || check;
    }

    public void swap_ace(int[] cards_num, int[] amt_points) {
        for(int i = 0; i < amt_points[0]; ++i)
        {
            if(this.values[cards_num[i]/4]==11)
            {
                cards_num[i] += 4;
                amt_points[1] -= 10;
            }
        }
    }

    public int check_win(int[] amt_points)
    {
        if(amt_points[1] == 21)
        {
            return 1;
        }
        else if (amt_points[1] > 21)
        {
            return 2;
        }
        return 0;
    }

    public String card_name(int num_card){
        if (num_card >= 52){
            return names[(num_card-4)/4]+" "+mast[(num_card-4)%4]+" ("+values[num_card/4]+")";
        }
        return names[num_card/4]+" "+mast[num_card%4]+" ("+values[num_card/4]+")";
    }

    public void print_score(int player_wins, int dealer_wins)
    {
        if (player_wins>dealer_wins)
        {
            System.out.print("Счет " + player_wins + ":" + dealer_wins +" в вашу пользу.\n");
        }
        else if(dealer_wins>player_wins)
        {
            System.out.print("Счет " + player_wins + ":" + dealer_wins + " в пользу дилера.\n");
        }
        else
        {
            System.out.print("Счет " + player_wins + ":" + dealer_wins + ".\n");
        }
    }

    public void print_cards(int[] cards_num_player, int[] amt_points_player, int[] cards_num_dealer, int[] amt_points_dealer, boolean closed)
    {
        System.out.print("\tВаши карты");
        out_cards(cards_num_player, amt_points_player);

        System.out.print("\tКарты Дилера");
        if (closed)
        {
            System.out.print(": ["+card_name(cards_num_dealer[0])+", <закрытая карта>]\n");
        }
        else
        {
            out_cards(cards_num_dealer, amt_points_dealer);
        }
    }

    public void out_cards(int[] cards_num, int[] amt_points)
    {
        System.out.print(": [");
        for(int i = 0; i < amt_points[0]; ++i)
        {
            System.out.print(card_name(cards_num[i]));
            if(i!=amt_points[0]-1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("] -> "+amt_points[1]+"\n");
    }
}