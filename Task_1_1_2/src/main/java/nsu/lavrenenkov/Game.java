package nsu.lavrenenkov;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        startGame();
    }

    public static boolean round(int round, Scanner scanner) {
        Hand dealer = new Hand();
        Hand player = new Hand();

        Deck deck = new Deck();
        deck.shuffle();


        System.out.println("Раунд " + round);

        dealer.takeCard(deck);
        dealer.takeCard(deck);

        player.takeCard(deck);
        player.takeCard(deck);

        System.out.println("Дилер раздал карты");

        System.out.println("Ваши карты");
        System.out.println(player.cards.toString() + " => " + player.checkValues());

        System.out.println("Карты дилера:");
        System.out.println("["+dealer.cards.getFirst().toString() + ", <закрытая карта>]");



        if(player.checkValues() == 21) {
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
            if (decision.equals("1")){
                player.takeCard(deck);

                System.out.println("Вы открыли карту " + player.cards.getLast().toString());
                int sym = player.checkValues();

                System.out.println("Ваши карты");
                System.out.println(player.cards.toString() + " => " + sym);

                System.out.println("Карты дилера:");
                System.out.println("["+dealer.cards.getFirst().toString() + ", <закрытая карта>]");

                if(sym > 21){
                    return false;
                }
            }
            if (decision.equals("0")){
                break;
            }
        }

        System.out.println("Ход дилера \n -------");
        System.out.println("Дилер открывает закрытую карту " + dealer.cards.getLast().toString());

        System.out.println("Ваши карты");
        System.out.println(player.cards.toString() + " => " + player.checkValues());

        int sym = dealer.checkValues();
        System.out.println("Карты Дилера");
        System.out.println(dealer.cards.toString() + " => " + sym);

        if(sym == 21){
            return false;
        }

        while (sym < 17){
            dealer.takeCard(deck);
            System.out.println("Дилер открывает карту " + dealer.cards.getLast().toString());
            sym = dealer.checkValues();

            System.out.println("Ваши карты");
            System.out.println(player.cards.toString() + " => " + player.checkValues());

            System.out.println("Карты Дилера");
            System.out.println(dealer.cards.toString() + " => " + sym);

            if (sym > 21){
                return true;
            }
        }

        return player.checkValues() >= dealer.checkValues();
    }

    public static void startGame() {
        int roundCounter = 1;
        int player_win = 0;
        int dealer_win = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в блэкджек!");
        while (player_win != 3 && dealer_win != 3) {
            boolean check = round(roundCounter, scanner);
            if (check) {
                player_win += 1;
                System.out.println("Вы выиграли! Счет " + player_win + ":" + dealer_win +"\n\n");

            }
            else {
                dealer_win += 1;
                System.out.println("Вы проиграли. Счет " + player_win + ":" + dealer_win+ "\n\n");
            }
            roundCounter += 1;
        }
        System.out.println("Финальный счет " + player_win + ":" + dealer_win);

    }
}
