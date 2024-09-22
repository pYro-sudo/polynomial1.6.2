package org.example.polynomial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class operationsUsage {
    private polynomial Poly = new polynomial();

    public polynomial getPoly() {
        return Poly;
    }

    public void printer(polynomial P){
        P.getPolynomial().stream().forEach(x->System.out.print(x.getValue()+"*x^"+x.getPower()));
    }
    public polynomial inputCoefficients() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the configuration of the polynomial\nFor breaking the cycle press 'q'");

        while(!scanner.nextLine().equals("q")){
            Double val; Integer power;
            System.out.println("Enter the power");
            power = scanner.nextInt();
            System.out.println("Enter the value of the coefficient");
            val = scanner.nextDouble();
            this.Poly.setMonomial(power,val);
        }

        return Poly;
    }

    public List<monomial> getValue() throws IOException{
        System.out.println("Enter the power");
        Scanner scanner = new Scanner(System.in);
        Integer power = scanner.nextInt();
        this.Poly.getCoefficients(power).stream().forEach(x->System.out.println("The coefficient is:"+x.getValue()));
        return this.Poly.getPolynomial();
    }

    public polynomial addPolynomials() throws IOException{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.polynomialSum(Poly));

        return Res;
    }

    public polynomial subtractPolynomials() throws IOException{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.subtractPolynomial(Poly));

        return Res;
    }

    public polynomial multiplyPolynomials() throws IOException{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.multiplyPolynomial(Poly));

        return Res;
    }

    public List<polynomial> dividePolynomials() throws IOException{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        List<polynomial> Result = new ArrayList<>();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.divPolynomial(Poly));
        Result.add(Res);
        System.out.println("The div part is:" + Res);
        Res.setPolynomial((List<monomial>) Res.modPolynomial(Poly));
        Result.add(Res);
        System.out.println("The mod part is:" + Res);

        return Result;
    }

    public String chooseOperation() throws Exception{
        String choice = new String();
        System.out.println("Welcome!");
        Scanner scanner = new Scanner(System.in);

        operationsUsage U1 = new operationsUsage();
        while(!scanner.nextLine().equals("q")){
            System.out.print("Enter the number of operation\n\"enter\" For entering coefficients\n\"add\" For adding polynomials\n\"sub\" For subtracting polynomials" +
                    "\n\"mul\" For multiplying polynomials\n\"div\" For dividing polynomials\n");
            choice = scanner.nextLine();
            switch (choice){
                case "enter":
                    U1.inputCoefficients();
                    break;
                case "add":
                    if(!U1.getPoly().getPolynomial().isEmpty()){
                        printer(U1.addPolynomials());
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "sub":
                    if(!U1.getPoly().getPolynomial().isEmpty()){
                        printer(U1.subtractPolynomials());
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "mul":
                    if(!U1.getPoly().getPolynomial().isEmpty()){
                        printer(U1.multiplyPolynomials());
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "div":
                    if(!U1.getPoly().getPolynomial().isEmpty()){
                        U1.dividePolynomials().stream().forEach(x->printer(x));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }

        return choice;
    }
}