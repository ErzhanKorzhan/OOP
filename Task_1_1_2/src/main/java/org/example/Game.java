package org.example;

import java.util.Scanner;

public class Game {
    public void round_game(int num_round, Deck deck, Player player, Player dealer){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nРаунд " + num_round + "\nДилер раздал карты\n");
        deck.init_cards(player.cards_num, player.amt_points, player.check_ace);
        deck.init_cards(dealer.cards_num, dealer.amt_points, dealer.check_ace);
        deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, true);

        if (player.check_win(player) == 0)
        {
            System.out.print("\nВаш ход\n-------");
            System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        }

        while(player.check_win(player) == 0 && scan.nextInt() != 0)
        {
            deck.put_card(player.cards_num, player.amt_points, player.check_ace);
            System.out.print("\nОткрыта карта "+deck.card_name(player.cards_num[player.amt_points[0]-1])+"\n");
            deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, true);
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
            deck.card_name(dealer.cards_num[1]);
            deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, false);
            while(dealer.check_win(dealer) == 0 && dealer.amt_points[1] < 17)
            {
                deck.put_card(dealer.cards_num, dealer.amt_points, dealer.check_ace);
                System.out.print("\nОткрыта карта "+deck.card_name(dealer.cards_num[dealer.amt_points[0]-1])+"\n");
                deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, false);
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
                if (player.amt_points[1] > dealer.amt_points[1])
                {
                    player.amt_wins++;
                    System.out.print("\nВы выиграли раунд! ");
                }
                else if (dealer.amt_points[1] > player.amt_points[1])
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
        player.amt_points[1] = 0;
        player.amt_points[0] = 0;
        dealer.amt_points[1] = 0;
        dealer.amt_points[0] = 0;
        player.check_ace = false;
        dealer.check_ace = false;

        System.out.print("\nВведите “1”, чтобы начать следующий раунд, и “0”, чтобы закончить игру...\n");
    }
}
