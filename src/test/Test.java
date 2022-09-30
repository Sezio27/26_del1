package test;

import spil.Dice;
import spil.RaffleCup;

public class Test {
    public static void main(String[] args) {
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        RaffleCup rc = new RaffleCup();
        int[] occurrences = new int[11];
        double[] occurrences_in_percentage = new double[11];
        double[] probabilities = new double[11];
        double[] differences = new double[11];

        int sum, identical;
        identical = 0;

        //Fill occurrences list and determine number of identical rolls in 1000 rolls;
        for (int i = 0; i < 1000; i++) {
            rc.roll(d1, d2);
            sum = d1.getFaceValue() + d2.getFaceValue();
            if (rc.getEns(d1, d2)) identical++;
            occurrences[sum - 2]++;
        }

        //Convert occurrences to percentage;
        for (int i = 0; i < occurrences_in_percentage.length ; i++) {
            double percentage = (occurrences[i]/1000.0) * 100.0;
            occurrences_in_percentage[i] = (double) Math.round(percentage*100.0)/100.0;
        }

        //Calculate theoretical probabilities
        for (int i = 0; i <= probabilities.length/2; i++) {
            double probability = ((i+1)/36.0)*100.0;
            probabilities[i] = (double) Math.round(probability*100.0)/100.0;
            probabilities[probabilities.length-i-1] =  probabilities[i];
        }

        //Calculate difference between theoretical and actual occurrences in percentage
        for (int i = 0; i < differences.length; i++) {
            differences[i] = Math.round((Math.abs(probabilities[i] - occurrences_in_percentage[i]))*100.0)/100.0;
        }

        //Print table
        System.out.println("\t\t\tProbability\t\t\tOccurrences\t\t\tDifference");
        for (int i = 0; i < occurrences.length; i++) {
            System.out.println(i + 2 + ":\t\t\t" + probabilities[i] + "%\t\t\t\t" + occurrences[i] + " (" + occurrences_in_percentage[i] + "%)\t\t\t" + differences[i] + "%" );
        }

        //Print statistics of identical rolls
        double idenProb = Math.round((1.0/6.0)*100.0*100)/100.0;
        double idenOccInPerc = Math.round(((identical/1000.0) * 100.0)*100.0)/100.0;
        double idenDiff = Math.round((Math.abs(idenProb - idenOccInPerc))*100.0)/100.0;

        System.out.println("Identical" + ":\t" + idenProb + "%\t\t\t\t" + identical + " ("+idenOccInPerc+"%)\t\t\t" + idenDiff +"%" );

    }
}
