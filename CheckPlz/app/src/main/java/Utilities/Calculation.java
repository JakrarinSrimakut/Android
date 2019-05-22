package Utilities;

import com.example.h.checkplz.PersonOrder;

import java.util.ArrayList;

public class Calculation {

    //returns total
    public static double calculateTotal(ArrayList<PersonOrder> mPersonOrderList) {
        double total = 0;
        double subTotal = 0;
        //double tax = 0;
        double calculateTipTenpercent = 0;
        total = calculateSubTotal(mPersonOrderList);
        //tax = calculateTax(subTotal);


        return total;
    }

    //return sub total
    public static double calculateSubTotal(ArrayList<PersonOrder> mPersonOrderList){
        double subTotal = 0;
        for(int i = 0; i<mPersonOrderList.size(); i++){
            subTotal += mPersonOrderList.get(i).getmOrderCost();
        }
        return subTotal;
    }

    //return tax
//    public static double calculateTax(double subTotal){
//
//
//    }
    //return tip 10%

    //return tip 15%

    //return tip 20%
}
