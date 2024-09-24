package ru.nsu.lavrenenkov.blackjack;

import java.util.Scanner;

/**
 * Game class, containing round and win logic.
 *
 * @author vadim_lavrenenkov
 *
 * @version 1.0
 */
public class Game {

    /**
     *Default main.
     *
     * @param args - default for main
     */
    public static void main(String[] args) {
        startGame();
    }

    /**
     * Method for holding a single round.
     *
     * @param round - round number
     * @param scanner - for user input
     * @return - true if player won, false if dealer won
     */
    public static boolean round(int round, Scanner scanner) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand dealer = new Hand();
        dealer.takeCard(deck);
        dealer.takeCard(deck);


        Hand player = new Hand();
        player.takeCard(deck);
        player.takeCard(deck);


        System.out.println("Раунд " + round);

        System.out.println("Дилер раздал карты");

        System.out.println("Ваши карты");
        System.out.println(player + " => " + player.checkValues());

        System.out.println("Карты дилера:");
        System.out.println("[" + dealer.getNcard(0) + ", <закрытая карта>]");

        if (player.checkValues() == 21) {
            return true;
        }
        System.out.println("Ваш ход \n -------");

        while (true) {

            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться");

            String decision = scanner.nextLine().strip();

            while (!decision.equals("1") && !decision.equals("0")) {
                System.out.println("Неверный ввод");
                decision = scanner.nextLine().strip();
            }
            if (decision.equals("1")) {
                player.takeCard(deck);

                System.out.println("Вы открыли карту "
                                   + player.getLastCard());
                int sym = player.checkValues();

                System.out.println("Ваши карты");
                System.out.println(player + " => " + sym);

                System.out.println("Карты дилера:");
                System.out.println("["
                                   + dealer.getNcard(0)
                                   + ", <закрытая карта>]");

                if (sym > 21) {
                    return false;
                }
            }
            if (decision.equals("0")) {
                break;
            }
        }

        System.out.println("Ход дилера \n -------");
        System.out.println("Дилер открывает закрытую карту "
                           + dealer.getLastCard());

        System.out.println("Ваши карты");
        System.out.println(player + " => " + player.checkValues());

        int sym = dealer.checkValues();
        System.out.println("Карты Дилера");
        System.out.println(dealer + " => " + sym);

        if (sym == 21) {
            return false;
        }

        while (sym < 17) {
            dealer.takeCard(deck);
            System.out.println("Дилер открывает карту "
                               + dealer.getLastCard());
            sym = dealer.checkValues();

            System.out.println("Ваши карты");
            System.out.println(player + " => " + player.checkValues());

            System.out.println("Карты Дилера");
            System.out.println(dealer + " => " + sym);

            if (sym > 21) {
                return true;
            }
        }

        return player.checkValues() >= dealer.checkValues();
    }

    /**
     * Method for holding a single game(several rounds).
     *
     */

    public static void startGame() {
        int roundCounter = 1;
        int playerWin = 0;
        int dealerWin = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в блэкджек!");
        while (playerWin != 3 && dealerWin != 3) {
            boolean check = round(roundCounter, scanner);
            if (check) {
                playerWin += 1;
                System.out.println("Вы выиграли! Счет " + playerWin + ":" + dealerWin + "\n\n");
            } else {
                dealerWin += 1;
                System.out.println("Вы проиграли. Счет " + playerWin + ":" + dealerWin + "\n\n");
            }
            roundCounter += 1;
        }
        System.out.println("Финальный счет " + playerWin + ":" + dealerWin);

    }
}
