package Utilities;

public class Calculation {

    //returns total value
    public static int calculateTotalValue(int maxEnterNumber, Double[] enteredNumberCost, Integer[] enteredNumberAmount ) {
        int sum = 0;

        for(int i = 0; i<maxEnterNumber; i++){
            sum += enteredNumberCost[i] * enteredNumberAmount[i];
        }
        return sum;
    }

    //return tip 10%

    //return tip 15%

    //return tip 20%
}
