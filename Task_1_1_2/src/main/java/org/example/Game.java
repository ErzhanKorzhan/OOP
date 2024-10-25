package org.example;

import java.util.Scanner;

public class Game {
    public void round_game(int num_round, Deck deck, Player player, Player dealer){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nРаунд " + num_round + "\nДилер раздал карты\n");
        deck.init_cards(player);
        deck.init_cards(dealer);
        deck.print_cards(player.cards_num, player.amt, player.points, dealer.cards_num, dealer.amt, dealer.points, true);

        if (player.check_win(player) == 0)
        {
            System.out.print("\nВаш ход\n-------");
            System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        }

        while(player.check_win(player) == 0 && scan.nextInt() != 0)
        {
            deck.put_card(player);
            System.out.print("\nОткрыта карта "+deck.card_name(player.cards_num.get(player.amt - 1))+"\n");
            deck.print_cards(player.cards_num, player.amt, player.points, dealer.cards_num, dealer.amt, dealer.points, true);
            System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        }

        if (player.check_win(player) == 1)
        {
            player.amt_wins++;
            System.out.print("\nВы выиграли раунд! ");
        }
        else if (player.check_win(player) == 2)
        {
            dealer.amt_wins++;
            System.out.print("\nДилер выиграл раунд! ");
        }
        else
        {
            System.out.print("\nХод дилера\n-------\nДилер открывает закрытую карту ");
            deck.card_name(dealer.cards_num.get(1));
            deck.print_cards(player.cards_num, player.amt, player.points, dealer.cards_num, dealer.amt, dealer.points, false);
            while(dealer.check_win(dealer) == 0 && dealer.points < 17)
            {
                deck.put_card(dealer);
                System.out.print("\nОткрыта карта "+deck.card_name(dealer.cards_num.get(dealer.amt-1))+"\n");
                deck.print_cards(player.cards_num, player.amt, player.points, dealer.cards_num, dealer.amt, dealer.points, false);
            }
            if (dealer.check_win(dealer) == 1)
            {
                dealer.amt_wins++;
                System.out.print("\nДилер выиграл раунд! ");
            }
            else if (dealer.check_win(dealer) == 2)
            {
                player.amt_wins++;
                System.out.print("\nВы выиграли раунд! ");
            }
            else
            {
                if (player.points > dealer.points)
                {
                    player.amt_wins++;
                    System.out.print("\nВы выиграли раунд! ");
                }
                else if (dealer.points > player.points)
                {
                    dealer.amt_wins++;
                    System.out.print("\nДилер выиграл раунд! ");
                }
                else
                {
                    System.out.print("\nНичья! ");
                }
            }
        }

        player.print_score(player, dealer);

        //Putting the cards back into the deck
        player.points = 0;
        player.amt = 0;
        dealer.points = 0;
        dealer.amt = 0;
        player.check_ace = false;
        dealer.check_ace = false;
        player.cards_num.clear();
        dealer.cards_num.clear();

        System.out.print("\nВведите “1”, чтобы начать следующий раунд, и “0”, чтобы закончить игру...\n");
    }
}
