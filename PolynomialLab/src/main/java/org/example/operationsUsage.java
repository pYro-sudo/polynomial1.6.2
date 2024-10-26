package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class operationsUsage {
    private polynomial Poly = new polynomial();
    private polynomial Poly1 = new polynomial();

    public polynomial getPoly() {
        return Poly;
    }
    public polynomial getPoly1(){return Poly1;}

    public polynomial printer(polynomial P){
        P.getPolynomial().forEach(x->System.out.print(x.getValue()+"*x^"+x.getPower()));
        System.out.print("\n");
        return P;
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

        System.out.println("Enter the configuration of the polynomial\nFor breaking the cycle press 'q'\n" +
                "Enter something besides 'q' if you want to continue");

        while(!scanner.nextLine().equals("q")){
            try{
                double val; int power;
                System.out.println("Enter the power");
                power = scanner.nextInt();
                System.out.println("Enter the value of the coefficient");
                val = scanner.nextDouble();
                this.Poly1.setMonomial(power,val);
            }
            catch (Exception e){
                System.out.println("Wrong input");
                e.printStackTrace();
                this.Poly1.getPolynomial().clear();
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

    public void chooseOperation() throws Exception{
        String choice = new String();
        System.out.println("Welcome!\nEnter something besides 'q'");
        Scanner scanner = new Scanner(System.in);

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
                        System.out.println("Nothing is in the polynomial at this power");
                        break;
                    }
                case "enter":
                    this.inputCoefficients();

                    break;
                case "add":
                    if(!this.getPoly1().getPolynomial().isEmpty() && !this.getPoly().getPolynomial().isEmpty()){
                        printer(Poly1.polynomialSum(this.getPoly()));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "sub":
                    if(!this.getPoly1().getPolynomial().isEmpty() && !this.getPoly().getPolynomial().isEmpty()){
                        printer(Poly.subtractPolynomial(this.getPoly1()));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "mul":
                    if(!this.getPoly1().getPolynomial().isEmpty() && !this.getPoly().getPolynomial().isEmpty()){
                        printer(Poly1.multiplyPolynomial(this.getPoly()));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "div":
                    if(!this.getPoly1().getPolynomial().isEmpty() && !this.getPoly().getPolynomial().isEmpty()){
                        printer(Poly.divPolynomial(Poly1));
                        printer(Poly.modPolynomial(Poly1));
                    }
                    else{
                        System.out.println("No input for the first polynomial");
                    }
                    break;
                case "q":
                    return ;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
        return ;
    }
}