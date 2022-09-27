package spil;

public class Dice {

    private int faceValue = 1;

    public Dice(){
    }

    public void roll(){
        faceValue = (int)(Math.random()*6+1);
    }
    public void setFaceValue(int x){
        faceValue = x;
    }
    public int getFaceValue(){
        return faceValue;
    }
    //HVIS GUI
    public void setDice(int x, int y){
    }
    public String toString(){
        return Integer.toString(faceValue);
    }
}
