package org.example;

import java.util.Scanner;

public class Game {
    public void round_game(int num_round, Deck deck, Player player, Player dealer){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nРаунд " + num_round + "\nДилер раздал карты\n");
        deck.init_cards(player);
        deck.init_cards(dealer);
        deck.print_cards(player, dealer, true);

        if (player.check_win(player) == 0)
        {
            System.out.print("\nВаш ход\n-------");
            System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        }

        while(player.check_win(player) == 0 && scan.nextInt() != 0)
        {
            deck.put_card(player);
            System.out.print("\nОткрыта карта "+deck.card_name(player.getCardNum(player.getCountCards() - 1))+"\n");
            deck.print_cards(player, dealer, true);
            System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        }

        if (player.check_win(player) == 1)
        {
            player.setAmtWins(player.getAmtWins() + 1);
            System.out.print("\nВы выиграли раунд! ");
        }
        else if (player.check_win(player) == 2)
        {
            dealer.setAmtWins(dealer.getAmtWins() + 1);
            System.out.print("\nДилер выиграл раунд! ");
        }
        else
        {
            System.out.print("\nХод дилера\n-------\nДилер открывает закрытую карту ");
            deck.card_name(dealer.getCardNum(1));
            deck.print_cards(player, dealer, false);
            while(dealer.check_win(dealer) == 0 && dealer.getPoints() < 17)
            {
                deck.put_card(dealer);
                System.out.print("\nОткрыта карта "+deck.card_name(dealer.getCardNum(dealer.getCountCards() -1))+"\n");
                deck.print_cards(player, dealer, false);
            }
            if (dealer.check_win(dealer) == 1)
            {
                dealer.setAmtWins(dealer.getAmtWins() + 1);
                System.out.print("\nДилер выиграл раунд! ");
            }
            else if (dealer.check_win(dealer) == 2)
            {
                player.setAmtWins(player.getAmtWins() + 1);
                System.out.print("\nВы выиграли раунд! ");
            }
            else
            {
                if (player.getPoints() > dealer.getPoints())
                {
                    player.setAmtWins(player.getAmtWins() + 1);
                    System.out.print("\nВы выиграли раунд! ");
                }
                else if (dealer.getPoints() > player.getPoints())
                {
                    dealer.setAmtWins(dealer.getAmtWins() + 1);
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
        player.setPoints(0);
        player.setCountCards(0);
        dealer.setPoints(0);
        dealer.setCountCards(0);
        player.setFlag(false);
        dealer.setFlag(false);
        player.cardsClear();
        dealer.cardsClear();

        System.out.print("\nВведите “1”, чтобы начать следующий раунд, и “0”, чтобы закончить игру...\n");
    }
}
