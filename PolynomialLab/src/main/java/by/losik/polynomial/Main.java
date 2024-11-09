package by.losik.polynomial;

public class Main {
    public static void main(String[] args) {
        try {
            OperationsUsage usage = new OperationsUsage();
            usage.chooseOperation();
        } catch (Exception e) {
            System.out.println("Wrong input");
            e.printStackTrace();
            return;
        }
    }
}