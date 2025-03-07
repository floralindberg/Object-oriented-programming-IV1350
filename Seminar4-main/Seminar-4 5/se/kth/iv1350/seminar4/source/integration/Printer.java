package se.kth.iv1350.seminar4.source.integration;



public class Printer {
    private static Printer instance;

    /**
     * Method that prints whole the receipt
     * @param time the time of the sale
     * @param info all of the information about the sale
     */
    public void print(String time, String info, double amountInRegister){

        System.out.println("Time of sale:" + time);
        System.out.println(info);
        System.out.println("Amount in Register:" + String.format("%.2f",amountInRegister));

    }

    /**
     * Method that returns the instance of the printer
     * @return the instance of the printer
     */

    public static synchronized Printer getInstance() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }
}
