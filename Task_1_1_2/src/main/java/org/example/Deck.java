package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11,1};
    public String[] names = {"Двойка","Тройка","Четверка","Пятерка","Шестерка","Семерка","Восьмерка","Девятка","Десятка","Валет","Дама","Король","Туз"};
    public String[] mast = {"Пики","Бубны","Трефы","Червы"};
    private final List<Integer> queue = new ArrayList<>();
    private int cnt = 0;

    public void uniqueListGenerator() {
        for (int i = 0; i < 52; i++) {
            this.queue.add(i);
        }
        Collections.shuffle(this.queue); // Перемешиваем числа для случайного порядка
    }

    public void put_card(Player player)
    {
        int val = this.queue.get(this.cnt++);
        player.points += this.values[val/4];
        player.cards_num.add(val);
        player.amt++;
        player.check_ace = check_for_ace(player.points, player.check_ace);
        if (player.check_ace)
        {
            swap_ace(player);
        }
    }

    public void init_cards(Player player)
    {
        put_card(player);
        put_card(player);
    }

    public boolean check_for_ace(int points, boolean check) {
        return (points > 21) || check;
    }

    public void swap_ace(Player player) {
        for(int i = 0; i < player.amt; ++i)
        {
            if(this.values[player.cards_num.get(i)/4]==11)
            {
                player.cards_num.set(i, player.cards_num.get(i)+4);
                player.points -= 10;
            }
        }
    }

    public String card_name(int num_card){
        if (num_card >= 52){
            return names[(num_card-4)/4]+" "+mast[(num_card-4)%4]+" ("+values[num_card/4]+")";
        }
        return names[num_card/4]+" "+mast[num_card%4]+" ("+values[num_card/4]+")";
    }

    public void print_cards(List<Integer> cards_num_player, int amt_player, int points_player, List<Integer> cards_num_dealer, int amt_dealer, int points_dealer, boolean closed)
    {
        System.out.print("\tВаши карты");
        out_cards(cards_num_player, amt_player, points_player);

        System.out.print("\tКарты Дилера");
        if (closed)
        {
            System.out.print(": ["+card_name(cards_num_dealer.getFirst())+", <закрытая карта>]\n");
        }
        else
        {
            out_cards(cards_num_dealer, amt_dealer, points_dealer);
        }
    }

    public void out_cards(List<Integer> cards_num, int amt, int points)
    {
        System.out.print(": [");
        for(int i = 0; i < amt; ++i)
        {
            System.out.print(card_name(cards_num.get(i)));
            if(i!=amt-1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("] -> "+points+"\n");
    }
}