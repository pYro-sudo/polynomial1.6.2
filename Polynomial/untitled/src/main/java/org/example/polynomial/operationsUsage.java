package org.example.polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class operationsUsage {
    private polynomial Poly = new polynomial();

    public polynomial getPoly() {
        return Poly;
    }

    public void printer(polynomial P){
        P.getPolynomial().forEach(x->System.out.print(x.getValue()+"*x^"+x.getPower()));
        System.out.print("\n");
    }
    public void inputCoefficients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the configuration of the polynomial\nFor breaking the cycle press 'q'\n" +
                "Enter something besides 'q' if you want to continue");

        while(!scanner.nextLine().equals("q")){
            try{
                double val; int power;
                System.out.println("Enter the power");
                power = scanner.nextInt();
                System.out.println("Enter the value of the coefficient");
                val = scanner.nextDouble();
                this.Poly.setMonomial(power,val);
            }
            catch (Exception e){
                System.out.println("Wrong input");
                e.printStackTrace();
                this.Poly.getPolynomial().clear();
                return;
            }
        }

    }

    public void getValue() throws Exception{
        System.out.println("Enter the power");
        Scanner scanner = new Scanner(System.in);
        int power = scanner.nextInt();
        this.Poly.getCoefficients(power).forEach(x->System.out.println("The coefficient is:"+x.getValue()));
    }

    public polynomial addPolynomials() throws Exception{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.polynomialSum(Poly));

        return Res;
    }

    public polynomial subtractPolynomials() throws Exception{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.subtractPolynomial(Poly));

        return Res;
    }

    public polynomial multiplyPolynomials() throws Exception{
        operationsUsage U1 = new operationsUsage();
        U1.inputCoefficients();
        polynomial Res = new polynomial();

        Res.setPolynomial(U1.getPoly().getPolynomial());
        Res.setPolynomial((List<monomial>) Res.multiplyPolynomial(Poly));

        return Res;
    }

    public List<polynomial> dividePolynomials() throws Exception{
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
        System.out.println("Welcome!\nEnter something besides 'q'");
        Scanner scanner = new Scanner(System.in);

        operationsUsage Usage = new operationsUsage();
        while(!scanner.nextLine().equals("q")){
            System.out.print("Enter the number of operation\n\"enter\" For entering coefficients\n\"add\" For adding polynomials\n\"sub\" For subtracting polynomials" +
                    "\n\"mul\" For multiplying polynomials\n\"div\" For dividing polynomials\n\"q\" For quitting\n");
            choice = scanner.nextLine();
            switch (choice){
                case "val":
                    if(!this.Poly.getPolynomial().isEmpty()){
                        getValue();
                    }
                    else{
                        System.out.println("Nothing is in the first polynomial");
                        break;
                    }
                case "enter":
                    Usage.inputCoefficients();
                    break;
                case "add":
                    if(!Usage.getPoly().getPolynomial().isEmpty()){
                        printer(Usage.addPolynomials());
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "sub":
                    if(!Usage.getPoly().getPolynomial().isEmpty()){
                        printer(Usage.subtractPolynomials());
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "mul":
                    if(!Usage.getPoly().getPolynomial().isEmpty()){
                        printer(Usage.multiplyPolynomials());
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "div":
                    if(!Usage.getPoly().getPolynomial().isEmpty()){
                        Usage.dividePolynomials().stream().forEach(x->printer(x));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "q":
                    return null;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }

        return choice;
    }
}