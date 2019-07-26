package Utilities;

import com.example.h.checkplz.PersonBill;
import com.example.h.checkplz.PersonOrder;

import java.util.ArrayList;

public class Calculation {

    //returns total
    public static double calculateTotal(ArrayList<PersonOrder> mPersonOrderList, double tip) {
        double total = 0;
        //double tax = 0;

        if(mPersonOrderList.isEmpty()){
            return tip;
        }
        total = calculateSubTotal(mPersonOrderList) + tip;
        //tax = calculateTax(subTotal);

        return total;
    }

    //return sub total
    public static double calculateSubTotal(ArrayList<PersonOrder> mPersonOrderList){
        double subTotal = 0;
        for(int i = 0; i<mPersonOrderList.size(); i++){
            subTotal += mPersonOrderList.get(i).getmOrderCost()*mPersonOrderList.get(i).getmOrderAmount();
        }
        return subTotal;
    }

    //return tax percentage by calculating the tax and total input amount
    public static double calculateTax(double taxInput, double totalInput){
        double tax = totalInput/taxInput;
        if(Double.isNaN(tax)){
            return 0;
        }
        return tax;
    }

    //return tip 10%
    public static double calculate10PercentTip(double totalBill){
        return totalBill * 0.1;
    }

    //return tip 15%
    public static double calculate15PercentTip(double totalBill){
        return totalBill * 0.15;
    }

    //return tip 20%
    public static double calculate20PercentTip(double totalBill){
        return totalBill * 0.2;
    }

    public static double calculatePartySubtotal(ArrayList<PersonBill> mPeopleBills) {
        double partySubTotal = 0;
        for (PersonBill pBill: mPeopleBills) {
            partySubTotal += pBill.getmSubTotalBill();
        }
        return partySubTotal;
    }

    public static double calculatePartyGratuity(ArrayList<PersonBill> mPeopleBills) {
        double partyGratuity = 0;
        for (PersonBill pBill: mPeopleBills) {
            partyGratuity += pBill.getmTip();
        }
        return partyGratuity;
    }

    public static double calculatePartyTotal(ArrayList<PersonBill> mPeopleBills) {
        double partyTotal = 0;
        for (PersonBill pBill: mPeopleBills) {
            partyTotal += pBill.getmTotalBill();
        }
        return partyTotal;
    }


}
