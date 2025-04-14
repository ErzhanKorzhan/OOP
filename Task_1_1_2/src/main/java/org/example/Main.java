package org.example;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Game game = new Game();
        Deck deck = new Deck();
        deck.uniqueListGenerator();
        Player player = new Player();
        Player dealer = new Player();
        int num_round = 0;

        System.out.print("\nДобро пожаловать в Блэкджек!\n");

        do {
            num_round++;
            game.round_game(num_round, deck, player, dealer);
        } while (scan.nextInt() != 0);
    }
}