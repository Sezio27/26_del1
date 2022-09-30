package spil;

import java.awt.*;
import java.util.Scanner;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Game {
    public static void main(String[] args) {
        run();
    }

    static GUI gui = new GUI();

    public static void run() {
        RaffleCup r1 = new RaffleCup();
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Player p1 = new Player(1, 0);
        Player p2 = new Player(2, 38);

        //GUI
        //Design choice for differentiating between cars
        GUI_Car[] cars = new GUI_Car[2];
        cars[0] = new GUI_Car(Color.WHITE, Color.BLACK, GUI_Car.Type.UFO, GUI_Car.Pattern.CHECKERED);
        cars[1] = new GUI_Car(Color.CYAN, Color.PINK, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.DOTTED);

        GUI_Player[] gui_players = new GUI_Player[2];
        for (int i = 0; i < 2; i++) {
            //Creating the players
            gui_players[i] = new GUI_Player("P" + (i + 1), 0, cars[i]);
            //Adding players to the board
            gui.addPlayer(gui_players[i]);
        }


        Scanner scan = new Scanner(System.in);
        System.out.println("Starting game...");
        System.out.println("Press enter to shake the raffle cup and roll the dice.");

        printCurrentPoints(p1, p2);
        while (true) {
            if (!hasOver40Points(p1)) {
                roll(r1, d1, d2, p1, scan);
                //Adding points to the GUI (p1)
                gui_players[0].setBalance(p1.getPoints());
                printCurrentPoints(p1, p2);
            }
            if (hasOver40Points(p2)) {
                rollForVictory(r1, d1, d2, p2, scan);
                if (r1.getEns(d1, d2)) {
                    System.out.println("Congratulations you won");
                    System.out.println();
                    break;
                }
            }
            if (!hasOver40Points(p2)) {
                roll(r1, d1, d2, p2, scan);
                //Adding points to the GUI (p2)
                gui_players[1].setBalance(p2.getPoints());
                printCurrentPoints(p1, p2);
            }
            if (hasOver40Points(p1)) {
                rollForVictory(r1, d1, d2, p1, scan);
                if (r1.getEns(d1, d2)) {
                    System.out.println("Congratulations you won");
                    System.out.println();
                    break;
                }
            }
        }


        scan.close();
    }

    public static void roll(RaffleCup r1, Dice d1, Dice d2, Player player, Scanner scan) {
        System.out.print("Player " + player.getPlayerNumber() + "'s turn to shake and roll the dice!");
        scan.nextLine();

        r1.roll(d1, d2);
        System.out.println("Dice 1: " + d1.getFaceValue());
        System.out.println("Dice 2: " + d2.getFaceValue());
        System.out.println();
        //GUI showcase of the dicethrow
        gui.setDice(d1.getFaceValue(), d2.getFaceValue());

        int earnedPoints;

        if (r1.getEns(d1, d2)) {
            earnedPoints = r1.getSum(d1, d2);
            player.setPoints(player.getPoints() + earnedPoints);
            System.out.println("Congratulations you rolled two identical and got " + earnedPoints + " points!");
            System.out.println();
        }


    }

    public static void rollForVictory(RaffleCup r1, Dice d1, Dice d2, Player player, Scanner scan) {
        System.out.print("Player " + player.getPlayerNumber() + "'s turn to shake and roll the dice!");
        scan.nextLine();

        r1.roll(d1, d2);
        System.out.println("Dice 1: " + d1.getFaceValue());
        System.out.println("Dice 2: " + d2.getFaceValue());
        System.out.println();
        //GUI showcase of the dicethrow
        gui.setDice(d1.getFaceValue(), d2.getFaceValue());
    }

    public static boolean hasOver40Points(Player p) {
        return p.getPoints() >= 40;
    }

    public static void printCurrentPoints(Player p1, Player p2) {
        System.out.println("Current Points:");
        System.out.println("Player " + p1.getPlayerNumber() + ": " + p1.getPoints());
        System.out.println("Player " + p2.getPlayerNumber() + ": " + p2.getPoints());
        System.out.println();
    }
}
