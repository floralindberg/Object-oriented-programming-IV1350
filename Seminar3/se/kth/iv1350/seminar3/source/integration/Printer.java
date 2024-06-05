package se.kth.iv1350.seminar3.source.integration;



public class Printer {


    /**
     * Method that prints whole the receipt
     * @param time the time of the sale
     * @param info all of the information about the sale
     */
    public void print(String time, String info, double amountInRegister){

        System.out.println("Time of sale:" + time);
        System.out.println(info);
        System.out.println("Amount in Register:" + amountInRegister);

    }
}
