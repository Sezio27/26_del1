package spil;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        RaffleCup r1 = new RaffleCup();
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Player p1 = new Player(1, 0);
        Player p2 = new Player(2, 0);

        Scanner scan = new Scanner(System.in);
        System.out.println("Starting game...");
        System.out.println("Press enter to shake the raffle cup and roll the dice.");

        while (true) {

            printCurrentPoints(p1, p2);

            roll(r1, d1, d2, p1, scan);

            if (hasWon(p1)) {
                printCurrentPoints(p1, p2);
                break;
            }

            roll(r1, d1, d2, p2, scan);

            if (hasWon(p2)) {
                printCurrentPoints(p1, p2);
                break;
            }
        }
        scan.close();
    }

    public static void roll(RaffleCup r1, Dice d1, Dice d2, Player player, Scanner scan) {
        System.out.println("Player " + player.getPlayerNumber() + "'s turn to shake and roll the dice!");
        scan.nextLine();

        r1.roll(d1, d2);
        System.out.println("Dice 1: " + d1.getFaceValue());
        System.out.println("Dice 2: " + d2.getFaceValue());
        System.out.println();

        int earnedPoints=  r1.getSum(d1, d2);
        player.setPoints(player.getPoints()+earnedPoints);

        if (r1.getEns(d1, d2)) {
            earnedPoints = r1.getSum(d1, d2);
            player.setPoints(player.getPoints() + earnedPoints);
            if(d1.getFaceValue() == 1) {
                System.out.println("Darn! you hit two ones, return to start :(");
                player.setPoints(0);
            } else {
                System.out.println("Congratulations you rolled two identical and got " + earnedPoints + " points!");
                System.out.println("You may roll again! 😎👍");
                roll(r1, d1, d2, player, scan);
            }
            System.out.println();
        }

    }

    public static boolean hasWon(Player p) {
        if (p.getPoints() >= 40) {
            System.out.println("Player " + p.getPlayerNumber() + " has won the game!");
            return true;
        }
        return false;
    }

    public static void printCurrentPoints(Player p1, Player p2) {
        System.out.println("Current Points:");
        System.out.println("Player " + p1.getPlayerNumber() + ": " + p1.getPoints());
        System.out.println("Player " + p2.getPlayerNumber() + ": " + p2.getPoints());
        System.out.println();
    }
}
