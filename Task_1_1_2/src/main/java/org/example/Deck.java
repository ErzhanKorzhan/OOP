package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11,1};
    private final String[] names = {"Двойка","Тройка","Четверка","Пятерка","Шестерка","Семерка","Восьмерка","Девятка","Десятка","Валет","Дама","Король","Туз"};
    private final String[] mast = {"Пики","Бубны","Трефы","Червы"};
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
        player.setCountCards(player.getCountCards() + 1);
        player.setPoints(player.getPoints() + this.values[val/4]);
        player.getNewCard(val);
        player.setFlag(check_for_ace(player.getPoints(), player.getFlag()));
        if (player.getFlag())
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
        for(int i = 0; i < player.getCountCards(); ++i)
        {
            if(this.values[player.getCardNum(i)/4]==11)
            {
                player.setCardNum(i, player.getCardNum(i)+4);
                player.setPoints(player.getPoints() - 10);
            }
        }
    }

    public String card_name(int num_card){
        if (num_card >= 52){
            return names[(num_card-4)/4]+" "+mast[(num_card-4)%4]+" ("+values[num_card/4]+")";
        }
        return names[num_card/4]+" "+mast[num_card%4]+" ("+values[num_card/4]+")";
    }

    public void print_cards(Player player, Player dealer, boolean closed)
    {
        System.out.print("\tВаши карты");
        out_cards(player);

        System.out.print("\tКарты Дилера");
        if (closed)
        {
            System.out.print(": ["+card_name(dealer.getCardNum(0))+", <закрытая карта>]\n");
        }
        else
        {
            out_cards(dealer);
        }
    }

    public void out_cards(Player pl)
    {
        System.out.print(": [");
        for(int i = 0; i < pl.getCountCards(); ++i)
        {
            System.out.print(card_name(pl.getCardNum(i)));
            if(i!=pl.getCountCards()-1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("] -> "+pl.getPoints()+"\n");
    }
}