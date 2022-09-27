package spil;

public class RaffleCup {

    public RaffleCup(){

    }
    public void roll(Dice d1, Dice d2){
        d1.roll();
        d2.roll();
    }
    public boolean getEns(Dice d1, Dice d2){
        if (d1.getFaceValue() == d2.getFaceValue())
            System.out.println("Terningerne har samme værdi af:" + d1);
        return true;
    }
    public int getSum(Dice d1, Dice d2){
        return d1.getFaceValue()+d2.getFaceValue();
    }
}
