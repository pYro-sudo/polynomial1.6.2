package org.example;
import org.example.polynomial.*;

public class Main {
    public static void main(String[] args) {
        try{
            operationsUsage U = new operationsUsage();
            U.chooseOperation();
        }
        catch (Exception e){
            System.out.println("Wrong input");
            e.printStackTrace();
            return;
        }
    }
}