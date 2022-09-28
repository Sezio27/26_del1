package spil;

public class Game {
    public static void main(String[] args) {

        RaffleCup r1 = new RaffleCup();
        Dice d1 = new Dice();
        Dice d2 = new Dice();

        for (int i = 0; i < 50; i++) {
            r1.roll(d1, d2);
            System.out.println(r1.getSum(d1, d2));
            r1.getEns(d1, d2);
        }
        d1.setFaceValue(5);
        for (int i = 0; i < 5; i++) {
            System.out.println(d1);
        }

    }
}
