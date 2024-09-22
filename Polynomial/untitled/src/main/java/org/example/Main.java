package org.example;
import org.example.polynomial.*;

public class Main {
    public static void main(String[] args) {
        try{
            operationsUsage U = new operationsUsage();
            U.chooseOperation();
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}