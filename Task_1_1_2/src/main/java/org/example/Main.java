package org.example;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Deck deck = new Deck();
        Player player = new Player();
        Player dealer = new Player();
        Scanner scan = new Scanner(System.in);
        int num_round = 0;

        System.out.print("\nДобро пожаловать в Блэкджек!\n");

        do {
            num_round++;
            System.out.print("Раунд " + num_round + "\nДилер раздал карты\n");
            deck.init_cards(player.cards_num, player.amt_points, player.check_ace);
            deck.init_cards(dealer.cards_num, dealer.amt_points, dealer.check_ace);

            deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, true);

            if (deck.check_win(player.amt_points) == 0)
            {
                System.out.print("\nВаш ход\n-------");
                System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
            }

            while(deck.check_win(player.amt_points) == 0 && scan.nextInt() != 0)
            {
                deck.put_card(player.cards_num, player.amt_points, player.check_ace);
                System.out.print("\nОткрыта карта "+deck.card_name(player.cards_num[player.amt_points[0]-1])+"\n");
                deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, true);
                System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
            }

            if (deck.check_win(player.amt_points) == 1)
            {
                player.amt_wins++;
                System.out.print("\nВы выиграли раунд! ");
            }
            else if (deck.check_win(player.amt_points) == 2)
            {
                dealer.amt_wins++;
                System.out.print("\nДилер выиграл раунд! ");
            }
            else
            {
                System.out.print("\nХод дилера\n-------\nДилер открывает закрытую карту ");
                deck.card_name(dealer.cards_num[1]);
                deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, false);
                while(deck.check_win(dealer.amt_points) == 0 && dealer.amt_points[1] < 17)
                {
                    deck.put_card(dealer.cards_num, dealer.amt_points, dealer.check_ace);
                    System.out.print("\nОткрыта карта "+deck.card_name(dealer.cards_num[dealer.amt_points[0]-1])+"\n");
                    deck.print_cards(player.cards_num, player.amt_points, dealer.cards_num, dealer.amt_points, false);
                }
                if (deck.check_win(dealer.amt_points) == 1)
                {
                    dealer.amt_wins++;
                    System.out.print("\nДилер выиграл раунд! ");
                }
                else if (deck.check_win(dealer.amt_points) == 2)
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

            deck.print_score(player.amt_wins, dealer.amt_wins);

            //Putting the cards back into the deck
            player.amt_points[1] = 0;
            player.amt_points[0] = 0;
            dealer.amt_points[1] = 0;
            dealer.amt_points[0] = 0;
            player.check_ace = false;
            dealer.check_ace = false;

            System.out.print("\nВведите “1”, чтобы начать следующий раунд, и “0”, чтобы закончить игру...\n");
        } while (scan.nextInt() != 0);
    }
}